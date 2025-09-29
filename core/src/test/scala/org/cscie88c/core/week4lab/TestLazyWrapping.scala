package org.cscie88c.core.week4lab

import org.cscie88c.core.testutils.StandardTest

import scala.collection.mutable.ListBuffer

class TestLazyWrapping extends StandardTest {


    "Wrapping facility " when {
        "asked to mutate lists" should {

            "be active in checking presents" in {
                val presentsNum: Seq[String] = (1 to 5).toList.map(g => {
                    println("Opening present now...")
                    "0" + g
                })
                println("Immediate: Now we will see what's in the presents")
                presentsNum.foreach(println)

            }
            "be lazy in checking presents" in {
                lazy val lazyPresentsNum: Seq[String] = (1 to 5).toList.map(g => {
                    println("Wait to open present...")
                    "*0" + g
                })
                println("Lazy: Now we will see what's in the presents")
                lazyPresentsNum.foreach(println)
            }

        }

    }
  
}
