package org.cscie88c.beam

import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.transforms.{Count, MapElements, SimpleFunction}
import org.apache.beam.sdk.values.TypeDescriptors
import org.apache.beam.sdk.options.PipelineOptionsFactory


object BeamWordCount {
  def main(args: Array[String]): Unit = {

    // 1. Create options
    val options = PipelineOptionsFactory.fromArgs(args: _*).as(classOf[FlinkPipelineOptions])
    options.setRunner(classOf[org.apache.beam.runners.flink.FlinkRunner])
    options.setParallelism(2) // optional

    // 2. Create pipeline
    val pipeline = Pipeline.create(options)

    // 3. Read input file from S3
    pipeline.apply("ReadLines", TextIO.read().from("s3://your-bucket/input.txt"))
      .apply("ExtractWords", MapElements.into(TypeDescriptors.strings())
        .via[String, String](line => line.split("\\s+").toSeq))
      .apply("CountWords", Count.perElement[String]())
      .apply("FormatResults", MapElements.into(TypeDescriptors.strings())
        .via(kv => s"${kv.getKey}: ${kv.getValue}"))
      .apply("WriteResults", TextIO.write().to("s3://your-bucket/output/results"))

    // 4. Run pipeline
    pipeline.run().waitUntilFinish()
  }

}
