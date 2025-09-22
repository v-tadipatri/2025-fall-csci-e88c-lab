package org.cscie88c.core.week3lab

import org.cscie88c.core.testutils.{StandardTest}
import org.cscie88c.core.week3lab.Toppings._
import org.cscie88c.core.week3lab.Sizes._

/**
 * We have 2 pizzerias in town.
 * They both take orders for several boxes of pizza, each with a topping
 * So you can place 2 orders, each having 1 small, 1 Large, and 1XL pizza
 * And for each size pizza you can pick how many 1 topping pizzas you want
 * So you would get:
 *   Order 1 :
 *      Small :  Peppers, Olives, Olives
 *      Large :  Peppers, Olives, Olives
 *      XLarge :  Peppers, Olives, Olives
 *   Order 2 :
 *      Small :  Peppers, Olives, Olives
 *      Large :  Peppers, Olives, Olives
 *      XLarge :  Peppers, Olives, Olives
 *
 *  Let's compare the "Pizzerias" - one that uses the old iterative way
 *    and the other which uses the Scala way of doing things
 */
class PizzeriaWordSpecTest extends StandardTest {
  val orders = 2
  val pizza_sizes = Seq(S,L,XL).toList
  val toppings = Seq(PEPPERS, OLIVES,OLIVES).toList
  val expectedNumberOfPizzas = orders * pizza_sizes.size * toppings.size

  /**
     *
     * This is the Old fashioned pizzeria
     *   It uses iteration to loop over all the orders, sizes and toppings
     *
     */

  val oldePizzeria = new OldePizzeria()
  
  /**
    * This is the new Scala Pizzeria
    *   It uses scala constructs to deliver!
    */
  val scalaPizzeria = new ScalaPizzeria()

  val debug = false
  "PizzeriaTest" when {

    "Olde Pizzeria is instantiated" should {
      "deliver pizzas in old fashioned (iterative) way" in {
        val delivery = oldePizzeria.deliverOldFashionedPizza(orders, pizza_sizes, toppings)
        println(s"=== got ${delivery.size} pizzas from olde fashioned pizzeria")
        delivery.size shouldBe (expectedNumberOfPizzas)
        delivery.foreach(printout)

      }
    }

    "Scala Pizzeria is instantiated" should {
      "deliver pizzas in scala way, using flatmap" in {
        val delivery = scalaPizzeria.deliverPizzaWithFlatMap(orders, pizza_sizes, toppings)
        println(s"=== got ${delivery.size} pizzas from scala pizzeria")
        delivery.size shouldBe (expectedNumberOfPizzas)
        delivery.foreach(printout)
      }
      
      "deliver pizzas in scala way, using for construct" in {
        val delivery = scalaPizzeria.deliverPizzaWithForConstruct(orders, pizza_sizes, toppings)
        delivery.size shouldBe (expectedNumberOfPizzas)
        println(s"=== got ${delivery.size} pizzas from scala pizzeria")
        delivery.foreach(printout)
      }


      

    }

    def printout(value: Any): Unit = {
      if (debug){
        println(value)
      }
    }
  }
}
