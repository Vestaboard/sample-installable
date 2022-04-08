package com.vestaboard.sample

import com.vestaboard.sample.lib.Config
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseConnector : Runnable {
  override fun run() {
    val config = Config()

    Database.connect(
      "jdbc:mysql://${config.mysqlHost}:${config.mysqlPort}/${config.mysqlDatabase}?useSSL=false",
      driver = "com.mysql.cj.jdbc.Driver",
      user = config.mysqlUser,
      password = config.mysqlPassword
    )

    transaction {
      SchemaUtils.createMissingTablesAndColumns(
        // TODO: tables
      )
    }
  }
}
