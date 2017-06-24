package com.jovelli.guide

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy, Terminated}
import akka.actor.SupervisorStrategy.{ Directive, Resume } 
import scala.concurrent.Future
import akka.pattern.ask
import akka.pattern.pipe
import akka.pattern.AskTimeoutException
import akka.util.Timeout
import scala.concurrent.duration._

object Barista {
  import CoffeeCustomer._
  import Register.Item
  
  sealed trait CoffeeRequest
  case object CappuccinoRequest extends CoffeeRequest
  case object EspressoRequest extends CoffeeRequest
  case object ClosingTime
  case class TryAgainLater(coffeeRequest: CoffeeRequest)
  case class Cup(state: Cup.State)
  object Cup {
    sealed trait State 
    case object Clean extends State
    case object Dirty extends State
    case object Filled extends State
  }
  case class Bill(price: Double)

  val system = ActorSystem("Bar")
  
  val barista: ActorRef = system.actorOf(Props[Barista], "Barista")
  val john: ActorRef = system.actorOf(Props(classOf[CoffeeCustomer], barista), "John")
  val mary: ActorRef = system.actorOf(Props(classOf[CoffeeCustomer], barista), "Mary")
  val jeff: ActorRef = system.actorOf(Props(classOf[CoffeeCustomer], barista), "Jeff")
  
  def sendMessage() = {
    implicit val timeout = Timeout(2.second)
    implicit val ec = system.dispatcher
    
    john ! CaffeineWithdrawalWarning
    mary ! CaffeineWithdrawalWarning
    john ! CaffeineEspresso
    jeff ! CaffeineEspresso
    jeff ! CaffeineEspresso
    
    Thread.sleep(15000)
    barista ! ClosingTime 
  }
}
class Barista extends Actor with ActorLogging {
  import Barista._
  import Barista.Cup._
  import Register._
  
  implicit val timeout = Timeout(4.seconds)
  val register = context.actorOf(Props[Register], "Register")
  import context.dispatcher
  
  def receive = {
    case CappuccinoRequest =>
      val s = sender
      val bill = register ? Transaction(Cappuccino)
      bill.map((Cup(Filled), _))
        .recover { case _: AskTimeoutException => TryAgainLater(CappuccinoRequest) } 
        .pipeTo(sender)
      log.info("I'm going to prepare your Cappuccino Sir") 
      
    case EspressoRequest =>
      val s = sender
      val bill = register ? Transaction(Espresso)
      bill.map((Cup(Filled), _))
        .recover { case _: AskTimeoutException => TryAgainLater(EspressoRequest) }
        .pipeTo(sender)
      log.info("Ok, got it, A great and fresh new Espresso is coming")
      
    case ClosingTime => register ! ResultTodayRequest
    
    case ResultToday(total, cappuccinoCount, espressoCount) => 
      log.info(s"Closing Time, go home and take a rest. Revenue Today $total. Cappuccinos: $cappuccinoCount Espressos: $espressoCount")
      context.system.terminate()
  }
}


object CoffeeCustomer {
  case object CaffeineWithdrawalWarning
  case object CaffeineEspresso
}
class CoffeeCustomer(caffeineSource: ActorRef) extends Actor with ActorLogging {
  import Barista._
  import Barista.Cup._
  import CoffeeCustomer._
  import Register._
  import context.dispatcher
  
  def receive = {
    case CaffeineWithdrawalWarning => caffeineSource ! CappuccinoRequest
    case CaffeineEspresso => caffeineSource ! EspressoRequest
    case (Cup(Filled), Bill(price)) => log.info(s"${self} says Caffeine is here for the price of ${price}") 
    case TryAgainLater(coffeeRequest) => 
      log.info("What ? Was it not possible to prepare my beverage ???")
      context.system.scheduler.scheduleOnce(300.millis) {
        caffeineSource ! coffeeRequest
      }
  }
}


object Register {
  sealed trait Item
  case object Espresso extends Item
  case object Cappuccino extends Item
  case object ResultTodayRequest
  case class ResultToday(total: Double, cappuccinoCount: Int, espressoCount: Int)
  case class Transaction(item: Item)
}
class Register extends Actor with ActorLogging {
  import Register._
  import Barista._
  import BillPrinter._

  import context.dispatcher
  implicit val timeout = Timeout(4.seconds)
  
  var total = 0.0
  var cappuccinoCount = 0 
  var espressoCount = 0
  var cntJammed = 0
  
  val prices = Map[Item, Double](Espresso -> 20.0, Cappuccino -> 23.5)
  val billPrinter = context.actorOf(Props[BillPrinter], "BillPrinter")
  val decider: PartialFunction[Throwable, Directive] = { 
    case _: PaperJamException => Resume
  }   
  
  context.watch(billPrinter)
  
  def receive = {
    case Transaction(item) => 
      val price = prices(item)
      val requester = sender
      
      (billPrinter ? printBillRequest(price))
        .map((requester, item, _))
        .pipeTo(self)
        
    case (requester: ActorRef, item: Item, bill: Bill) => 
      total += bill.price
      item match { 
        case Espresso => espressoCount += 1
        case Cappuccino => cappuccinoCount += 1
      }
      log.info(s"Partial Revenue is $total")
      requester ! bill
    
    case ResultTodayRequest => sender ! ResultToday(total, cappuccinoCount, espressoCount)
  }
  
  override def supervisorStrategy: SupervisorStrategy = { 
    OneForOneStrategy()(decider)
  }
}

object BillPrinter {
  case class printBillRequest(price: Double)
  case object KillIt
  class PaperJamException(msg: String) extends Exception(msg)
}
class BillPrinter extends Actor with ActorLogging {
  import BillPrinter._
  import Barista.Bill
  import util.Random
  
  def receive = {
    case printBillRequest(price) => 
      sender ! createBill(price)
            
    case _ => log.error("I can't print this. I don't know what is it.")
  }
  
  def createBill(price: Double): Bill = {
    if (Random.nextBoolean()) {
      throw new PaperJamException("Not again")
    }
    
    Bill(price)  
  }
  
  override def postRestart(reason: Throwable) {
    super.postRestart(reason)
    log.info("Restartng BillPrinter because of " + reason)
  }
  
}
