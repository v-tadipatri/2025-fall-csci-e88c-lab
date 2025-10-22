package org.cscie88c.core.week6ppt.slide13

import com.typesafe.scalalogging.LazyLogging
import org.cscie88c.core.week6ppt.slide12.Greetable

trait GenericServer[A] extends LazyLogging {
   //abstract method
   def resourceString: String
   def logString(msg: String) = {
     //access to logger
     logger.info(s"resources: [ $resourceString ] : $msg")
   }

}

case class SparkServer(name: String, numberOfCores: Int) extends Greetable with GenericServer[SparkServer]{

  override def resourceString: String = numberOfCores.toString
}

case class KafkaServer(name: String, zookeeperUrl: String) extends Greetable with GenericServer[SparkServer]{

  override def resourceString: String = zookeeperUrl
}

