package com.jovelli.guide

object Math {
  import annotation.implicitNotFound
  
  @implicitNotFound("This type is not available ${T}")
  trait NumberLike[T] {
    def plus(x: T, y: T): T
    def minus(x: T, y: T) : T
    def divide(x: T, y: Int): T
  }
  
  object NumberLike {
    implicit object NumberLikeInt extends NumberLike[Int] {
      def plus(x: Int, y: Int): Int = x + y
      def minus(x: Int, y: Int): Int = x - y
      def divide(x: Int, y: Int): Int = x / y
    }
    
    implicit object NumberLikeDouble extends NumberLike[Double] {
      def plus(x: Double, y: Double): Double = x + y
      def minus(x: Double, y: Double): Double = x - y
      def divide(x: Double, y: Int): Double = x / y
    }
  }
}

object Statistics {
  import Math.NumberLike
  
  def median[T: NumberLike](lst: Vector[T]): T = lst(lst.size / 2)
  
  def quartil[T: NumberLike](lst: Vector[T]): (T, T, T) = (lst(lst.size / 4), median(lst), lst(lst.size - ((lst.size / 4) + 1)))
  
  def iqr[T: NumberLike](lst: Vector[T]): T = quartil(lst) match {
    case (menorQuartil, _, maiorQuartil) => implicitly[NumberLike[T]].minus(maiorQuartil, menorQuartil)  
  }
  
  def media[T](lst: Vector[T])(implicit ev: NumberLike[T]): T = {
    ev.divide(lst.reduce(ev.plus(_, _)), lst.size)
  }
}
