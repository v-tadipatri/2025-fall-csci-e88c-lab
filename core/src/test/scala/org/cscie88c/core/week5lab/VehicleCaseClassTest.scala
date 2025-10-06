package org.cscie88c.core.week5lab

import org.cscie88c.core.testutils.{StandardTest}


// write unit tests for VehicleTest below
class VehicleCaseClassTest extends StandardTest {

    "Vehicle" when {
    "instantiated" should {
      "work with car case classes" in {
        val toyotacar = VehicleCase("toyota", "corolla")
        val hondacar = VehicleCase("honda", "accord")

        val my_cars = Seq(VehicleCase("toyota", "camry"), toyotacar, hondacar)
        
        val found_toyota = my_cars.filter( car => {
          val result = car match {
              case VehicleCase(make, model) if (make =="toyota" && model == "camry") => {
                Result(true, "this is also a camry " )
              }
              case VehicleCase(make, _) if (make =="toyota") => Result(true, "")
              case _ => Result(false, "not a toyota")
          }
          println(s" === ${car} Is this a  Toyota car ? ${result}")
          result.flag 
        })

        found_toyota.foreach (t => println(s"We found a 'Toyota', not sure what to do with it ${t} ..."))

      }

    }
  }



  
}
