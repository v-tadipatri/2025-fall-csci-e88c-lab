package org.cscie88c.core.week3lab

//need these import statements
import org.cscie88c.core.week3lab.Toppings._
import org.cscie88c.core.week3lab.Sizes._
import org.cscie88c.core.week3lab._

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
        //for each of the sizes of pizza , make a cheese pizza of that size
        sizes.map(s => PizzaType( s, CHEESE ))
    }

    def deliverPizzaWithFlatMap(numOrders: Int, sizes: List[String], toppings: List[String]): List[(Int,PizzaType)] = {
         //this is how many orders we got
        val orders = makeOrderList(numOrders)
        //for each of the sizes, make a cheese pizza base of that size
        val pizza_base = makeCheesePizzaBases(sizes)

        //call flatmap except for innermost, which is a map
        //this is complicated syntax to figure out...
        val deliveries = orders.flatMap(o => {
            pizza_base.flatMap(p => {
                toppings.map( t=> {
                    //this is a tuple that is returned
                    (o,p.copy(topping = t))
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
        //a for construct, NOT a for loop like in other languages
        val deliveries = for {
            o <- orders
            p <- pizza_base
            t <- toppings
            //if t != ANCHOVIES
        } yield (o, p.copy(topping = t))

        deliveries

    }

}
