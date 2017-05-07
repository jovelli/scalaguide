package com.jovelli.guide

import com.jovelli.guide.TestTry._
import scala.util.{ Try, Success, Failure }
import java.net.URL
import java.io.InputStream
import java.net.UnknownHostException
import java.net.MalformedURLException

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
                                                  
    parseUrl("http://www.facebook.com").filter(_.getProtocol == "http").foreach(println(_))
                                                  //> http://www.facebook.com

		getWebContent("http://www.uol.com.br").foreach(println(_))
                                                  //> non-empty iterator
     
    val message: String = parseUrl("http://www.google.com/") match {
    	case Success(url) => "Success " + url
    	case Failure(error) => "Error " + error
    }                                             //> message  : String = Success http://www.google.com/
    
    println(message)                              //> Success http://www.google.com/
      
		getWebContent("http://itDoestNotExist").foreach(println(_))
                                                  //> Well, are you sure your host exists ? Error message: itDoestNotExist
    
    val source = getContent(new URL("https://www.uol.com.br"))
                                                  //> source  : Either[String,scala.io.Source] = Right(non-empty iterator)

    source match {
    	case Left(message) => println(message)
    	case Right(source) => source.getLines().foreach(println)
    }                                             //> <!DOCTYPE html>
                                                  //| <html>
                                                  //|   <head lang="pt-br">
                                                  //|     <!-- cache version: '1beo4ti8u'
                                                  //|       // base path: ''
                                                  //|       // view path: '/camaleao/view/web'
                                                  //|       //  prod  -->
                                                  //|         <link rel="dns-prefetch" href="https://jsuol.com.br">
                                                  //|     <link rel="dns-prefetch" href="https://stc.uol.com">
                                                  //|     <link rel="dns-prefetch" href="https://conteudo.imguol.com.br">
                                                  //|     <link rel="dns-prefetch" href="https://imguol.com.br">
                                                  //|     <link rel="dns-prefetch" href="https://imguol.com">
                                                  //|     <link rel="dns-prefetch" href="https://e.imguol.com">
                                                  //|     <link rel="dns-prefetch" href="//click.uol.com.br">
                                                  //|         <link rel="dns-prefetch" href="https://smetrics.uol.com.br">
                                                  //|         <link rel="dns-prefetch" href="https://tm.uol.com.br">
                                                  //|     <link rel="dns-prefetch" href="https://tm.jsuol.com.br">
                                                  //|         <link rel="dns-prefetch" href="https://notify.uol.com.br">
                                                  //|     <link rel="dns-prefetch" href="https://widget.perfil.uol.com
                                                  //| Output exceeds cutoff limit.
    
    source.right.map(s => s.getLines().foreach(println))
                                                  //> res4: Product with Serializable with scala.util.Either[String,Unit] = Right
                                                  //| (())
    
    val content = getContent(new URL("http://www.google.com.br")).right.map(_.getLines())
                                                  //> content  : Product with Serializable with scala.util.Either[String,Iterator
                                                  //| [String]] = Left(Not allowed to access google here)
    val moreContent2 = getContent(new URL("http://www.uol.com.br")).left.map(Iterator(_))
                                                  //> moreContent2  : Product with Serializable with scala.util.Either[Iterator[S
                                                  //| tring],scala.io.Source] = Right(non-empty iterator)
}