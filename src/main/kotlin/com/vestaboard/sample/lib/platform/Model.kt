package com.vestaboard.sample.lib.platform

import com.vestaboard.sample.lib.shared.ApiCredential
import com.vestaboard.sample.lib.shared.Layout
import com.vestaboard.sample.lib.shared.SubscriptionId

data class PostMessageResponse(
  val success: Boolean
)

data class PostMessageRequest(
  val subscription: SubscriptionId,
  val apiCredential: ApiCredential,
  val layout: Layout
)

data class UpdateChannelRequest(
  val subscription: SubscriptionId,
  val apiCredential: ApiCredential,
  val newTitle: String,
  val newDescription: String
)

data class UpdateChannelResponse(
  val success: Boolean
)
