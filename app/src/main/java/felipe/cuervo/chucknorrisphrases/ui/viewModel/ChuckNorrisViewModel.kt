package felipe.cuervo.chucknorrisphrases.ui.viewModel

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.bumptech.glide.Glide.init
import felipe.cuervo.chucknorrisphrases.data.repository.ChuckNorrisRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ChuckNorrisViewModel (private val repository: ChuckNorrisRepository): ViewModel(){
    val chuckQuote = repository.chuckNorrisQuote
    val isLoading = MutableLiveData<Boolean>()
    private val _getChuckQuote = MutableLiveData<String?>()
    val getChuckQuote : LiveData<String?>get() = _getChuckQuote


    init {
        viewModelScope.launch {

            genetateNewQuote()

        }
    }

    suspend fun genetateNewQuote(){
        isLoading.postValue(true)
        repository.loadChuckNorrisQuote()
        isLoading.postValue(false)
    }

    fun reload(){
        repository.let {
            getChuckQuote
        }
    }

}


class ChuckNorrisViewModelFactory(
    private val repository: ChuckNorrisRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?
):AbstractSavedStateViewModelFactory(owner, defaultArgs){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(ChuckNorrisViewModel::class.java)){
            return ChuckNorrisViewModel(repository) as T
        }
        throw  IllegalArgumentException("Invalid view model")
    }
}
