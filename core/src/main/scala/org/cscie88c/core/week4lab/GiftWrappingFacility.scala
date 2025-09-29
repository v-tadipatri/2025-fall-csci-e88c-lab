package org.cscie88c.week4lab

/**
  * This gift wrapping facility takes gifts and "wraps" them using a function
  * All of the class methods take 2 parameters
  *    - gifts (Sequence of Strings)
  *    - func (function to call)
  *
  */
class GiftWrappingFacility {
  
    def wrapGifts(gifts: Seq[String], func: (String, String) => String): String  = {
        //println("These are some really cool gifts " + Math.random())
        gifts.fold(".")(func)
    }

    //see the return value
    def watchGiftsGetWrapped(gifts: Seq[String], func: (String, String) => String): Array[String]  = {
        gifts.scan(".")(func).toArray
    }

    def wrapGiftsBackwards(gifts: Seq[String], func: (String, String) => String): String  = {
        gifts.foldRight(".")(func)
    }

    //see the return value
    def watchGiftsGetWrappedBackwards(gifts: Seq[String], func: (String, String) => String): Array[String]  = {
        gifts.scanRight(".")(func).toArray
    }


}
