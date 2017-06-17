import com.jovelli.guide.Barista

object Actors {
  Barista.sendMessage
  Thread.sleep(2000)                              //> Ok, got it, A great and fresh new Espresso is coming
                                                  //| I'm going to prepare your Cappuccino Sir
                                                  //| I'm going to prepare your Cappuccino Sir
                                                  //| Closing Time, go home and take a rest. Today Cappuccinos: 2 Espressos: 1
                                                  //| Actor[akka://Bar/user/CoffeeCustomer#-686579210] says Caffeine is here for th
                                                  //| e price of 23.5
                                                  //| Actor[akka://Bar/user/CoffeeCustomer#-686579210] says Caffeine is here for th
                                                  //| e price of 23.5|
}