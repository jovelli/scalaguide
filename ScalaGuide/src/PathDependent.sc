import com.jovelli.guide.Franchise

object PathDependent {
	val startTrek = new Franchise("Startrek") //> startTrek  : com.jovelli.guide.Franchise = com.jovelli.guide.Franchise@59363
                                                  //| 4ad
  val starWars = new Franchise("Starwars")        //> starWars  : com.jovelli.guide.Franchise = com.jovelli.guide.Franchise@39ba5a
                                                  //| 14
  
  val yoda = new starWars.Character("Yoda", "Star Wars")
                                                  //> yoda  : PathDependent.starWars.Character = Character(Yoda,Star Wars)
  val luke = new starWars.Character("Luck Skywalker", "Star Wars")
                                                  //> luke  : PathDependent.starWars.Character = Character(Luck Skywalker,Star War
                                                  //| s)
  
  val drSpock = new startTrek.Character("Dr. Spock", "Star Trek")
                                                  //> drSpock  : PathDependent.startTrek.Character = Character(Dr. Spock,Star Trek
                                                  //| )
  val quark = new startTrek.Character("Quark", "Star Trek")
                                                  //> quark  : PathDependent.startTrek.Character = Character(Quark,Star Trek)
 
	startTrek.createFanFiction(drSpock, drSpock)
                                                  //> res0: (PathDependent.startTrek.Character, PathDependent.startTrek.Character)
                                                  //|  = (Character(Dr. Spock,Star Trek),Character(Dr. Spock,Star Trek))
}