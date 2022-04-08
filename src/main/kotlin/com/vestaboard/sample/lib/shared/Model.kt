package com.vestaboard.sample.lib.shared

import okio.internal.commonAsUtf8ToByteArray
import org.json.JSONArray
import org.json.JSONObject
import java.security.MessageDigest
import java.util.*

fun ByteArray.toDatabaseSafeString() = Base64.getEncoder()
  .encode(this)
  .toString(Charsets.UTF_8)

fun Layout.fingerprint() = MessageDigest
  .getInstance("MD5")
  .let { md5 -> md5.digest(JSONArray(this.get()).toString(0).commonAsUtf8ToByteArray()) }
  .let { it.toDatabaseSafeString() }

data class Layout(private val characters: List<List<Int>>) {
  fun get() = characters

  override fun toString() = try {
    "Layout(fingerprint=${this.fingerprint()})"
  } catch (t: Throwable) {
    t.printStackTrace()
    ""
  }
}

data class SubscriptionId(private val id: UUID) {
  fun get() = id
}

data class MessageId(private val id: UUID) {
  fun get() = id
}

fun SubscriptionId.asString() = this.get().toString().lowercase()
fun MessageId.asString() = this.get().toString().lowercase()
fun InstallationId.asString() = this.get().toString().lowercase()
fun String.asMessageId() = this.asUUID().let { MessageId(it) }
fun String.asUUID() = UUID.fromString(this)
fun String.asInstallationId() = this.asUUID().let { InstallationId(it) }
fun String.asSubscriptionId() = this.asUUID().let { SubscriptionId(it) }

data class InstallationId(private val id: UUID) {
  fun get() = id
}

data class ChannelConfigurationToken(private val token: String) {
  fun get() = token
}

fun String.asSubscriptionConfigToken() = ChannelConfigurationToken(this)

data class ChannelConfiguration(
  val id: SubscriptionId,
  val installationId: InstallationId,
  val apiKey: String,
  val apiSecret: String,
  val userData: String? = null
)

fun Map<String, Any?>.asJsonObject() = JSONObject(this)
fun String.asJsonObject() = JSONObject(this)

data class ApiCredential(
  val key: String,
  val secret: String
)
