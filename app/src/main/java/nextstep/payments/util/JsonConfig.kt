package nextstep.payments.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

import java.time.YearMonth

object JsonConfig {

    val json = Json {
        serializersModule = SerializersModule {
            contextual(YearMonth::class, YearMonthSerializer)
        }
    }
}


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = YearMonth::class)
object YearMonthSerializer : KSerializer<YearMonth> {
    override fun serialize(encoder: Encoder, value: YearMonth) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): YearMonth {
        return YearMonth.parse(decoder.decodeString())
    }
}