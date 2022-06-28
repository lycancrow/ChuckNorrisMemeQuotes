package felipe.cuervo.chucknorrisphrases.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import felipe.cuervo.chucknorrisphrases.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class ChuckNorrisRepository(private val apiService: ApiService) {
    private var _chuckNorrisQuote = MutableLiveData<String?>()
    val chuckNorrisQuote: LiveData<String?> get()=_chuckNorrisQuote

    suspend fun loadChuckNorrisQuote(){

        val result = try{
            apiService.getChuckNorrisQuote()
        }catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        result?.let {
            _chuckNorrisQuote.value = it.value
            Log.i("citaChuck", it.value)
        }

    }

}