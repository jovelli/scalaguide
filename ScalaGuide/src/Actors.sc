import com.jovelli.guide.Barista

object Actors {
  Barista.sendMessage                             //> [INFO] [06/24/2017 19:13:07.647] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/Barista] I'm going to prepare your Cappuccino Sir
                                                  //| [INFO] [06/24/2017 19:13:07.648] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/Barista] I'm going to prepare your Cappuccino Sir
                                                  //| [INFO] [06/24/2017 19:13:07.649] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/Barista] Ok, got it, A great and fresh new Espresso is coming
                                                  //| [INFO] [06/24/2017 19:13:07.649] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/Barista] Ok, got it, A great and fresh new Espresso is coming
                                                  //| [INFO] [06/24/2017 19:13:07.649] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/Barista] Ok, got it, A great and fresh new Espresso is coming
                                                  //| [WARN] [06/24/2017 19:13:07.652] [Bar-akka.actor.default-dispatcher-10] [akka
                                                  //| ://Bar/user/Barista/Register/BillPrinter] Not again
                                                  //| [WARN] [06/24/2017 19:13:07.657] [Bar-akka.actor.default-dispatcher-7] [akka:
                                                  //| //Bar/user/Barista/Register/BillPrinter] Not again
                                                  //| [INFO] [06/24/2017 19:13:07.660] [Bar-akka.actor.default-dispatcher-9] [akka:
                                                  //| //Bar/user/Barista/Register] Partial Revenue is 23.5
                                                  //| [INFO] [06/24/2017 19:13:07.660] [Bar-akka.actor.default-dispatcher-9] [akka:
                                                  //| //Bar/user/Barista/Register] Partial Revenue is 43.5
                                                  //| [INFO] [06/24/2017 19:13:07.660] [Bar-akka.actor.default-dispatcher-9] [akka:
                                                  //| //Bar/user/Barista/Register] Partial Revenue is 63.5
                                                  //| [INFO] [06/24/2017 19:13:07.661] [Bar-akka.actor.default-dispatcher-9] [akka:
                                                  //| //Bar/user/Jeff] Actor[akka://Bar/user/Jeff#-746673090] says Caffeine is here
                                                  //|  for the price of 20.0
                                                  //| [INFO] [06/24/2017 19:13:07.661] [Bar-akka.actor.default-dispatcher-7] [akka:
                                                  //| //Bar/user/John] Actor[akka://Bar/user/John#-575992596] says Caffeine is here
                                                  //|  for the price of 20.0
                                                  //| [INFO] [06/24/2017 19:13:07.662] [Bar-akka.actor.default-dispatcher-10] [akka
                                                  //| ://Bar/user/Mary] Actor[akka://Bar/user/Mary#270063091] says Caffeine is here
                                                  //|  for the price of 23.5
                                                  //| [INFO] [06/24/2017 19:13:11.662] [Bar-akka.actor.default-dispatcher-5] [akka:
                                                  //| //Bar/user/Jeff] What ? Was it not possible to prepare my beverage ???
                                                  //| [INFO] [06/24/2017 19:13:11.662] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/John] What ? Was it not possible to prepare my beverage ???
                                                  //| [INFO] [06/24/2017 19:13:11.980] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/Barista] I'm going to prepare your Cappuccino Sir
                                                  //| [INFO] [06/24/2017 19:13:11.981] [Bar-akka.actor.default-dispatcher-5] [akka:
                                                  //| //Bar/user/Barista/Register] Partial Revenue is 87.0
                                                  //| [INFO] [06/24/2017 19:13:11.980] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/Barista] Ok, got it, A great and fresh new Espresso is coming
                                                  //| [INFO] [06/24/2017 19:13:11.983] [Bar-akka.actor.default-dispatcher-5] [akka:
                                                  //| //Bar/user/John] Actor[akka://Bar/user/John#-575992596] says Caffeine is here
                                                  //|  for the price of 23.5
                                                  //| [INFO] [06/24/2017 19:13:11.984] [Bar-akka.actor.default-dispatcher-7] [akka:
                                                  //| //Bar/user/Barista/Register] Partial Revenue is 107.0
                                                  //| [INFO] [06/24/2017 19:13:11.984] [Bar-akka.actor.default-dispatcher-13] [akka
                                                  //| ://Bar/user/Jeff] Actor[akka://Bar/user/Jeff#-746673090] says Caffeine is her
                                                  //| e for the price of 20.0
  Thread.sleep(1000)                              //> [INFO] [06/24/2017 19:13:23.363] [Bar-akka.actor.default-dispatcher-6] [akka:
                                                  //| //Bar/user/Barista] Closing Time, go home and take a rest. Revenue Today 107.
                                                  //| 0. Cappuccinos: 2 Espressos: 3|
}