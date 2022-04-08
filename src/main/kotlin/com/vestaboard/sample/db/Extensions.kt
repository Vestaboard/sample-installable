package com.vestaboard.sample.db

import com.vestaboard.sample.InstallationTable
import com.vestaboard.sample.SubscriptionTable
import com.vestaboard.sample.lib.shared.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun SubscriptionTable.get(id: SubscriptionId) = transaction {
  SubscriptionTable
    .select { subscription_id eq id.asString() }
    .singleOrNull()
    ?.let {
      SubscriptionModel(
        id = it.get(subscription_id).asSubscriptionId(),
        created = it.get(created)
      )
    }
}

fun InstallationTable.get(id: InstallationId) = transaction {
  InstallationTable
    .select { installation_id eq id.asString() }
    .singleOrNull()
    ?.let {
      InstallationModel(
        id = it.get(installation_id).asInstallationId(),
        created = it.get(created)
      )
    }
}
