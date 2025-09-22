package org.cscie88c.core.week3lab

//need these import statements
import org.cscie88c.core.week3lab.Toppings._
import org.cscie88c.core.week3lab._
import scala.collection.mutable.ListBuffer

/**
 * This is the old fashioned pizzeria
 * It uses bad scala code in an iterative fashion
 */
class OldePizzeria {


    def makeOrderList(numOrders: Int): List[Int] = {
        (1 to numOrders).toList
    }

    def makeCheesePizzaBases(sizes: Seq[String]) = {
        sizes.map(s => PizzaType(CHEESE, s ))
    }

    def deliverOldFashionedPizza(numOrders: Int, sizes: List[String], toppings: List[String]): List[(Int,PizzaType)] = {
         //this is how many orders we got
        val orders = makeOrderList(numOrders)
        //for each of the sizes, make a cheese pizza base of that size
        val pizza_base = makeCheesePizzaBases(sizes)

        //DO NOT DO THIS ON YOUR HOMEWORK ASSIGNMENT !!!!!

        val uglyMutableList = new ListBuffer[(Int, PizzaType)]()

        //your IDE will really dislike this iterative style!!
        for (i <- 0 to orders.size -1){
            for (j <- 0 to pizza_base.size -1){
                for (k <- 0 to toppings.size -1){
                    val myorder = orders(i)
                    val mytopping = toppings(k)
                    val pizza = pizza_base(j).copy(topping = mytopping)
                    //order number and pizza
                    uglyMutableList += ( (myorder,pizza))

                }
            }
        }

        //DO NOT DO THIS ON YOUR HOMEWORK ASSIGNMENT !!!!!

        println ("===start debug")
        
        println ("===end debug")

         uglyMutableList.toList

    }


}
