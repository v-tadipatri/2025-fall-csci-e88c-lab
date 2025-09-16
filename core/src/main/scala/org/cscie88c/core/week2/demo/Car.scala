package org.cscie88c.core.week2.demo

class Car(position: Int) extends Vehicle(position){

  override def drive(distance: Int): Vehicle = {
    //testPosition += 1
    new Car(position+distance)
  }
  /*
  override def toString: String = {
     "Not implemented yet!"
  }
   */
}
