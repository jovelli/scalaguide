package com.jovelli.guide

import java.net.URL
import scala.util.Try

object TestTry {
  def buyCigarettes(customer: Customer) = 
    if (customer.age < 15) 
      throw UnderAgeException(s"===Under ager 15 ${customer.age} ===")
    else new Cigarettes
    
  def parseUrl(url: String): Try[URL] = { Try(new URL(url)) } 
} 

case class Customer(age: Int)

class Cigarettes

case class UnderAgeException(message: String) extends Exception 
 