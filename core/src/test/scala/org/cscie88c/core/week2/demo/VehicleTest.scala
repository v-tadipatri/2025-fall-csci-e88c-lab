package org.cscie88c.core.week2.demo

import org.cscie88c.core.testutils.StandardTest

class VehicleTest extends StandardTest{
  "Car" when {
    "started" should {
      "drive around" in {
        val car1 =new Car(5)
        //save return value of drive into a new value 'car2'
        val car2 = car1.drive(10)
        //toString has not been defined
        println(s"My car is ${car2}")
        //use shouldBe
        //what happens if we don't extend StandardTest?
        car2.position shouldBe 15

      }
    }
    "cloned" should {
       "produce duplicate cars" in {
         val originalFord = CarCase(1, "Ford", "ModelT")
         //note how you can copy just the fields you want
         val newFord = originalFord.copy( model="Mustang")
         println(s"Converted an old ${originalFord} to a new ${newFord}")
         newFord.position shouldBe 1
         newFord.make shouldBe "Ford"
         newFord.model shouldBe "Mustang"
       }
    }
  }
  "List of Cars" when {
     "processed" should {
       "sort correctly" in {
           val carset = List("Toyota Corolla", "Honda Accord", "Ford F150", "Hyundai Sonata")
           println(s"Cars before: ${carset}")
           //here is how to sort a list
           val carset_sorted = carset.sorted
           println(s"Cars after: ${carset_sorted}")
           //note the syntax for should<space>be
           carset_sorted(0) should be("Ford F150")
           carset_sorted(0) should not be("Ford Transit")
           //map functionality
           val carset_sorted_objects = carset_sorted.map(c => {
              val makemodel= c.split(" ")
              CarCase(0, makemodel(0), makemodel(1))
           })







         //now let's try the same thing with an object
         println(s"Cars as instances of CarCase: ${carset_sorted_objects}")
         val carset_sorted_test = carset_sorted.map(c => {
           //Use shorthand for : CarObject.apply(c)
           CarObject(c)
         })
         //we'll use the placeholder syntax in the next lecture to make it even shorter
         println(s"Cars as instances of CarObject: ${carset_sorted_test}")

       }
     }
  }
}
