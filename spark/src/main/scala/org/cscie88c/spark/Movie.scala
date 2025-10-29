package org.cscie88c.spark

case class Movie(
        genre: String,
        category: String,
        title: String,
        year: String,
        distribution: String,
        description: String,
        url: String,
        cover_photo: String
) {
  def isSuperhero = description.toLowerCase.indexOf("superhero")!= -1

  def isYetAnotherAvengersMovie = title.toLowerCase().contains("avengers")

}

case class MovieSnippet(
                       title: String,
                       year: Int,
                       description: String
                )
