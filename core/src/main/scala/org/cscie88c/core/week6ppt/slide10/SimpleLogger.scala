package org.cscie88c.core.week6ppt.slide10

trait Greetable {
  def name: String
  def greet: String =
    s"Hello, $name"

}

trait SimpleLogger {
   def logString(msg: String) =
     println(s"[logging String]: $msg")
}

case class Employee(name: String, title: String) extends Greetable with SimpleLogger
