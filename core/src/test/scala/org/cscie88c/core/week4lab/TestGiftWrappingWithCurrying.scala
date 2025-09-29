package org.cscie88c.core.week4lab

import org.cscie88c.core.testutils.{StandardTest}

class TestGiftWrappingWithCurrying extends StandardTest {
    val wrappingFacility = new GiftWrappingFacilityWithCurrying
    val presentsNum: Seq[String] = (1 to 5).toList.map("0" + _)
    val presentsAlpha: Seq[String] = Seq("A", "B", "C", "D", "E")

    val parenthesesWrap :   (String, String)  => String     = (s1: String, s2: String) =>  {
        s1 + s"(${s2})"
    }

    "Wrapping facility " when {
        "asked to wrap presents using currying" should {

            "handle numeric Strings with parentheses" in {
                //this looks similar to what we saw before
                val dotResultLikeBefore = wrappingFacility.wrapGiftsWithCurrying(".")(presentsNum)(parenthesesWrap)
                dotResultLikeBefore shouldBe ".(01)(02)(03)(04)(05)"

                //but with currying, we can pass it just the 1st param, save that  
                val dotWrapper = wrappingFacility.wrapGiftsWithCurrying(".") _
                val dotResult = dotWrapper(presentsNum)(parenthesesWrap)
                dotResult shouldBe ".(01)(02)(03)(04)(05)"


            }

            "handle different presents with parentheses and a bow before it" in {
                //Here's how we can reuse it
                //let's try wrapping with a bow
                val bowWrapper = wrappingFacility.wrapGiftsWithCurrying("=BOW=") _
                
                //we can wrap numbers with a bow
                val bowResultNum = bowWrapper(presentsNum)(parenthesesWrap)
                bowResultNum shouldBe "=BOW=(01)(02)(03)(04)(05)"

                //we can wrap letters with a bow
                val bowResultLetters = bowWrapper(presentsAlpha)(parenthesesWrap)
                bowResultLetters shouldBe "=BOW=(A)(B)(C)(D)(E)"


            }


        }

    }
  
}
