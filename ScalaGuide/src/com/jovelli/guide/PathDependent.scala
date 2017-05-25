package com.jovelli.guide

object Franchise {
  case class Character(name: String, franchise: String)
}

class Franchise(name: String) {
  import Franchise.Character
  def createFanFiction(c1: Character, c2: Character): (Character, Character) = {
    require(c1.franchise == c2.franchise)   
    (c1, c2)
  }
 
}