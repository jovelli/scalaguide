
import com.jovelli.guide._
 
object Test {
  // val user: User = new PremiumUser("Romulo", 10)
  val user: User = new FreeUser("MaurÃ­cio", 10, 0.4)
                                                  //> user  : com.jovelli.guide.User = com.jovelli.guide.FreeUser@f2a0b8e
  
  val message:String = user match {
  	case FreeUser(name, _, probability) =>
  		if (probability > 0.75) "Hello Dear FreeUser " + name else "Hello FreeUser " + name
  	case PremiumUser(name, score) => "Hello PremiumUser " + name + score
  	case _ => "Other user"
  }                                               //> message  : String = Hello FreeUser Maurício

  println(message)                                //> Hello FreeUser Maurício

  val xs: List[Int] = 11 :: 2 :: 4 :: 10 :: Nil   //> xs  : List[Int] = List(11, 2, 4, 10)
  //val xs: List[Int] = 11 :: 2 :: Nil

  val message2 = xs match {
    case List(a) => "Um elemento"
  	case List(a, b) =>  "Dois elementos"
  	case List(primeiro, segundo, terceiro) => "Tres elementos"
  	case List(a, b, _*) => "Mais elementos (a * b) " + (a * b)
  	case _ => "Recebeu Qualquer coisa, NÃ£o Ã© um List com elementos"
  }                                               //> message2  : String = Mais elementos (a * b) 22

	val names = "John Armstrong Smith"        //> names  : String = John Armstrong Smith
	
	names match {
		case GivenName(firstName, _*) => println("Name: " + firstName)
		case _ => println("NoNames")
	}                                         //> Name: John
	
	names match {
		case GivenMoreNames(_1, lastName, _*) => println("Hello " + _1 + " " + lastName)
	}                                         //> Hello John Smith
	
	val a = ("Nome", 13, "Endereco")          //> a  : (String, Int, String) = (Nome,13,Endereco)
	
	println(a._1 + a._2)                      //> Nome13
	
	
	val lists = List(1, 2, 4) :: List.empty :: List(4) :: Nil
                                                  //> lists  : List[List[Int]] = List(List(1, 2, 4), List(), List(4))
	
	for {
		list @ head :: Nil <- lists
	} yield list.size                         //> res0: List[Int] = List(1)
	
	
	val testee = List(1, 2, 3, 4)             //> testee  : List[Int] = List(1, 2, 3, 4)
	
	for {
		number <- testee
		if (number * 3 ==  9)
	}
	yield number                              //> res1: List[Int] = List(3)


	//Anonymous Patterns
	val wordsFrequency = ("Orange", 10) :: ("Strawberry", 5) :: ("Apple", 15) :: ("Melon", 11) :: ("Pineapple", 2) :: Nil
                                                  //> wordsFrequency  : List[(String, Int)] = List((Orange,10), (Strawberry,5), (
                                                  //| Apple,15), (Melon,11), (Pineapple,2))
	
	val predicateQty: ((String, Int)) => Boolean = { case (_, qty) => qty > 3 && qty < 14 }
                                                  //> predicateQty  : ((String, Int)) => Boolean = <function1>
	
  val mapName: ((String, Int)) => String = { case (name, _) => name }
                                                  //> mapName  : ((String, Int)) => String = <function1>
  
  //Partial Functions
  def filtrarFrequenciaPf: PartialFunction[(String, Int), String] = { case (word, freq) if freq > 3 && freq < 14 => word }
                                                  //> filtrarFrequenciaPf: => PartialFunction[(String, Int),String]
   
	def filtrosFrequenciaPalavras(words: Seq[(String, Int)]): Seq[String] =  words.collect(filtrarFrequenciaPf) //words.collect filter(predicateQty).map(mapName)
                                                  //> filtrosFrequenciaPalavras: (words: Seq[(String, Int)])Seq[String]
	
	filtrosFrequenciaPalavras(wordsFrequency) //> res2: Seq[String] = List(Orange, Strawberry, Melon)
	
	
	
}