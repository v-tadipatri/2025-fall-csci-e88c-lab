package org.cscie88c.core.week5lab


/**
  * Real class with a companion object
  *
  * @param make
  * @param model
  */
class VehicleClass (val make: String, val model: String) {

  // Here are some methods  

    def toMilesMethod(km: Int) = (km  / 1.6).toInt
    def driveMethod(miles: Int) = println(s"You just drove ${miles} miles on a ${make} ${model},  and used ETA Expansion!")

  // Here are some functions  

    val toMilesFunc : Int => Int = { km => 
      (km  / 1.6).toInt
    }
    val driveFunc : Int => Unit = { miles => 
      println(s"You just drove ${miles} miles on a ${make} ${model}")
    }
    
    // This is how the functions are called

    def driveForMilesFunc = toMilesFunc andThen driveFunc

    // This is how the methods are called - with "ETA Expansion"
    def driveForMilesMethod = toMilesMethod _ andThen driveMethod _

}
object VehicleClass {
  def apply(makemodel: String) = {
    val arr = makemodel.split(" ")
    new VehicleClass(arr(0), arr(1) )
  }
  def unapply(vehicle: VehicleClass ): Option[(String, String)] = Some(vehicle.make, vehicle.model)

}




