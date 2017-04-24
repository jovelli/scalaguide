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
  
  repo.findById(3).map(user => user.age)          //> res0: Option[Int] = Some(30)
  repo.findById(2).map(user => user.gender)       //> res1: Option[Option[String]] = Some(Some(male))
 
 	repo.findById(2).flatMap(user => user.gender)
                                                  //> res2: Option[String] = Some(male)
                                                  
	val names: List[Option[String]] = List(Option("FirstName"), Option("SecondName"), None)
                                                  //> names  : List[Option[String]] = List(Some(FirstName), Some(SecondName), None
                                                  //| )
	names.flatMap(_.map(_.toLowerCase).filter(_.length < 1))
                                                  //> res3: List[String] = List()
	 
	repo.findById(2).filter(_.age > 200)      //> res4: Option[com.jovelli.guide.User] = None
	
	repo.findAll                              //> res5: Iterable[com.jovelli.guide.User] = MapLike(User(1,John,Knox,21,Some(m
                                                  //| ale)), User(2,Jeff,Knox,33,Some(male)), User(3,Johan,Knox,30,Some(female)),
                                                  //|  User(4,Mary,Knox,32,None))
  for {
  	User(_, _, _, _, Some(gender)) <- repo.findAll
  } yield gender                                  //> res6: Iterable[String] = List(male, male, female)
 
  repo.findAll.flatMap(_.gender).toSet            //> res7: scala.collection.immutable.Set[String] = Set(male, female)
  
  List("fdsa", "fdsa", "a").distinct              //> res8: List[String] = List(fdsa, a)
}