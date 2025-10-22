package org.cscie88c.core.week7lab

import org.cscie88c.core.testutils.StandardTest

import scala.collection.parallel.ForkJoinTaskSupport
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
    //what happens when import is commented out?
    lines.par.foreach(slowPrint(_) )
  }


  "parallel load should run with configurable threads" in {
    val forkJoinPool = new java.util.concurrent.ForkJoinPool(10)
    //need to save parallel collection and set pool
    val lines_par = lines.par
    lines_par.tasksupport = new ForkJoinTaskSupport(forkJoinPool)
    lines_par.foreach(slowPrint(_) )
  }


}
