package com.jovelli.guide

import java.net.URL
import scala.util.Try
import scala.io.Source
import java.net.UnknownHostException
import java.net.MalformedURLException
import scala.util.control.Exception.catching

object TestsWebPages {
  
  def handle[Ex <: Throwable, T](exceptionType: Class[Ex])(block: => T) = {
    catching(exceptionType).either(block).asInstanceOf[Either[Ex, T]]
  }
  
  def buyCigarettes(customer: Customer): Either[UnderAgeException, Cigarettes] = 
    if (customer.age < 15) Left(UnderAgeException(customer.age))
    else Right(new Cigarettes)
    
  def parseUrl(url: String): Try[URL] = { Try(new URL(url)) }
  
  def getUrl(url: String): Either[MalformedURLException, URL] = { handle(classOf[MalformedURLException])(new URL(url)) }
    
  def getWebContentLowLevelData(urlStr: String): Try[Iterator[String]] = {
    for {
      url <- parseUrl(urlStr)
      conn <- Try(url.openConnection)
      is <- Try(conn.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines()
  }
  
  def getWebContent(url: String) = {
    getWebContentLowLevelData(url).recover {
    	case e: UnknownHostException => "Well, are you sure your host exists ? Error message: " + e.getMessage
    	case e: MalformedURLException => "Check your url. It is not fine. Error message: " + e.getMessage
    	case e => "NOK - Unexpected Error" + e.getMessage
    }  
  }
  
  def getContent(url: URL): Either[String, Source] = {
    url.getHost.contains("google") match {
      case true => Left("Not allowed to access google here")
      case _ => Right(Source.fromURL(url))
    }
  }
  
  def averageBetweenPages(url1: URL, url2: URL): Either[String, Int] = 
    for {
    	lines1 <- getContent(url1).right
    	lines2 <- getContent(url2).right
    	size1 <- Right(lines1.getLines().size).right
    	size2 <- Right(lines2.getLines().size).right
    } yield (size1 + size2) / 2
  
  val blackList = List(
    BlacklistResource(new URL("http://www.google.com.br"), Set("John", "James", "Oswald")),
    BlacklistResource(new URL("http://www.uol.com.br"), Set("John", "John")),
    BlacklistResource(new URL("http://www.dw.com"), Set.empty),
    BlacklistResource(new URL("http://www.nytimes.com"), Set.empty)
  )
} 

case class BlacklistResource(url: URL, visitors: Set[String])

case class Customer(age: Int)

class Cigarettes

case class UnderAgeException(age: Int) extends Exception 
 