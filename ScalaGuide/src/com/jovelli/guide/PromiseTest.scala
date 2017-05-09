package com.jovelli.guide

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.Promise

case class FoodException(message: String) extends Exception

case class Food(price: Double)

object PromiseTest {

  val f: Future[String] = Future { "This is a String" }

  private val meal = Promise[Food]()

  def preparelunch(price: Double): Future[Food] = {
    Future {
      price match {
        case p if (p > 100) => meal.failure(FoodException("Too expensive")) 
        case _ =>  { 
          println("Start preparing lunch")
          meal.success(Food(price))
          println("Finish preparing lunch")
        }
      }
    } 

    meal.future
  } 
}

