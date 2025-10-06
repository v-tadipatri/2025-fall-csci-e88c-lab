package org.cscie88c.core.week5lab

import org.cscie88c.core.testutils.{StandardTest}


// write unit tests for VehicleTest below
class VehicleRealClassTest extends StandardTest {

    "Vehicle" when {
    "instantiated" should {

      "work with regular car classes" in {
        val toyotacar =  new VehicleClass("toyota", "corolla")
        val hondacar =  new VehicleClass("honda", "accord")

        val my_cars = Seq(new VehicleClass("toyota", "camry"), toyotacar, hondacar)
        
        val found_toyota = my_cars.filter( car => {
          val result = car match {
              case VehicleClass(make, model) if (make =="toyota" && model == "camry") => {
                Result(true, "this is also a camry " )
              }
              case VehicleClass(make, _) if (make =="toyota") => Result(true, "")
              case _ => Result(false, "not a toyota")
          }
          println(s" === ${car} Is this a  Toyota car ? ${result}")
          result.flag
        })
        found_toyota.foreach( c => {
          //driving 100 km
          //c.driveForMilesFunc(100)

        })
      }

    }
  }



  
}
