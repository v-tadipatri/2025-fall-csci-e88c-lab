package org.cscie88c.core.week7lab

import org.cscie88c.core.testutils.StandardTest

import scala.util.{Failure, Success, Try}

class TryOptionTest   extends StandardTest {

  //Here's my multiply function
  def multiplier(x: Int) = x * 5


  "Try option" should {
    "parse values" in {
      val optInt = Option(2)
      val outputInt = optInt.map( multiplier(_))
      println(s"Got integer output : ${outputInt}")

    }

    "parse empty values with extra code" in {
      val emptyInt = Option.empty[Int]
      val outputEmpty = if (emptyInt.isEmpty){
         Option.empty[Int]
      }else{
        emptyInt.map(multiplier(_))
      }
      println(s"Got empty output with bulky code: ${outputEmpty}")
    }

    "parse empty values nicely" in {
      val emptyInt = Option.empty[Int]
      val outputEmpty = emptyInt.map(multiplier(_))
      println(s"Got empty output nicely: ${outputEmpty}")
    }
    "try dividing by 0 with bulky code" in {
      val test = Try(15 / 0)
      val result = if (test.isFailure == false){
        Option(test.get)
      }else{
        Option.empty
      }
      println(s"Finally - got my result from div by 0 : ${result}")

    }

    "try dividing by 0" in {
      val optlist = Seq(Option(5), Option(2), Option.empty[Int])
      //optlist.map(x => println(s"Processing ${x}"))
      optlist(1).map(x => println(s"option = ${x}"))
      optlist(2).map(x => println(s"option = ${x}"))
      val result_div2 = Try( 15 / 2).map( x => println(s"Div by 2: ${x}"))
      Try(15/0) match {
        case Success(value) => println("success" + value)
        case Failure(err) => println("failure "+ err)
      }
      println(s"Got result after div by 2 : ${result_div2}")
      //this works because it's a functor
      //note that println never happens
      val result_div0 = Try( 15 / 0).map( x => println(s"Div by 0: ${x}"))
      println(s"Got result after div by 0 : ${result_div0}")
      //try is also a monad
      val result_div0_flatten = Try( 15 / 0).flatMap( x => {println(s"Div by 0: ${x}"); Try(x)})
      println(s"Got result after div by 0 : ${result_div0_flatten}")

    }

  }


}
