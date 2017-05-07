import com.jovelli.guide.TestsWebPages._
import java.net.URL;
import com.jovelli.guide.BlacklistResource

object Either {
  val source = getContent(new URL("https://www.uol.com.br"))
                                                  //> source  : Either[String,scala.io.Source] = Right(non-empty iterator)

    source match {
    	case Left(message) => println(message)
    	case Right(source) => source.getLines().foreach(println)
    }                                             //> <!DOCTYPE html>
                                                  //| <html>
                                                  //|   <head lang="pt-br">
                                                  //|     <!-- cache version: '1bf5gplqu'
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
                                                  //|     <link rel="dns-prefetch" href="https://widget.perfil.uol.com.br">
                                                  //|         <
                                                  //| Output exceeds cutoff limit.
    
    source.right.map(s => s.getLines().foreach(println))
                                                  //> res0: Product with Serializable with scala.util.Either[String,Unit] = Right(
                                                  //| ())
    
    val content = getContent(new URL("http://www.google.com.br")).right.map(_.getLines())
                                                  //> content  : Product with Serializable with scala.util.Either[String,Iterator[
                                                  //| String]] = Left(Not allowed to access google here)
    val moreContent2 = getContent(new URL("http://www.uol.com.br")).left.map(Iterator(_))
                                                  //> moreContent2  : Product with Serializable with scala.util.Either[Iterator[St
                                                  //| ring],scala.io.Source] = Right(non-empty iterator)
                                                  
    val imutable = new URL("http://www.scala-lang.org/api/current/scala/collection/immutable/index.html")
                                                  //> imutable  : java.net.URL = http://www.scala-lang.org/api/current/scala/colle
                                                  //| ction/immutable/index.html
    val mutable = new URL("http://www.scala-lang.org/api/current/scala/collection/mutable/index.html")
                                                  //> mutable  : java.net.URL = http://www.scala-lang.org/api/current/scala/collec
                                                  //| tion/mutable/index.html
    
    // getContent(imutable).right.flatMap(a => getContent(mutable).right.map(b => (a.getLines.size + b.getLines.size) / 2))
		// averageBetweenPages(imutable, mutable)
                                                  
    getContent(new URL("http://www.google.com.br")).fold(println(_), b => println(b.getLines().size))
                                                  //> Not allowed to access google here
                                                  
    getUrl("ht2tp://www.google.com.br")           //> res1: Either[java.net.MalformedURLException,java.net.URL] = Left(java.net.M
                                                  //| alformedURLException: unknown protocol: ht2tp)
                                                  
    val blackListVisitor = blackList.map(r => if (r.visitors.isEmpty) Left(r.url) else Right(r.visitors))
                                                  //> blackListVisitor  : List[Product with Serializable with scala.util.Either[j
                                                  //| ava.net.URL,Set[String]]] = List(Right(Set(John, James, Oswald)), Right(Set
                                                  //| (John)), Left(http://www.dw.com), Left(http://www.nytimes.com))
    val suspiciousResources = blackListVisitor.flatMap(_.left.toOption)
                                                  //> suspiciousResources  : List[java.net.URL] = List(http://www.dw.com, http://
                                                  //| www.nytimes.com)
    val suspiciousCitizens = blackListVisitor.flatMap(_.right.toOption).flatten.distinct
                                                  //> suspiciousCitizens  : List[String] = List(John, James, Oswald)
    
}