package org.cscie88c.core.week2.demo

object CarObject {
  val position: Int =2
  val make: String = "Toyota"
  val model: String = "Camry"

  def apply(makemodel: String): CarObject = {
    val makemodel_arr = makemodel.split(" ")
    new CarObject(0, makemodel_arr(0), makemodel_arr(1))
  }

}
case class CarObject(position: Int, make: String, model: String)
