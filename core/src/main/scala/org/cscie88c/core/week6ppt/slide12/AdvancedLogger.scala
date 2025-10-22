package org.cscie88c.core.week6ppt.slide12

import com.typesafe.scalalogging.LazyLogging

trait Greetable {
  def name: String
  def greet: String =
    s"Hello, $name"

}

//use import
trait AdvancedLogger extends LazyLogging {
   def logString(msg: String) = {
     logger.info("test me")
     println(s"[logging String]: $msg")
     //access to logger
   }

}

case class Administrator(name: String, title: String) extends Greetable with AdvancedLogger

