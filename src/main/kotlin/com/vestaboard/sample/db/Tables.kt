package com.vestaboard.sample

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object SubscriptionTable : LongIdTable("subscription") {
  val created = datetime("created")
  val subscription_id = varchar("subscription_id", 250).uniqueIndex()
  val installation_id = varchar("installation_id", 250).nullable().index()
}

object InstallationTable : LongIdTable("installation") {
  val created = datetime("created")
  val installation_id = varchar("installation_id", 250).index()
  val api_key = varchar("api_key", 250).nullable().index()
  val api_secret = varchar("api_secret", 250).nullable()
}
