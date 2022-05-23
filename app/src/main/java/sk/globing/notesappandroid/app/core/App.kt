package sk.globing.notesappandroid.app.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import sk.globing.core.di.allModules
import sk.globing.notesappandroid.app.di.notesAppModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(allModules + notesAppModule)
        }
    }
}