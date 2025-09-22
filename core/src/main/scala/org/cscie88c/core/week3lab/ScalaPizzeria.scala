package org.cscie88c.week3lab

//need these import statements
import org.cscie88c.week3lab.Toppings._
import org.cscie88c.week3lab.Sizes._
import org.cscie88c.week3lab._

/**
 * This Pizzeria delivers in 2 different ways
 *    - using flatmap followed by a map
 *    - using for construct
 */
class ScalaPizzeria {
  

    def makeOrderList(numOrders: Int): List[Int] = {
        (1 to numOrders).toList
    }

    def makeCheesePizzaBases(sizes: Seq[String]) = {
        sizes.map(s => PizzaType(CHEESE, s ))
    }

    def deliverPizzaWithFlatMap(numOrders: Int, sizes: List[String], toppings: List[String]): List[(Int,PizzaType)] = {
         //this is how many orders we got
        val orders = makeOrderList(numOrders)
        //for each of the sizes, make a cheese pizza base of that size
        val pizza_base = makeCheesePizzaBases(sizes)

        //call flatmap except for innermost, which is a map
        val deliveries = orders.flatMap(o => {
            pizza_base.flatMap(p => {
                toppings.map( t=> {
                    (o,p.copy(t))
                })
            })
        })
        deliveries

    }

    def deliverPizzaWithForConstruct(numOrders: Int, sizes: List[String], toppings: List[String]): List[(Int, PizzaType)] = {
        //this is how many orders we got
        val orders = makeOrderList(numOrders)
        //for each of the sizes, make a cheese pizza base of that size
        val pizza_base = makeCheesePizzaBases(sizes)

        //use braces here, not parentheses
        val deliveries = for {
            o <- orders
            p <- pizza_base
            t <- toppings
        } yield (o, p.copy(topping = t))

        deliveries

    }

}
