name := "beam"

val scioVersion = "0.14.1"
val beamVersion = "2.48.0"

resolvers += "Confluent" at "https://packages.confluent.io/maven/"


libraryDependencies ++= Seq(
  "com.spotify" %% "scio-core" % scioVersion,
  "com.spotify" %% "scio-test" % scioVersion % Test,
  "org.apache.beam" % "beam-runners-direct-java" % beamVersion % "provided",
  "org.apache.beam" % "beam-runners-google-cloud-dataflow-java" % beamVersion % "provided",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.1" % "provided",
  //"org.apache.beam" % "beam-sdks-java-core" % beamVersion,
  //"org.apache.beam" % "beam-sdks-java-io-amazon-web-services" % beamVersion,
  //"org.apache.beam" % "beam-sdks-java-io-kinesis" % beamVersion,
  //"org.apache.hadoop" % "hadoop-aws" % "3.3.4"
  "org.apache.beam" % "beam-sdks-java-core" % beamVersion % "provided",
  "org.apache.beam" % "beam-sdks-java-io-kinesis" % beamVersion,
  "org.apache.beam" % "beam-runners-direct-java" % beamVersion % "provided"

  // "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1"
)
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.2"

Compile / mainClass := Some("org.cscie88c.beam.AwsBeamJob")

assembly / mainClass := Some("org.cscie88c.beam.AwsBeamJob")
assembly / assemblyJarName := "BeamJob.jar"
assembly / test := {}
assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  /*
  case "application.conf"            => MergeStrategy.concat
  case x =>
    val oldStrategy = (assembly / assemblyMergeStrategy).value
    oldStrategy(x)
   */
  case _ => MergeStrategy.first

}
// see shading feature at https://github.com/sbt/sbt-assembly#shading
assembly / assemblyShadeRules := Seq(
  ShadeRule.rename("shapeless.**" -> "shadeshapeless.@1").inAll
)
