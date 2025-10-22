package org.cscie88c.core.week6ppt.slide20

import org.cscie88c.core.week6ppt.slide18.ApplicationConfig
import org.cscie88c.core.week6ppt.slide19.TransitApi

class Implicit {
  implicit val config: ApplicationConfig = ApplicationConfig("my-app", "http://api.com", "my-token")
  TransitApi.callTransitRestApi("green", 10)

}
