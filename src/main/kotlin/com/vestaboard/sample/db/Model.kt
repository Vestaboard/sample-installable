package com.vestaboard.sample.db

import com.vestaboard.sample.lib.shared.ApiCredential
import com.vestaboard.sample.lib.shared.InstallationId
import com.vestaboard.sample.lib.shared.SubscriptionId
import java.time.LocalDateTime

data class SubscriptionModel(
  val id: SubscriptionId,
  val created: LocalDateTime,
  val installation: InstallationModel? = null
)

data class InstallationModel(
  val id: InstallationId,
  val created: LocalDateTime,
  val apiCredential: ApiCredential? = null
)

