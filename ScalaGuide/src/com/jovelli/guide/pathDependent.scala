package com.jovelli.guide
 
class Franchise(name: String) {
  case class Character(name: String, franchise: String)
  def createFanFiction(c1: Character, c2: Character): (Character, Character) = (c1, c2)
 
} 