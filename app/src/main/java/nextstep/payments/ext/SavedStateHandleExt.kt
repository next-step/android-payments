package nextstep.payments.ext

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.json.Json
import nextstep.payments.util.JsonConfig


inline fun <reified T> SavedStateHandle.getSerializable(
    key: String,
    json: Json = JsonConfig.json
): T? {
    return get<String>(key)?.let { json.decodeFromString<T>(it) }
}