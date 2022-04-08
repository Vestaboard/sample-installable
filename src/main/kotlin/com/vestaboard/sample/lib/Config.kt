package com.vestaboard.sample.lib

data class Config(
  val mysqlHost: String = System.getenv("MYSQL_HOST"),
  val mysqlDatabase: String = System.getenv("MYSQL_DATABASE"),
  val mysqlUser: String = System.getenv("MYSQL_USER"),
  val mysqlPassword: String = System.getenv("MYSQL_PASSWORD"),
  val mysqlPort: Int = System.getenv("MYSQL_PORT").toInt()
)
