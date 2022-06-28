package felipe.cuervo.chucknorrisphrases.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChuckNorrisModel(
    val value : String
)