package org.cscie88c.core.week6ppt.slide25

case class Dog(id: String, breed: String, age: Int)
object Dog {
  implicit val dogOrder: Ordering[Dog] = new Ordering[Dog] {
    def compare(x: Dog, y: Dog): Int = {
      if (x.age != y.age) x.age - y.age
      else if (x.breed != y.breed) x.breed.compareTo(y.breed)
      else x.id.compareTo(y.id)
    }
  }
}
