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
        println("These should be cars: "+my_cars)
        
        val found_toyota = my_cars.filter( car => {
          val result = car match {
            //which one will toyota camry match?
              case VehicleCase(make, model) if (make =="toyota" && model == "camry") => {
                Result(true, "this is also a camry " )
              }
              case VehicleCase(make, _) if (make =="toyota") => Result(true,s"is some other kind of toyota")
              case VehicleCase(_,_) => Result(false, s"is not a toyota")
              case _ => Result(false, "not even a car")
          }
          println(s" === ${car} Is this a  Toyota car ? ${result}")
          result.flag 
        })

        found_toyota.foreach (t => println(s"We found a 'Toyota', not sure what to do with it ${t} ..."))

        //let's throw some random objects in my_cars above
      }

    }
  }

  /*
  val plane = Airplane("Boeing", "747")
  val samsung_fridge = Refrigerator("Samsung", "french-door")
  val ge_fridge = Refrigerator("GE", "stainless_steel")

   */


  
}
