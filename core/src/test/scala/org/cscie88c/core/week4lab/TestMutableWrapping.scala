package org.cscie88c.core.week4lab

import org.cscie88c.core.testutils.StandardTest

import scala.collection.mutable.ListBuffer

class TestMutableWrapping extends StandardTest {
    val presentsNum: Seq[String] = (1 to 5).toList.map("0" + _)


    "Wrapping facility " when {
        "asked to mutate lists" should {

            "add more presents" in {
                //try different ways of wrapping gifts
                //presentsNum += "07"
                val mutablePresents = ListBuffer(presentsNum : _*)
                mutablePresents should not contain("007")
                println(s"before: ${mutablePresents}")
                mutablePresents +="007"
                mutablePresents +="008"
                mutablePresents +="009"
                println(s"after: ${mutablePresents}")
                mutablePresents should contain("007")

            }

        }

    }
  
}
