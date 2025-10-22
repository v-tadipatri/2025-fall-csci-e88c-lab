package org.cscie88c.core.week6ppt.slide9

trait Greetable {
  def name: String
  def greet: String =
    s"Hello, $name"

}

case class User(name: String, age: Int) extends Greetable
