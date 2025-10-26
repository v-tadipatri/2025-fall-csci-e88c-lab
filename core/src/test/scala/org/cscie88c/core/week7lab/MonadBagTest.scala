package org.cscie88c.core.week7

import org.cscie88c.core.testutils.{ StandardTest }
import scala.util.{Try, Success, Failure}

/**
 * See here for monad concept: https://medium.com/beingprofessional/understanding-functor-and-monad-with-a-bag-of-peanuts-8fa702b3f69e
 * Also here: https://www.adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html
 * @param content
 * @tparam A
 */
case class MonadBag[A](content: A) {
 def map[B](f: A => B):         MonadBag[B] = MonadBag(f(content))

 def flatMap[B](f: A => MonadBag[B]):       MonadBag[B] = f(content)

 def flatten = content


}

/**
 * This is what's in the bag - sugar
 * @param weight
 */
case class Sugar(weight: Double)

object MoreFunctions {
  // the guy who is expert at adding 2 to sugar
  def add2 = (sugar: Sugar) => Sugar(sugar.weight + 2)

  //the guy who add2 the sugar, then puts it in another bag
  def add2Bagger = (sugar: Sugar) => MonadBag(add2(sugar))

}

class MonadBagTest extends StandardTest {
  import MoreFunctions._


  "Bag Test" should {
    "load some sugar" in {
      //get 20 lb dominos
      val dominos = Sugar(20)

      //put it into a "MonadBag"
      val sugar_bag = MonadBag(dominos)
  // map is the guy in our story who can perform operations 
  // by unwrapping the bag and then calling respective function
  // and wraps the content back in a bag
      val add2_sugar_bag = sugar_bag
                              .map(sugar => add2(sugar))
      println(s"add2 bagged: ${add2_sugar_bag}")

      val add2_sugar_bagged_again = sugar_bag
                                      .map(sugar => add2Bagger(sugar))
      println(s"add2 bagged again: ${add2_sugar_bagged_again}")

      
      //now flatten it
      val add2_sugar_bagged_again_flattened = sugar_bag
                                                .map(sugar => add2Bagger(sugar))
                                                .flatten
      println(s"add2 bagged again, then flattened: ${add2_sugar_bagged_again_flattened}")

      //this is easier
      val add2_sugar_bagged_again_flatmap = sugar_bag
                                                .flatMap(sugar => add2Bagger(sugar))
      println(s"add2 bagged again, then flatmap: ${add2_sugar_bagged_again_flatmap}")

    }
  }

}
