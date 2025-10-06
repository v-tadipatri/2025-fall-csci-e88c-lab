package org.cscie88c.core.week5lab

import org.cscie88c.core.testutils.{StandardTest}

// write unit tests for VehicleTest below
class VehicleEnhancedTest extends StandardTest {
    val japaneseBrands = Seq("toyota", "honda", "nissan")

    val isJapaneseWithoutCase: PartialFunction[VehicleClass, String] =
      new PartialFunction[VehicleClass, String]{

        override def isDefinedAt(car: VehicleClass): Boolean = japaneseBrands.contains(car.make)

        override def apply(car: VehicleClass): String = s"${car.model}(${car.make})"
      }


    "Vehicle" when {
    "instantiated" should {

      "work with regular car classes" in {
        val lastTest = new VehicleRealClassTest()
        val found_toyota = lastTest.findToyotaCars()
        val all_cars = lastTest.my_cars
        val modelsFound = all_cars.collect(isJapaneseWithoutCase)
        println("==== These are the Japanese car brands we found ===")
        modelsFound.foreach(println)
        //try setting isDefined = true
        //can we use a more compact method?

        println(ObjectComplimenter.praise(all_cars.map(c => c.make+"_"+c.model)))
        println("======")
        println(ObjectComplimenter.praise(Seq("my_neighbor", "my_neighbors_dog", "stray_cat", "little_kitten")))
        println("======")
        println(ObjectComplimenter.praise((1 to 5)))
        val infiniteList = LazyList.from(1).map(i => i *5)
        //should we run the next line?
        //println(ObjectComplimenter.praise(infiniteList))
        //can we "reify" the list?
        println("======")
        //what about an empty list?
        println(ObjectComplimenter.praise(Seq()))
        println("======")

        /*
        found_toyota.foreach( c => {
          //driving 100 km
          c.driveForMilesFunc(100)
          //c.driveForMilesMethod(100)

        })
         */
      }

    }
  }


  val isJapaneseUsingCase: PartialFunction[VehicleClass, String] = {
    case car if japaneseBrands.contains(car.make.toLowerCase) => s"${car.model}(${car.make})"
  }

  
}
