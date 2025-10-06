package org.cscie88c.core.week5lab

import scala.util.Random

object ObjectComplimenter {

  //hide our possible compliments from the outside world!!
  private val compliments = Seq("amazing", "super", "cool", "wonderful", "hip", "gnarly", "friggin_awesome", "friggin_cool")
  private val rnd = new Random()

  //parametric polymorphism here, with the type represented as "A"
  def praise[A] (xs: Seq[A]): String  = {
     val all_ratings = xs.map(x => {
       val compliment = compliments(rnd.nextInt(compliments.length))
       println(s"${x} is so ${compliment}")
       compliment
     })
     all_ratings.length match {
       case 0 =>  "\nNothing to compliment!"
       case _ => "\nOverall rating: "+all_ratings.head
     }

  }

}
