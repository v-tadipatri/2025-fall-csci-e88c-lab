package org.cscie88c.core.week2.demo

/*
  With a simple case class definition, we get other methods
    like copy, toString, equals
 */
case class CarCase(position: Int, make: String, model: String)








//keep scrolling...










/*
case class CarCase(override val position: Int, make: String, model: String) extends Vehicle(position) {

  override def drive(distance: Int): Vehicle = {
      CarCase(position+distance, make, model)
  }
}
 */
