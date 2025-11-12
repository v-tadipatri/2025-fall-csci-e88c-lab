package org.cscie88c.beam

import com.spotify.scio._

// Beam word count example
// run locally using
// sbt beam/run
object BeamJob {
  def main(args: Array[String]): Unit = {
    println("Starting beam job...")
    val (sc: ScioContext, jobArgs: Args) = ContextAndArgs(args)
    implicit val scImplicit: ScioContext = sc
    val inputFile = jobArgs.getOrElse("input-file", "../data/alice_in_wonderland.txt")
    val checkFile = new java.io.File(inputFile)
    println(s"${checkFile.getAbsolutePath} exists = ${checkFile.exists}")
    val outputFile = jobArgs.getOrElse("output-file", "./target/alice_word_count")
    // Define the pipeline

    val wordCounts = sc.textFile(inputFile)
      .flatMap(_.split("\\W+"))
      .map(_.toLowerCase)
      .countByValue

    // Write the output
    wordCounts.saveAsTextFile(outputFile)
    sc.run().waitUntilDone()
  }
}
