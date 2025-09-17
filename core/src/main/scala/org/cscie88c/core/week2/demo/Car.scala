package org.cscie88c.core.week2.demo

/**
 * Syntax for creating a car which extends a vehicle
 * @param position
 */
class Car(position: Int) extends Vehicle(position){

  //spacing to show the parts of method
  override def drive(   distance: Int   ):       Vehicle        =         {
    //we cannot modify the testPosition
    //testPosition += 1
    //so we return a new Car
    new Car(position+distance)
  }




  //keep scrolling...

  /*
  override def toString: String = {
     "Not implemented yet!"
  }
   */
}
