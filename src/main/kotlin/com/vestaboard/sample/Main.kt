package com.vestaboard.sample

import com.vestaboard.sample.lib.platform.PlatformApiClient

fun main() {
  val databaseConnector = DatabaseConnector()
  val platformApiClient = PlatformApiClient()
  val api = Api(platformApiClient)

  databaseConnector.run()
  api.run()
}
