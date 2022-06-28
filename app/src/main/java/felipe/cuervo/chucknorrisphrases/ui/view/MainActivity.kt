package felipe.cuervo.chucknorrisphrases.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import felipe.cuervo.chucknorrisphrases.ChuckNorrisViwerApplicarion
import felipe.cuervo.chucknorrisphrases.core.ServiceLocator
import felipe.cuervo.chucknorrisphrases.data.repository.ChuckNorrisRepository
import felipe.cuervo.chucknorrisphrases.databinding.ActivityMainBinding
import felipe.cuervo.chucknorrisphrases.ui.viewModel.ChuckNorrisViewModel
import felipe.cuervo.chucknorrisphrases.ui.viewModel.ChuckNorrisViewModelFactory


//https://api.chucknorris.io/jokes/random
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ChuckNorrisViewModel>{
        val apiService = (application as ChuckNorrisViwerApplicarion).serviceLocator.apiService
        val repository = ChuckNorrisRepository(apiService)
        ChuckNorrisViewModelFactory(repository, this, intent.extras)
    }

    private lateinit var  binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.chuckQuote.observe(this){
            it?.let {
                binding.textView.text = it
            }
        }
        viewModel.isLoading.observe(this){
            it?.let {
                binding.loading.isVisible = it
            }
        }


        binding.showPhrase.setOnClickListener {

            viewModel.reload()
        }


    }



}