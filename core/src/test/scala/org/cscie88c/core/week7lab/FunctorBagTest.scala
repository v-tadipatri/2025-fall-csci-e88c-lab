package org.cscie88c.core.week7

import org.cscie88c.core.testutils.{ StandardTest }
import scala.util.{Try, Success, Failure}

/**
 * See here for functor concept: https://medium.com/beingprofessional/understanding-functor-and-monad-with-a-bag-of-peanuts-8fa702b3f69e
 * @param content
 * @tparam A
 */
case class FunctorBag[A](content: A) {
 def map[B](f: A => B): FunctorBag[B] = FunctorBag(f(content))
}


/**
 * This is what's in the bag - flour
 * @param weight
 */
case class Flour(weight: Double)

/**
 * These are the set of functions
 */
object FunctionSet {
  // the guy who is expert at making flour half
  def half = (someflour: Flour) => Flour(someflour.weight / 2)

  //the guy who halfs the flour, then puts it in another bag
  def halfBagger = (someflour: Flour) => FunctorBag(Flour(someflour.weight / 2))

}


class FunctorBagTest extends StandardTest {
  //need to import this
  import FunctionSet._

  "Bag Test" should {
    "load some flour" in {
      //get 20 lb flour
      val goldmedal = Flour(20)

      //put it into a "FunctorBag"
      val flour_bag = FunctorBag(goldmedal)
  // map is the guy in our story who can perform operations 
  // by unwrapping the bag and then calling respective function
  // and wraps the content back in a bag
      val half_flour_bag = flour_bag.map(flour => half(flour))
      println(s"Half bagged: ${half_flour_bag}")

      val half_flour_bagged_again = flour_bag.map(flour => halfBagger(flour))
      println(s"Half bagged again: ${half_flour_bagged_again}")
      //because this is just a functor, we can't do anything about the "double bag"

        
    }
  }

}
