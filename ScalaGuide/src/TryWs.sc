package com.jovelli.guide

import com.jovelli.guide.TestTry._
import scala.util.{ Try, Success, Failure }
import java.net.URL
import java.io.InputStream

object TryWs {
     	
   val youngCustomer = new Customer(12)           //> youngCustomer  : com.jovelli.guide.Customer = Customer(12)
   
   try {
   		buyCigarettes(youngCustomer)
   } catch {
   		case UnderAgeException(message) => println(message)
   }                                              //> ===Under ager 15 12 ===
                                                  //| res0: Any = ()
   
    // buyCigarettes(Customer(12))
      
    parseUrl("http://www.uol.com.br").map(_.getProtocol)
                                                  //> res1: scala.util.Try[String] = Success(http)
    parseUrl("error").flatMap(url => Try(url.openConnection()).flatMap(conn => Try(conn.getInputStream)))
                                                  //> res2: scala.util.Try[java.io.InputStream] = Failure(java.net.MalformedURLExc
                                                  //| eption: no protocol: error)

		parseUrl("error").getOrElse(new URL("http://www.google.com.br"))
                                                  //> res3: java.net.URL = http://www.google.com.br
}