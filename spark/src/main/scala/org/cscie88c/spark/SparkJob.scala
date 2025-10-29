package org.cscie88c.spark

import org.apache.spark.sql.SparkSession
import org.cscie88c.core.Utils

import java.io.File
import scala.util.Try

object SparkJob {

  def loadSparkCluster: SparkSession = {
    println("Loading cluster config...")
     SparkSession.builder()
      .appName("ClusterSparkJob")
      .master("spark://spark-master:7077")  // connect to local Spark master in Docker
      .config("spark.executor.memory", "1g")
      .config("spark.driver.host", "localhost")
      .getOrCreate()
  }

  def loadSparkLocal: SparkSession = {
    println("Loading local config...")
    SparkSession.builder()
      .appName("SampleSparkJob")
      .master("local[*]")
      .getOrCreate()

  }

  def main(args: Array[String]): Unit = {
    val spark = sys.env.getOrElse("LOCAL_MODE", "0") match {
      case "0" => loadSparkCluster
      case "1" => loadSparkLocal

    }

    import spark.implicits._

    val df = Seq("Alice", "Bob", "Carol").toDF("name")
    val greeted = df.map(row => Utils.greet(row.getString(0)))
    greeted.show(false)

    //will only work on local paths
    val path = Try(this.getClass.getResource("/data.csv").toURI.toString)
      .getOrElse("/opt/spark/spark-data/data.csv")
    //use "file:" and absolute path on spark cluster
    println(s"=== loading from : ${path}")


    //work with dataframe
    val df_movies = spark.read.option("header", "true").csv(path)
    println("===Show full data, as dataframe")
    df_movies.show()
    println("===Show distinct genres")
    df_movies.select("genre").distinct().show()

    df_movies.createOrReplaceTempView("movie_tbl")
    println("===Show counts by genre")
    val query = spark.sql("select genre, count(*) from movie_tbl group by genre")
    query.show()


    //or convert to a dataset
    val movie_set = df_movies.as[Movie]
    println("===show full data, as dataset")
    movie_set.show()
    println("=== these are superhero movies")
    movie_set.filter(_.isSuperhero).show()

    println("=== these are avenger movies")
    movie_set
      .filter(_.isYetAnotherAvengersMovie)
      .map(m => {
        MovieSnippet(m.title, m.year.toInt, m.description)
      })
      .show()

    spark.stop()
  }
}
