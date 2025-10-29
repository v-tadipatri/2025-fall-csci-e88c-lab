package org.cscie88c.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, when}
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

  def loadResource(path: String) = {
    //if local, get from path
    //otherwise, copy to movies subdirectory in data folder (mounted in docker)
    val fullpath = Try(this.getClass.getResource(s"/movies/${path}").toURI.toString)
      .getOrElse(s"/opt/spark/spsark-data/movies/${path}")

    println(s"=== loading ${path} from : ${fullpath}")
    fullpath
  }

  def main(args: Array[String]): Unit = {
    /*
    val spark = sys.env.getOrElse("LOCAL_MODE", "0") match {
      case "0" => loadSparkCluster
      case "1" => loadSparkLocal

    }
    */
   val spark = loadSparkLocal

    import spark.implicits._

    val df = Seq("Alice", "Bob", "Carol").toDF("name")
    val greeted = df.map(row => Utils.greet(row.getString(0)))
    greeted.show(false)

    //will only work on local paths
    val title_path = loadResource("titles.csv")
    //use "file:" and absolute path on spark cluster

    val genre_path = loadResource("genres.csv")

    //work with dataframe
    val df_movies = spark.read.option("header", "true").csv(title_path)
    val df_genre_tmp = spark.read.option("header", "true").csv(genre_path)

    println("===Show full movie data, as dataframe")
    df_movies.show(false)

    //example of adding a column
    val isKidFriendly = col("genre_title").equalTo("Family")
    val newcol = when(isKidFriendly, "yes").otherwise("no")
    val df_genre = df_genre_tmp.withColumn("kid_friendly", newcol)
    println("===Show full genre data, as dataframe")
    df_genre.show(false)

    val joined = df_movies.join(df_genre, df_movies("genre")===df_genre("genre_title"))
    println("===Show joined data, as dataframe")
    joined.show(100, false)

    println("===Show distinct genres")
    df_movies.select("genre").distinct().show()

    df_movies.createOrReplaceTempView("movie_tbl")
    println("===Show counts by genre")
    val query = spark.sql("select genre, count(*) from movie_tbl group by genre")
    query.show()


    //or convert to a dataset
    val movie_set = df_movies.as[Movie]
    println("===show full data, as dataset")
    movie_set.show(false)
    println("=== these are superhero movies")
    movie_set.filter(_.isSuperhero).show(false)

    println("=== these are avenger movies")
    movie_set
      .filter(_.isYetAnotherAvengersMovie)
      .map(m => {
        val short_desc = m.description.length match {
          case i if i > 50 => m.description.substring(0,50)+"..."
          case _ => m.description
        }
        MovieSnippet(m.title, m.year.toInt, short_desc)
      })
      .show(false)

    spark.stop()
  }
}
