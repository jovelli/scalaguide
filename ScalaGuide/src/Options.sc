package com.jovelli

import com.jovelli.guide.User;
import com.jovelli.guide.{ UserRepository => repo }

object Options {
  val user = User(2, "Leila", "Loe", 31, None)    //> user  : com.jovelli.guide.User = User(2,Leila,Loe,31,None)
  val user2 = repo.findById(1)                    //> user2  : Option[com.jovelli.guide.User] = Some(User(1,John,Knox,21,Some(male
                                                  //| )))
  
  println("User Gender " + user.gender.getOrElse("No Gender"))
                                                  //> User Gender No Gender
  println("User2 Gender " + user2.get.gender.getOrElse("No Gender"))
                                                  //> User2 Gender male
  
  val gender2 = user2.get match {
  	case User(_, _, _, _, Some(gender)) => gender
  	case User(_, _, _, _, None) => "No Gender"
  }                                               //> gender2  : String = male
  
  val gender = user.gender match {
  	case Some(gender) => gender
  	case None => "No Gender"
  }                                               //> gender  : String = No Gender
  
  // Returns None from findById(30), so expression inside foreach is never called
  repo.findById(30).foreach(user => println(user.gender))
 	 
}