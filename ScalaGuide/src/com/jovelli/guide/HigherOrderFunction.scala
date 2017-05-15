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
    ("RECEIVE", Email("InterstingTechnolyStuff", "overthere@gmail.com", "New gadget", "me@gmail.com")),
    ("RECEIVE", Email("", "haveto@gmail.com", "Bad Email, but inevitable to receive. Here we have a BAD_WORD to u", "me@gmail.com"))
  )
  
  def complement[A](predicate: (A => Boolean)) = (a: A) => !predicate(a)
  def any[A](predicates: (A => Boolean)*): A => Boolean = a => predicates.exists(pred => pred(a))
  def none[A](predicates: (A => Boolean)*) = complement(any(predicates: _*))
  def every[A](predicates: (A => Boolean)*) = none(predicates.view.map(complement(_)): _*)
  
  def getGoodEmails = emailList.filter(_._1.equals("RECEIVE")).map(_._2).toSeq
  
  def getUnpleasantEmails = emailList.filter(_._1.equals("BLOCK")).map(_._2).toSeq
  
  def newEmailsForUser(mails: Seq[Email], filter: EmailFilter): Seq[Email] = { 
    mails.filter(filter) 
  } 
    
  val senderCheck: (Set[String], Email) => Boolean = (senders, email) => senders.exists(s => email.sender.toLowerCase.contains(s.toLowerCase)) 
  val sendByOneOf: Set[String] => EmailFilter = senders => email => senderCheck(senders, email) 
  val notSendByAnyOf: Set[String] => EmailFilter = senders => email => !senderCheck(senders, email)
  val blockedContent: Set[String] => EmailFilter = blockedTerms => email => !blockedTerms.exists(term => email.text.contains(term))
  
  val sizeConstraintF: SizeChecker => EmailFilter = f => email => f(email.text.size)   
  val minimumSize: Int => EmailFilter = min => sizeConstraintF(_ >= min)
  val maximumSize: Int => EmailFilter = max => sizeConstraintF(_ <= max)
  
  //transformation pipeline
  val addMissingSubject = (email: Email) => if (email.subject.isEmpty()) email.copy(subject = "No Subject") else email
  val checkSpelling = (email: Email) => email.copy(text = email.text.replaceAll(" u ", "you"))
  val removeInappropriateLanguage = (email: Email) => email.copy(text = email.text.replaceAll("BAD_WORD", "***"))
  val AddAdsFooter = (email: Email) => email.copy(text = email.text + "\nThis email came from EmailSender App :)")  
  
  val pipeline = Function.chain(Seq(addMissingSubject, checkSpelling, removeInappropriateLanguage, AddAdsFooter))
  
  //Partially applied Functions
  type DoubleIntPred = (Int, Int) => Boolean
  def sizeConstraint(predicate: DoubleIntPred, email: Email, n: Int) = predicate(email.text.size, n)
  
  val gt: DoubleIntPred = _ > _
  val ge: DoubleIntPred = _ >= _
  val lt: DoubleIntPred = _ < _
  val le: DoubleIntPred = _ <= _
  val eq: DoubleIntPred = _ == _ 
  
  val max20: EmailFilter = fixedSize20Constraint(gt, _: Email)
  val min20: EmailFilter = fixedSize20Constraint(lt, _: Email)
  val variableSize: (DoubleIntPred, Int) => EmailFilter = (d, n) => email => sizeConstraint(d, email, n)
  
  val fixedSize20Constraint: (DoubleIntPred, Email) => Boolean = sizeConstraint(_: DoubleIntPred, _: Email, 20) //sizeConstraint _
  
  
}