package com.jovelli.guide

case class Email ( 
    subject: String,
    sender: String,
    text: String,
    recipient: String 
)
    
object HigherOrderFunction {
  type EmailFilter = Email => Boolean
  type SizeChecker = Int => Boolean
  
  private val emailList = List(("BLOCK", Email("BORROW ME MONEY", "John@gmail.com", "I need some money, please help", "me@gmail.com")),
    ("BLOCK", Email("buy it N", "buyit@gmail.com", "This is the product of your life", "me@gmail.com")),
    ("BLOCK", Email("SPAM_SPAM_SPAM", "spam@gmail.com", "I am a SPAM, we serve SPAM with SPAM and SPAM to drink", "me@gmail.com")),
    ("BLOCK", Email("Here is you card", "cardididnotrequest@gmail.com", "Your card is ready to be used", "me@gmail.com")),
    ("RECEIVE", Email("Happy Birthday", "johnJohn@gmail.com", "Happy Birthday", "me@gmail.com")),
    ("RECEIVE", Email("here is your new JOB, your opportunity", "here@gmail.com", "Congratulations!", "me@gmail.com")),
    ("RECEIVE", Email("Congratulations, you won a free weekend", "there@gmail.com", "Take a rest", "me@gmail.com")),
    ("RECEIVE", Email("InterstingTechnolyStuff", "overthere@gmail.com", "New gadget", "me@gmail.com"))
  )
  
  def getGoodEmails = emailList.filter(_._1.equals("RECEIVE")).map(_._2).toSeq
  
  def getUnpleasantEmails = emailList.filter(_._1.equals("BLOCK")).map(_._2).toSeq
  
  def newEmailsForUser(mails: Seq[Email], filter: EmailFilter): Seq[Email] = { 
    mails.filter(filter) 
  } 
    
  val sendersCheck: (Set[String], Email) => Boolean = (senders, email) => senders.exists(s => email.sender.toLowerCase.contains(s.toLowerCase))
  val sendByOneOf: Set[String] => EmailFilter = senders => email => sendersCheck(senders, email)
  val notSendByAnyOf: Set[String] => EmailFilter = senders => email => !sendersCheck(senders, email)
  
  val blockedContent: Set[String] => EmailFilter = blockedTerms => email => !blockedTerms.exists(term => email.text.contains(term))
  
  val sizeConstraint: SizeChecker => EmailFilter = f => email => f(email.text.size)   
  val minimumSize: Int => EmailFilter = min => sizeConstraint(_ >= min)
  val maximumSize: Int => EmailFilter = max => sizeConstraint(_ <= max)
  
}