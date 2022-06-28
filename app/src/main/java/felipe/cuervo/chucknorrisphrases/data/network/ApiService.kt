package felipe.cuervo.chucknorrisphrases.data.network

import felipe.cuervo.chucknorrisphrases.data.network.model.ChuckNorrisModel
import retrofit2.http.GET

interface ApiService {
    @GET("/jokes/random")
    suspend fun getChuckNorrisQuote(): ChuckNorrisModel
}