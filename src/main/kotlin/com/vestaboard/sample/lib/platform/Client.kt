package com.vestaboard.sample.lib.platform

import com.vestaboard.sample.lib.shared.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import org.slf4j.LoggerFactory

interface IPlatformApiClient {
  fun getChannel(token: ChannelConfigurationToken): ChannelConfiguration
  fun updateChannel(request: UpdateChannelRequest): UpdateChannelResponse
  fun postMessage(request: PostMessageRequest): PostMessageResponse
}

class PlatformApiClient : IPlatformApiClient {
  private val CLIENT = OkHttpClient()
  private val LOGGER = LoggerFactory.getLogger("PlatformApiClient")

  private val PLATFORM_BASE_URL = "https://platform.vestaboard.com"

  override fun updateChannel(request: UpdateChannelRequest): UpdateChannelResponse {
    val request =
      Request.Builder()
        .url("${PLATFORM_BASE_URL}/v2.0/subscriptions/${request.subscription.asString()}")
        .post(
          RequestBody.Companion.create(
            "application/json".toMediaType(),
            JSONObject(
              mapOf(
                Pair("newTitle", request.newTitle),
                Pair("newDescription", request.newDescription)
              )
            ).toString(0)
          )
        )
        .header("X-Vestaboard-Api-Key", request.apiCredential.key)
        .header("X-Vestaboard-Api-Secret", request.apiCredential.secret)
        .build()

    val response = CLIENT.newCall(request).execute()

    return when {
      (response.code > 299) -> UpdateChannelResponse(success = false)
      else -> return UpdateChannelResponse(success = true)
    }
  }

  override fun getChannel(token: ChannelConfigurationToken): ChannelConfiguration {
    val request = Request.Builder()
      .get()
      .url("${PLATFORM_BASE_URL}/v2.0/subscription-configuration-token")
      .header("X-Vestaboard-Subscription-Configuration-Token", token.get())
      .build()

    val response = CLIENT.newCall(request).execute()

    val body = response.body?.string()?.asJsonObject() ?: throw Error()

    val subscription = body.getJSONObject("subscription")
    val subscriptionId = subscription.getString("id").asSubscriptionId()
    val installation = subscription.getJSONObject("installation")
    val installationId = installation.getString("id").asInstallationId()
    val apiCredential = installation.getJSONObject("apiCredential")
    val apiKey = apiCredential.getString("key")
    val apiSecret = apiCredential.getString("secret")

    return ChannelConfiguration(
      id = subscriptionId, installationId = installationId, apiKey = apiKey, apiSecret = apiSecret
    )
  }

  override fun postMessage(request: PostMessageRequest): PostMessageResponse {
    val request = Request.Builder()
      .url("https://platform.vestaboard.com/subscriptions/${request.subscription.asString()}/message")
      .post(
        RequestBody.Companion.create(
          "application/json".toMediaType(),
          JSONObject(
            mapOf(
              Pair("characters", request.layout.get())
            )
          ).toString(0)
        )
      )
      .header("X-Vestaboard-Api-Key", request.apiCredential.key)
      .header("X-Vestaboard-Api-Secret", request.apiCredential.secret)
      .build()

    val response = CLIENT.newCall(request).execute()

    return when {
      (response.code > 299) -> PostMessageResponse(success = true)
      else -> PostMessageResponse(success = false)
    }
  }
}
