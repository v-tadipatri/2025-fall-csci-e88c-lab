package org.cscie88c.core.week4lab

import org.cscie88c.core.testutils.StandardTest

import scala.collection.mutable.ListBuffer

class TestFancyGiftWrapping extends StandardTest {
    val wrappingFacility = new GiftWrappingFacility
    val presentsNum: Seq[String] = (1 to 5).toList.map("0" + _)

    //this function puts parentheses
    val parenthesesWrap :   (String, String)  => String     = (s1: String, s2: String) =>  {
        s1 + s"(${s2})"
    }

    "Wrapping facility " when {
        "asked to wrap presents in fancy ways" should {

            "wrap like usual" in {
                //try different ways of wrapping gifts
                val result = wrappingFacility.wrapGifts(presentsNum, parenthesesWrap)
                println(s"foldLeft result:  ${result}")
                result shouldBe ".(01)(02)(03)(04)(05)"
            }
            "wrap backwards" in {
                //try different ways of wrapping gifts
                val result = wrappingFacility.wrapGiftsBackwards(presentsNum, parenthesesWrap)
                println(s"foldRight result:  ${result}")
            }

            "watch wrapping forwards" in {
                //try different ways of wrapping gifts
                val result = wrappingFacility.watchGiftsGetWrapped(presentsNum, parenthesesWrap)
                val result_arr = result.mkString("Array(", ", ", ")")
                println(s"scanLeft result:  ${result_arr}")
            }
            "watch wrapping backwards" in {
                //try different ways of wrapping gifts
                val result = wrappingFacility.watchGiftsGetWrappedBackwards(presentsNum, parenthesesWrap)
                val result_arr = result.mkString("Array(", ", ", ")")
                println(s"scanRight result:  ${result_arr}")
            }
        }

    }
  
}
