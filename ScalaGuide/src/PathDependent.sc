import com.jovelli.guide.Franchise

object PathDependent {
	val startTrek = new Franchise("Startrek") //> startTrek  : com.jovelli.guide.Franchise = com.jovelli.guide.Franchise@59363
                                                  //| 4ad
  val starWars = new Franchise("Starwars")        //> starWars  : com.jovelli.guide.Franchise = com.jovelli.guide.Franchise@39ba5a
                                                  //| 14
  
  val yoda = new Franchise.Character("Yoda", "Star Wars")
                                                  //> yoda  : com.jovelli.guide.Franchise.Character = Character(Yoda,Star Wars)
  val luke = new Franchise.Character("Luck Skywalker", "Star Wars")
                                                  //> luke  : com.jovelli.guide.Franchise.Character = Character(Luck Skywalker,Sta
                                                  //| r Wars)
  
  val drSpock = new Franchise.Character("Dr. Spock", "Star Trek")
                                                  //> drSpock  : com.jovelli.guide.Franchise.Character = Character(Dr. Spock,Star 
                                                  //| Trek)
  val quark = new Franchise.Character("Quark", "Star Trek")
                                                  //> quark  : com.jovelli.guide.Franchise.Character = Character(Quark,Star Trek)

	startTrek.createFanFiction(drSpock, yoda) //> java.lang.IllegalArgumentException: requirement failed
                                                  //| 	at scala.Predef$.require(Predef.scala:212)
                                                  //| 	at com.jovelli.guide.Franchise.createFanFiction(PathDependent.scala:10)
                                                  //| 	at PathDependent$$anonfun$main$1.apply$mcV$sp(PathDependent.scala:13)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at PathDependent$.main(PathDependent.scala:3)
                                                  //| 	at PathDependent.main(PathDependent.scala)
}