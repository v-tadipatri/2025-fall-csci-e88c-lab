package org.cscie88c.week4lab

import org.cscie88c.testutils.{StandardTest}

class TestGiftWrapping extends StandardTest {
    val wrappingFacility = new GiftWrappingFacility
    val presentsNum: Seq[String] = (1 to 5).toList.map("0" + _)

    "Wrapping facility " when {
        "asked to wrap presents in a normal way" should {

            "handle numeric Strings with parentheses" in {
                //this function puts parentheses
                val parenthesesWrap :   (String, String)  => String     = (s1: String, s2: String) =>  {
                    s1 + s"(${s2})"
                }
                //try different ways of wrapping gifts
                val result = wrappingFacility.wrapGifts(presentsNum, parenthesesWrap)
                println(s"parentheses result:  ${result}")
                result shouldBe ".(01)(02)(03)(04)(05)"
            }

            "handle numeric Strings with stars" in {
                //this is a different function we will pass
                val starWrap :   (String, String)  => String     = (s1: String, s2: String) =>  {
                    s1 + s" **| ${s2}"
                }
                val result = wrappingFacility.wrapGifts(presentsNum, starWrap)
                println(s"starred result: ${result}")
                result shouldBe ". **| 01 **| 02 **| 03 **| 04 **| 05"
            }


            "handle numeric Strings with stars as a pure function" in {
                //this is a different function we will pass
                val starWrap :   (String, String)  => String     = (s1: String, s2: String) =>  {
                    s1 + s" **| ${s2}"
                }
                val result = wrappingFacility.wrapGifts(presentsNum, starWrap)
                val result2 = wrappingFacility.wrapGifts(presentsNum, starWrap)

                println("===== combined result below =====")
                val combined = wrappingFacility.wrapGifts(presentsNum, starWrap) + wrappingFacility.wrapGifts(presentsNum, starWrap)
                println(s"starred result: ${result}")
                result shouldBe ". **| 01 **| 02 **| 03 **| 04 **| 05"
            }
        }

    }
  
}
