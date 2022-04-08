package com.vestaboard.sample

import com.vestaboard.sample.lib.platform.IPlatformApiClient
import com.vestaboard.sample.lib.shared.asSubscriptionConfigToken
import io.javalin.Javalin
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime
import java.time.ZoneOffset

class Api(
  private val platformApiClient: IPlatformApiClient
) : Runnable {
  override fun run() {
    Javalin
      .create()
      .start(7000).apply {
        get("/") { ctx -> ctx.result("Hello World") }

        put("/settings/{id}/{token}") { ctx ->
          val channelToken = ctx.pathParam("token").asSubscriptionConfigToken()
          // TODO: channel update endpoint
        }

        get("/settings/{id}/{token}") { ctx ->
          val channelToken = ctx.pathParam("token").asSubscriptionConfigToken()
          // TODO: channel get endpoint
        }
      }
  }
}
