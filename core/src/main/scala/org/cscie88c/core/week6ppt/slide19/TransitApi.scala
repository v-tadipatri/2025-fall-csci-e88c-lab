package org.cscie88c.core.week6ppt.slide19

import org.cscie88c.core.week6ppt.slide18.ApplicationConfig

object TransitApi {
  def logAppStartup(implicit config: ApplicationConfig, transit: String): Unit = {
    println(s"Application [${config.appName}] started with ${config.apiUrl} and authToken ${config.authenticationToken}")
  }

  def callTransitRestApi(
              transitLine: String,
              resultsToReturn: Int,
                         ) (implicit config: ApplicationConfig): Any = {
    println(s"Calling rest api with url [ ${config.apiUrl} ] and transitLine: $transitLine and results ${resultsToReturn}")
    val result = List((1,2), (3,4))
    result
  }

}
