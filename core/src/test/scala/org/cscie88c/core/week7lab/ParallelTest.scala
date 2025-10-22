package org.cscie88c.core.week7lab

import org.cscie88c.core.testutils.StandardTest
//this must be in dependencies: "org.scala-lang.modules" %% "scala-parallel-collections"
//MUST include this import
import scala.collection.parallel.CollectionConverters._

class ParallelTest extends StandardTest{
  def slowPrint(line: String) = {
     Thread.sleep(1000)
      println(line)
  }

  //unlike java, this does not start with a slash
   val lines = scala.io.Source.fromResource("data/inputNums.txt").getLines().toList
   "sequential load should run" in {
     lines.foreach(slowPrint(_) )
   }
  "parallel load should run" in {
    lines.par.foreach(slowPrint(_) )
  }
}
