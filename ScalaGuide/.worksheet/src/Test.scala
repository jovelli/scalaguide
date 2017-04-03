
import com.jovelli.guide._

object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(91); 
  val user: User = new PremiumUser("Romulo", 10);System.out.println("""user  : com.jovelli.guide.User = """ + $show(user ));$skip(324); 
  //val user: User = new FreeUser("Maurício", 10, 1)
  
  val message:String = user match {
  	case FreeUser(name, _, probability) =>
  		if (probability > 0.75) "Hello Dear FreeUser " + name else "Hello FreeUser " + name
  	case PremiumUser(name, score) => "Hello PremiumUser " + name + score
  	case _ => "Other user"
  };System.out.println("""message  : String = """ + $show(message ));$skip(22); 
  
  println(message)}
  	
}
