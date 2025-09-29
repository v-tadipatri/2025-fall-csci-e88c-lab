package org.cscie88c.core.week4lab

class GiftWrappingFacilityWithCurrying {
  
    /**
      * This is a curried function
      *
      * @param ribbon
      * @param presents
      * @param func
      * @return
      */
    def wrapGiftsWithCurrying(ribbon: String)(gifts: Seq[String])(func: (String, String) => String) : String = {
        gifts.fold(ribbon)(func)

    }

}
