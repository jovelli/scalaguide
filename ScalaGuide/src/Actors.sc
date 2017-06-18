import com.jovelli.guide.Barista

object Actors {
  Barista.sendMessage
  Thread.sleep(15000)                             //> I'm going to prepare your Cappuccino Sir
                                                  //| Ok, got it, A great and fresh new Espresso is coming
                                                  //| I'm going to prepare your Cappuccino Sir
                                                  //| [INFO] [06/18/2017 18:35:50.245] [Bar-akka.actor.default-dispatcher-3] [akka:
                                                  //| //Bar/user/Barista/Register] Partial Revenue is 23.5
                                                  //| [WARN] [06/18/2017 18:35:50.252] [Bar-akka.actor.default-dispatcher-5] [akka:
                                                  //| //Bar/user/Barista/Register] Not again...
                                                  //| [INFO] [06/18/2017 18:35:50.253] [Bar-akka.actor.default-dispatcher-3] [akka:
                                                  //| //Bar/user/Barista/Register] Partial Revenue is 43.5
                                                  //| [WARN] [06/18/2017 18:35:50.255] [Bar-akka.actor.default-dispatcher-5] [akka:
                                                  //| //Bar/user/Barista/Register] Not again...
                                                  //| [INFO] [06/18/2017 18:35:50.255] [Bar-akka.actor.default-dispatcher-3] [akka:
                                                  //| //Bar/user/Barista/Register] Partial Revenue is 67.0
                                                  //| [WARN] [06/18/2017 18:35:50.255] [Bar-akka.actor.default-dispatcher-5] [akka:
                                                  //| //Bar/user/Barista/Register] Not again...
                                                  //| Closing Time, go home and take a rest. Revenue Today 67.0. Cappuccinos: 2 Esp
                                                  //| ressos: 1
                                                  //| [INFO] [06/18/2017 18:35:50.384] [Bar-akka.actor.default-dispatcher-6] [akka:
                                                  //| //Bar/user/Mary] Actor[akka://Bar/user/Mary#-836076365] says Caffeine is here
                                                  //|  for the price of 23.5
                                                  //| [INFO] [06/18/2017 18:35:50.384] [Bar-akka.actor.default-dispatcher-2] [akka:
                                                  //| //Bar/user/John] Actor[akka://Bar/user/John#-86667481] says Caffeine is here 
                                                  //| for the price of 23.5\
}