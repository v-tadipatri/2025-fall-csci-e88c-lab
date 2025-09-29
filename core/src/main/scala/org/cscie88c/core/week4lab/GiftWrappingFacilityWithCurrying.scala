package org.cscie88c.core.week4lab

class GiftWrappingFacilityWithCurrying {

    /**
     * The Olde-fashioned way of writing functions
     * Note that we have 3 params - ribbon, gifts, func
     * @param ribbon
     * @param gifts
     * @param func
     * @return
     */
    def wrapGiftsOldeStyle(ribbon: String, gifts: Seq[String], func: (String, String) => String) : String = {
        gifts.fold(ribbon)(func)
    }

    /**
      * This is a curried function
      *
      * @param ribbon
      * @param presents
      * @param func
      * @return
      */
    def wrapGiftsWithCurrying    (ribbon: String)   (gifts: Seq[String])   (func: (String, String) => String) : String = {
        gifts.fold(ribbon)(func)
    }

}
