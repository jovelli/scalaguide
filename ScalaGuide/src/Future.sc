import com.jovelli.guide.FutureTests._
import com.jovelli.guide._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Success, Failure }
import scala.concurrent.Future
 
object FutureTest {
	      
	/*selecionar(CoffeeBeans("Pele")).onComplete {
		case Success(selecionado) => println("Got it, coffee ground " + selecionado)
		case Failure(ex) => println("Exception, it is not possible to grind this kind of coffee [" + ex.getMessage + "]")
	}*/
	
 /*val temperaturaOk: Future[Boolean] = esquentarAgua(Water(25)).map{
  	water => println("we're in the future " + water.temperature)
  	water.temperature.equals(100)
  } */
    
  prepareCappuccino()                             //> inicio leite fermentando
                                                  //| termino leite fermentando
                                                  //| inicio selecionando cafe
                                                  //| termino selecionando cafe
                                                  //| inicio esquentando agua
                                                  //| hot, it's hot
                                                  //| inicio cafe fermentando
                                                  //| termino cafe fermentando
                                                  //| res0: scala.util.Try[com.jovelli.guide.Cappuccino] = Success(Cappuccino(DONE
                                                  //| ))-
                                                  
                                                   
 
  
  Thread.sleep(5000)
}