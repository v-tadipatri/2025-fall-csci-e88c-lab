package org.cscie88c.core.week2.demo

/**
 * Parent class, which is abstract and takes a position in constructor
 * @param position
 */
abstract class Vehicle(val position: Int) {
  //this is a val which cannot be modified
  val testPosition = 0
  //subclass defines the behavior
  //note how it returns a new Vehicle
  def drive(distance: Int): Vehicle

}
