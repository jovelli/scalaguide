package com.jovelli.guide

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.actor.SupervisorStrategy.{ Directive, Resume } 
import scala.concurrent.Future
import akka.pattern.ask
import akka.pattern.pipe
import akka.util.Timeout
import scala.concurrent.duration._

object Barista {
  import CoffeeCustomer.CaffeineWithdrawalWarning
  
  sealed trait CoffeeRequest
  case object CappuccinoRequest extends CoffeeRequest
  case object EspressoRequest extends CoffeeRequest
  case object ClosingTime
  case class Cup(state: Cup.State)
  object Cup {
    sealed trait State 
    case object Clean extends State
    case object Dirty extends State
    case object Filled extends State
  }
  case class Bill(check: Double)

  val system = ActorSystem("Bar")
  
  val barista: ActorRef = system.actorOf(Props[Barista], "Barista")
  val john: ActorRef = system.actorOf(Props(classOf[CoffeeCustomer], barista), "John")
  val mary: ActorRef = system.actorOf(Props(classOf[CoffeeCustomer], barista), "Mary")
  
  def sendMessage() = {
    implicit val timeout = Timeout(2.second)
    implicit val ec = system.dispatcher
    
    john ! CaffeineWithdrawalWarning
    mary ! CaffeineWithdrawalWarning
    barista ! EspressoRequest
    barista ! ClosingTime 
  }
}
class Barista extends Actor {
  import Barista._
  import Barista.Cup._
  import Register._
  
  var cappuccinoCount = 0 
  var espressoCount = 0
  
  val decider: PartialFunction[Throwable, Directive] = { 
    case _: PaperJamException => Resume
  }
  
  implicit val timeout = Timeout(4.seconds)
  val register = context.actorOf(Props[Register], "Register")
  import context.dispatcher
  
  def receive = {
    case CappuccinoRequest =>
      val cappuccino = register ? Transaction(Cappuccino)
      cappuccino.map((Cup(Filled), _)).pipeTo(sender)
      cappuccinoCount += 1
      println("I'm going to prepare your Cappuccino Sir") 
      
    case EspressoRequest =>
      register ! Transaction(Espresso)
      espressoCount += 1
      println("Ok, got it, A great and fresh new Espresso is coming")
      
    case ClosingTime => 
      val totalToday = register ? RevenueTodayRequest
      
      totalToday.onSuccess {
        case RevenueToday(total) => {
          println(s"Closing Time, go home and take a rest. Revenue Today $total. Cappuccinos: $cappuccinoCount Espressos: $espressoCount")
          context.system.terminate()
        }
      }
  }
  
  override def supervisorStrategy: SupervisorStrategy = { 
    OneForOneStrategy()(decider.orElse(SupervisorStrategy.defaultDecider))
  }
}


object CoffeeCustomer {
  case object CaffeineWithdrawalWarning
}
class CoffeeCustomer(caffeineSource: ActorRef) extends Actor with ActorLogging {
  import Barista._
  import Barista.Cup._
  import CoffeeCustomer._
  
  def receive = {
    case CaffeineWithdrawalWarning => caffeineSource ! CappuccinoRequest
    case (Cup(Filled), Bill(price)) => log.info(s"${self} says Caffeine is here for the price of ${price}") 
  }
}


object Register {
  sealed trait Item
  case object Espresso extends Item
  case object Cappuccino extends Item
  case object RevenueTodayRequest
  case class RevenueToday(total: Double)
  case class Transaction(item: Item)
  class PaperJamException(msg: String) extends Exception(msg)
}
class Register extends Actor with ActorLogging {
  import Register._
  import Barista._
  import util.Random
  
  var total = 0.0;
  val prices = Map[Item, Double](Espresso -> 20.0, Cappuccino -> 23.5)
  
  def receive = {
    case Transaction(item) => 
      val price = prices(item)
      
      sender ! Bill(prices(item))
      total += price
      
      log.info(s"Partial Revenue is $total")
      
      if (Random.nextBoolean()) {
        throw new PaperJamException("Not again...")   
      }
      
    case RevenueTodayRequest => sender ! RevenueToday(total)
  }
  
  override def postRestart(reason: Throwable) {
    super.postRestart(reason)
    log.info(s"Restarted because of $reason, and revenue is $total")
  }
}