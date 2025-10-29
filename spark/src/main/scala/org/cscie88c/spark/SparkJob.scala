package org.cscie88c.spark

import org.apache.spark.sql.SparkSession
import org.cscie88c.core.Utils

import java.io.File
import scala.util.Try

object SparkJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SampleSparkJob")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val df = Seq("Alice", "Bob", "Carol").toDF("name")
    val greeted = df.map(row => Utils.greet(row.getString(0)))
    //will only work on local paths
    val path = Try(this.getClass.getResource("/data.csv").toURI.toString).getOrElse("/data/")
    //use "file:" and absolute path on spark cluster


    //work with dataframe
    val df_movies = spark.read.option("header", "true").csv(path)
    df_movies.select("genre").distinct().show
    df_movies.show()

    //or convert to a dataset
    val movie_set = df_movies.as[Movie]
    movie_set.show()

    greeted.show(false)
    spark.stop()
  }
}
