package org.cscie88c.core.week3lab

import org.scalatest.funsuite.AnyFunSuite
import org.scalacheck._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.should.Matchers
import org.cscie88c.core.week3lab.Toppings._
import org.cscie88c.core.week3lab.Sizes._

/**
  * This is an example of the "Function suite" style of writing tests
  * We want to use Property based testing along with generators
  *   This will ensure that no matter what kind of pizzas get delivered,
  *   they will not contain any anchovies 
  *
  */
class PizzaToppingFunSuite extends AnyFunSuite with ScalaCheckPropertyChecks with Matchers{
    val scalaPizzeria = new ScalaPizzeria()

   test("Scala Pizzeria's pizzas should not have fishy toppings") {
        //Scalacheck Gen can pick one of a certainvalues
        val randomOrders = Gen.choose(1,5)
        val pizzaSizeGen = Gen.oneOf(S,M,L,XL)
        
        val toppingsGen = Gen.oneOf(OLIVES, PEPPERS, /*ANCHOVIES, */ CHEESE)

        //now we build lists of the sizes and toppings
        val randomSizes = Gen.listOf(pizzaSizeGen)
        val randomToppings = Gen.listOf(toppingsGen)

        //use for construct to generate a tuple
        //we want to use what this generates as the params for delivery
        val tupleGen: Gen[(Int, List[String], List[String])] =  for {
          order <- randomOrders
          pizzaSize <- randomSizes
          topping <- randomToppings

        }yield  (order, pizzaSize, topping)

        //for anything the tupleGen produces, any kind of order that's produced
        //make sure that even if customer placed order for ANCHOVIES, we don't deliver that
        forAll(tupleGen) {
          (tuple) => {
            val delivery = scalaPizzeria.deliverPizzaWithForConstruct(tuple._1, tuple._2, tuple._3)
            //note the functional assertion style
            assert(delivery.map(_._2.topping).toArray.contains (ANCHOVIES)==false, "We don't want fish on our pizza!")
            //delivery.map(_._2.topping).toArray.contains (ANCHOVIES)==false
          }
        }        



    } 
}
