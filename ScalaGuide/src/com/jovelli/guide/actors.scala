package com.jovelli.guide

import akka.actor.{ActorSystem, Actor, ActorRef, Props}
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
  val coffeeCustomer: ActorRef = system.actorOf(Props(classOf[CoffeeCustomer], barista), "CoffeeCustomer")
  
  def sendMessage() = {
    implicit val timeout = Timeout(2.second)
    implicit val ec = system.dispatcher
    
    coffeeCustomer ! CaffeineWithdrawalWarning
    coffeeCustomer ! CaffeineWithdrawalWarning
    
    val futureEspresso: Future[Any] = barista ? EspressoRequest
    
    futureEspresso.onSuccess {
      case Bill(money) => println(s"From Future, you will pay $money for your Espresso")
    }
    
    barista ! ClosingTime 
  }
}
class Barista extends Actor {
  import Barista._
  import Barista.Cup._
  import Register._
  
  var cappuccinoCount = 0 
  var espressoCount = 0
  
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
      val espresso = (register ? Transaction(Espresso))
      espresso.map((Cup(Filled), _)).pipeTo(sender)
      espressoCount += 1
      println("Ok, got it, A great and fresh new Espresso is coming")
      
    case ClosingTime => 
      println(s"Closing Time, go home and take a rest. Today Cappuccinos: $cappuccinoCount Espressos: $espressoCount")
      context.system.terminate()
  }
}


object CoffeeCustomer {
  case object CaffeineWithdrawalWarning
}
class CoffeeCustomer(caffeineSource: ActorRef) extends Actor {
  import Barista._
  import Barista.Cup._
  import CoffeeCustomer._
  
  def receive = {
    case CaffeineWithdrawalWarning => caffeineSource ! CappuccinoRequest
    case (Cup(Filled), Bill(price)) => println(s"${self} says Caffeine is here for the price of ${price}") 
  }
}


object Register {
  sealed trait Item
  case object Espresso extends Item
  case object Cappuccino extends Item
  case class Transaction(item: Item)
}
class Register extends Actor {
  import Register._
  import Barista._
  
  var total = 0.0;
  val prices = Map[Item, Double](Espresso -> 20.0, Cappuccino -> 23.5)
  
  def receive = {
    case Transaction(item) => 
      val price = prices(item)
      sender ! Bill(prices(item))
      total += price
  }
  
}