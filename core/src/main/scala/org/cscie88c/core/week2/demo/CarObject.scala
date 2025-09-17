package org.cscie88c.core.week2.demo

/**
 * Companion object
 */
object CarObject {
  //these are not specific to an instance
  val position: Int =2
  //back in the early 1900's, if you had a car it was...
  val make: String = "Ford"
  val model: String = "ModelT"

  def apply(makemodel: String): CarObject = {
    //the apply method takes a string and makes a new instance
    val makemodel_arr = makemodel.split(" ")
    new CarObject(0, makemodel_arr(0), makemodel_arr(1))
  }

}

/**
 * Case class - note the same name
 * @param position
 * @param make
 * @param model
 */
case class CarObject(position: Int, make: String, model: String)
