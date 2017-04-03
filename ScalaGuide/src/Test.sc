
import com.jovelli.guide._

object Test {
  val user: User = new PremiumUser("Romulo", 10)  //> user  : com.jovelli.guide.User = com.jovelli.guide.PremiumUser@593634ad
  //val user: User = new FreeUser("Maurício", 10, 1)
  
  val message:String = user match {
  	case FreeUser(name, _, probability) =>
  		if (probability > 0.75) "Hello Dear FreeUser " + name else "Hello FreeUser " + name
  	case PremiumUser(name, score) => "Hello PremiumUser " + name + score
  	case _ => "Other user"
  }                                               //> message  : String = Hello PremiumUser Romulo10
  
  println(message)                                //> Hello PremiumUser Romulo10
  	
}