package org.cscie88c.core.week2.demo

case class CarCase(position: Int, make: String, model: String)

















/*
case class CarCase(override val position: Int, make: String, model: String) extends Vehicle(position) {

  override def drive(distance: Int): Vehicle = {
      CarCase(position+distance, make, model)
  }
}
 */
