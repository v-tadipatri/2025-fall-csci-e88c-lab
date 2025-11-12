package org.cscie88c.beam

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.{AWSCredentialsProvider, InstanceProfileCredentialsProvider}
import com.amazonaws.regions.Regions
import com.amazonaws.services.kinesis.{AmazonKinesis, AmazonKinesisClientBuilder}
import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.options.PipelineOptionsFactory
import org.apache.beam.sdk.transforms.MapElements
import org.apache.beam.sdk.transforms.SimpleFunction
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream
import org.apache.beam.sdk.io.kinesis.KinesisIO

object BeamKinesisToS3 extends App {

  val options = PipelineOptionsFactory.create()
  val p = Pipeline.create(options)

  // Kinesis stream configuration


  /*
  val streamName = "my-kinesis-stream"
  val region = "us-east-1"
  val transform =  KinesisIO.read()
    .withStreamName("my-stream")
    .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON)
   */
  // Read from Kinesis
  //val kinesisData = p.apply("ReadFromKinesis", transform

  //)
  /*
  val kinesisData = p.apply("ReadFromKinesis",
    KinesisIO.read()
      .withStreamName("my-kinesis-stream")
      .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON)
  )
   */



  val creds: AWSCredentialsProvider = new InstanceProfileCredentialsProvider(false) // EMR instance role


  /*
 val kinesisClient: AmazonKinesis =
    AmazonKinesisClientBuilder.standard()
      .withRegion(Regions.US_EAST_1)
      .build()

  val config = new ClientConfiguration()
  val kinesisData =
    p.apply(
      "ReadFromKinesis",
      KinesisIO.read()
        .withStreamName("my-stream")
        .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON)
        .withAWSClientsProvider(creds, Regions.US_EAST_1)
    )
   */


  //need to force types for compilation
  val kinesisDataCast = p.apply(
    "ReadFromKinesis",
    KinesisIO.read()
      .withStreamName("my-stream")
      .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON)
      .withAWSClientsProvider(creds, Regions.US_EAST_1)
      .asInstanceOf[org.apache.beam.sdk.transforms.PTransform[
      org.apache.beam.sdk.values.PBegin,
      org.apache.beam.sdk.values.PCollection[Array[Byte]]
    ]]
  )


  // Convert ByteArray -> String (assuming UTF-8)
  val lines = kinesisDataCast.apply("DecodeBytes", MapElements.via(new SimpleFunction[Array[Byte], String] {
    override def apply(input: Array[Byte]): String = new String(input, "UTF-8")
  }))


  // Write to S3
  lines.apply("WriteToS3",
    TextIO.write().to("s3://aws-vtadipatri-bucket/output/streaming-data")
      .withSuffix(".txt")
      .withNumShards(1)
  )

  p.run().waitUntilFinish()
}
