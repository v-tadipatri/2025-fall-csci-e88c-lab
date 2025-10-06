package org.cscie88c.core.week5lab

import org.cscie88c.core.testutils.{StandardTest}

// write unit tests for VehicleTest below
class VehicleEnhancedTest extends StandardTest {

    "Vehicle" when {
    "instantiated" should {

      "work with regular car classes" in {
        //note that apply method is called here
        val toyotacar =  VehicleClass("toyota corolla")
        val hondacar =  VehicleClass("honda accord")
        val fordcar =  VehicleClass("ford mustang")
        val fordcar2 =  VehicleClass("ford bronco")

        //this explicitly calls class constructor
        val my_cars = Seq(new VehicleClass("toyota", "camry"), toyotacar, hondacar, fordcar, fordcar2)
        
        val found_toyota = my_cars.filter( car => {
          val result = car match {
              case VehicleClass(make, model) if (make =="toyota" && model == "camry") => {
                Result(true, "this is also a camry " )
              }
              //try commenting out apply and unapply methods
              case VehicleClass(make, _) if (make =="toyota") => Result(true, "")
              
              //case VehicleClass(make, model) if (make =="ford" && model=="mustang") => Result(false, "...but that's a cool car")
              case _ => Result(false, "not a toyota")
          }
          //we did not implement toString
          println(s" === ${car.make} ${car.model} Is this a Toyota car ? ${result}")
          result.flag == true
        })

        found_toyota.foreach( c => {
          //driving 100 km
          c.driveForMilesFunc(100)
          //c.driveForMilesMethod(100)

        })
      }

    }
  }



  
}
