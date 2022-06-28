package felipe.cuervo.chucknorrisphrases

import android.app.Application
import felipe.cuervo.chucknorrisphrases.core.ServiceLocator

class ChuckNorrisViwerApplicarion: Application() {
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator()
    }

}