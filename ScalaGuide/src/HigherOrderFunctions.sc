package com.jovelli.guide

import com.jovelli.guide.HigherOrderFunction._
import com.jovelli.guide._

object HigherOrderFunctions {
   
  val sendBy: EmailFilter = sendByOneOf(Set("Antony", "here", "Marco", "john"))
                                                  //> sendBy  : com.jovelli.guide.HigherOrderFunction.EmailFilter = <function1>
  val notSendBy: EmailFilter = notSendByAnyOf(Set("BuyIt", "SPAM", "Marco", "johaan"))
                                                  //> notSendBy  : com.jovelli.guide.HigherOrderFunction.EmailFilter = <function1>
                                                  //| 
  val max: EmailFilter = maximumSize(30)          //> max  : com.jovelli.guide.HigherOrderFunction.EmailFilter = <function1>
  val min: EmailFilter = minimumSize(12)          //> min  : com.jovelli.guide.HigherOrderFunction.EmailFilter = <function1>
  val block: EmailFilter = blockedContent(Set("card", "money"))
                                                  //> block  : com.jovelli.guide.HigherOrderFunction.EmailFilter = <function1>
  
  val goodEmails = getGoodEmails                  //> goodEmails  : scala.collection.immutable.Seq[com.jovelli.guide.Email] = List
                                                  //| (Email(Happy Birthday,johnJohn@gmail.com,Happy Birthday,me@gmail.com), Email
                                                  //| (here is your new JOB, your opportunity,here@gmail.com,Congratulations!,me@g
                                                  //| mail.com), Email(Congratulations, you won a free weekend,there@gmail.com,Tak
                                                  //| e a rest,me@gmail.com), Email(InterstingTechnolyStuff,overthere@gmail.com,Ne
                                                  //| w gadget,me@gmail.com))
  val unpleasantEmails = getUnpleasantEmails      //> unpleasantEmails  : scala.collection.immutable.Seq[com.jovelli.guide.Email] 
                                                  //| = List(Email(BORROW ME MONEY,John@gmail.com,I need some money, please help,m
                                                  //| e@gmail.com), Email(buy it N,buyit@gmail.com,This is the product of your lif
                                                  //| e,me@gmail.com), Email(SPAM_SPAM_SPAM,spam@gmail.com,I am a SPAM, we serve S
                                                  //| PAM with SPAM and SPAM to drink,me@gmail.com), Email(Here is you card,cardid
                                                  //| idnotrequest@gmail.com,Your card is ready to be used,me@gmail.com))
                                                    
    
	newEmailsForUser(unpleasantEmails, max)   //> res0: Seq[com.jovelli.guide.Email] = List(Email(BORROW ME MONEY,John@gmail.c
                                                  //| om,I need some money, please help,me@gmail.com), Email(Here is you card,card
                                                  //| ididnotrequest@gmail.com,Your card is ready to be used,me@gmail.com))
	
}