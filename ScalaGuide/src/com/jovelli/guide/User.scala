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
