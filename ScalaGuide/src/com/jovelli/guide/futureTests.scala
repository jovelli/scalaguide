package com.jovelli.guide

import scala.util.Try
import scala.util.Random
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Water(temperature: Int)
case class Cappuccino(status: String)
case class Milk(tipo: String)
case class CoffeeBeans(name: String)
case class Espresso(status: String)
case class FrothedMilk(leite: String)
case class GroundCoffee(status: String)

case class SelecaoException(msg: String) extends Exception(msg)
case class LeiteQuenteException(msg: String) extends Exception(msg)
case class AguaQuenteException(msg: String) extends Exception(msg)
case class FermentoException(msg: String) extends Exception(msg)

object FutureTests {
  def selecionar(grao: CoffeeBeans): Future[GroundCoffee] = Future {
    println("inicio selecionando cafe")
    if (grao.name.equals("caboclo")) throw SelecaoException("No way for this brand") 
    println("termino selecionando cafe")
    GroundCoffee(s"selecionado grao de cafe ${grao}")
  }

  def esquentarAgua(agua: Water): Future[Water] = Future {
    println("inicio esquentando agua")
    println("hot, it's hot")
    agua.copy(temperature = 100)
  }
  
  def esquentarLeite(leite: Milk): Future[FrothedMilk] =  Future {
    println("inicio leite fermentando")
    println("termino leite fermentando")
    FrothedMilk(s"Leite {leite} esquentado")
  }
  
  def fermentar(cafe: Future[GroundCoffee], agua: Water): Future[Espresso] = Future {
    println("inicio cafe fermentando")
    println("termino cafe fermentando")
    Espresso("espresso")
  }
  
  def prepareCooffee(cafe: Future[GroundCoffee]): Future[Espresso] = {
    esquentarAgua(Water(35)).flatMap(water => fermentar(cafe, water))
  }
  
  def combine(espresso: Future[Espresso], leiteFermentado: Future[FrothedMilk]): Cappuccino = Cappuccino("DONE")
  
  def prepareCappuccino(): Try[Cappuccino] = {
    val leiteQuente = Try(esquentarLeite(Milk("integral")))
    
    for {
      cafeSelecionado <- Try(selecionar(CoffeeBeans("cafe pele")))
      espresso <- Try(prepareCooffee(cafeSelecionado))
      leite <- leiteQuente
    } yield combine (espresso, leite)
  }
}
















