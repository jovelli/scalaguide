
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{ Success, Failure }
import com.jovelli.guide.PromiseTest._
import com.jovelli.guide._
 
object PromiseWs {
 	
 	//meal.success(Food(20.3))
	//meal.future
      
  preparelunch(100).onComplete{
  	case Success(Food(price)) => println(Food(price))
  	case Failure(FoodException(msg)) => println(msg)
  	case _ => println("No case selected")
  }

  Thread.sleep(3000)                              //> Start preparing lunch
                                                  //| Finish preparing lunch
                                                  //| Food(100.0)/
}