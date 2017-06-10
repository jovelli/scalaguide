package com.jovelli.guide

case class User(id: Int, name: String, lastName: String, age: Int, gender: Option[String])

object UserRepository {
  private val users = Map(1 -> User(1, "John", "Knox", 21, Some("male")), 
      2 -> User(2, "Jeff", "Knox", 33, Some("male")),
      3 -> User(3, "Johan", "Knox", 30, Some("female")), 
      4 -> User(4, "Mary", "Knox", 32, None))
      
  def findById(id: Int): Option[User] = users.get(id)
  
  def findAll = users.values
}