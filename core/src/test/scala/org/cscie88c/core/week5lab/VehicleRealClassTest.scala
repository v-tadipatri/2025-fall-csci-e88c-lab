package org.cscie88c.core.week5lab

import org.cscie88c.core.testutils.{StandardTest}


// write unit tests for VehicleTest below
class VehicleRealClassTest extends StandardTest {
    val toyotacar =  new VehicleClass("toyota", "corolla")
    val hondacar =  new VehicleClass("honda", "accord")
    //these are defined a different way...
    val fordcar =  VehicleClass("ford mustang")
    val fordcar2 =  VehicleClass("ford bronco")

    val my_cars = Seq(new VehicleClass("toyota", "camry"), toyotacar, hondacar, fordcar, fordcar2)

     //just for demo purposes - normally, we would not put this in a test class
    def findToyotaCars(): Seq[VehicleClass] = {


      val found_toyota = my_cars.filter( car => {
        val result = car match {
          case VehicleClass(make, model) if (make =="toyota" && model == "camry") => {
            Result(true, "this is also a camry " )
          }
          //try commenting out apply and unapply methods
          case VehicleClass(make, _) if (make =="toyota") => Result(true, "")
          case _ => Result(false, "not a toyota")
        }
        println(s" === ${car} Is this a  Toyota car ? ${result}")
        result.flag
      })
      found_toyota
    }

    "Vehicle" when {
    "instantiated" should {

      "work with regular car classes" in {
        val found_toyota = findToyotaCars()
        found_toyota.foreach( c => {
          //driving 100 km
          //c.driveForMilesFunc(100)

        })
      }

    }
  }



  
}
