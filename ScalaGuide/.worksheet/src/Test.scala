
import com.jovelli.guide._
 
object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(149); 
  // val user: User = new PremiumUser("Romulo", 10)
  val user: User = new FreeUser("Maurício", 10, 0.4);System.out.println("""user  : com.jovelli.guide.User = """ + $show(user ));$skip(271); 
  
  val message:String = user match {
  	case FreeUser(name, _, probability) =>
  		if (probability > 0.75) "Hello Dear FreeUser " + name else "Hello FreeUser " + name
  	case PremiumUser(name, score) => "Hello PremiumUser " + name + score
  	case _ => "Other user"
  };System.out.println("""message  : String = """ + $show(message ));$skip(20); 

  println(message);$skip(49); 

  val xs: List[Int] = 11 :: 2 :: 4 :: 10 :: Nil;System.out.println("""xs  : List[Int] = """ + $show(xs ));$skip(339); 
  //val xs: List[Int] = 11 :: 2 :: Nil

  val message2 = xs match {
    case List(a) => "Um elemento"
  	case List(a, b) =>  "Dois elementos"
  	case List(primeiro, segundo, terceiro) => "Tres elementos"
  	case List(a, b, _*) => "Mais elementos (a * b) " + (a * b)
  	case _ => "Recebeu Qualquer coisa, Não é um List com elementos"
  };System.out.println("""message2  : String = """ + $show(message2 ));$skip(37); 

	val names = "John Armstrong Smith";System.out.println("""names  : String = """ + $show(names ));$skip(116); 
	
	names match {
		case GivenName(firstName, _*) => println("Name: " + firstName)
		case _ => println("NoNames")
	};$skip(103); 
	
	names match {
		case GivenMoreNames(_1, lastName, _*) => println("Hello " + _1 + " " + lastName)
	};$skip(36); 
	
	val a = ("Nome", 13, "Endereco");System.out.println("""a  : (String, Int, String) = """ + $show(a ));$skip(24); 
	
	println(a._1 + a._2);$skip(63); 
	
	
	val lists = List(1, 2, 4) :: List.empty :: List(4) :: Nil;System.out.println("""lists  : List[List[Int]] = """ + $show(lists ));$skip(58); val res$0 = 
	
	for {
		list @ head :: Nil <- lists
	} yield list.size;System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(35); 
	
	
	val testee = List(1, 2, 3, 4);System.out.println("""testee  : List[Int] = """ + $show(testee ));$skip(69); val res$1 = 
	
	for {
		number <- testee
		if (number * 3 ==  9)
	}
	yield number;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(143); 


	//Anonymous Patterns
	val wordsFrequency = ("Orange", 10) :: ("Strawberry", 5) :: ("Apple", 15) :: ("Melon", 11) :: ("Pineapple", 2) :: Nil;System.out.println("""wordsFrequency  : List[(String, Int)] = """ + $show(wordsFrequency ));$skip(91); 
	
	val predicateQty: ((String, Int)) => Boolean = { case (_, qty) => qty > 3 && qty < 14 };System.out.println("""predicateQty  : ((String, Int)) => Boolean = """ + $show(predicateQty ));$skip(73); 
	
  val mapName: ((String, Int)) => String = { case (name, _) => name };System.out.println("""mapName  : ((String, Int)) => String = """ + $show(mapName ));$skip(148); 
  
  //Partial Functions
  def filtrarFrequenciaPf: PartialFunction[(String, Int), String] = { case (word, freq) if freq > 3 && freq < 14 => word };System.out.println("""filtrarFrequenciaPf: => PartialFunction[(String, Int),String]""");$skip(163); 
   
	def filtrosFrequenciaPalavras(words: Seq[(String, Int)]): Seq[String] =  words.collect(filtrarFrequenciaPf);System.out.println("""filtrosFrequenciaPalavras: (words: Seq[(String, Int)])Seq[String]""");$skip(45); val res$2 =  //words.collect filter(predicateQty).map(mapName)
	
	filtrosFrequenciaPalavras(wordsFrequency);System.out.println("""res2: Seq[String] = """ + $show(res$2))}
	
	
	
}
