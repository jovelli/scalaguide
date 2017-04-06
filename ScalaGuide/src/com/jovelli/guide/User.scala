package com.jovelli.guide

trait User {
  def name: String
  def score: Int
}

class FreeUser(val name: String, val score: Int, val upgradeProbability: Double) extends User

object FreeUser {
  def unapply(user: FreeUser): Option[(String, Int, Double)] = 
    Some(user.name, user.score, user.upgradeProbability) 
}

class PremiumUser(val name: String, val score: Int) extends User

object PremiumUser {
  def unapply(user: PremiumUser): Option[(String, Int)] = 
    Some(user.name, user.score)
}

object GivenName {
  def unapplySeq(obj: String): Option[Seq[String]] = {
    val names = obj.trim.split(" ")
    if (names.forall(_.isEmpty)) None else Some(names)
  }
}

object GivenMoreNames {
  def unapplySeq(obj: String): Option[(String, String, Seq[String])] = {
    val names = obj.trim.split(" ")
    if (names.size < 2) None
    else Some((names.head, names.last, names.drop(1).dropRight(1)))
  }
}