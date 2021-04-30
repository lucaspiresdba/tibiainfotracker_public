package br.com.lucaspires.tibiainfotracker.presentation.app

import android.app.Application
import br.com.lucaspires.tibiainfotracker.AppConstants.FIREBASE_DATABASE_REFERENCE_CITIES
import br.com.lucaspires.tibiainfotracker.AppConstants.FIREBASE_DATABASE_REFERENCE_WORLDS
import br.com.lucaspires.tibiainfotracker.BuildConfig
import br.com.lucaspires.tibiainfotracker.di.AppModules
import com.facebook.stetho.Stetho
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class TibiaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
        Firebase.database.run {
            setPersistenceEnabled(true)
            getReference(FIREBASE_DATABASE_REFERENCE_WORLDS).keepSynced(true)
            getReference(FIREBASE_DATABASE_REFERENCE_CITIES).keepSynced(true)
        }

        startKoin {
            androidContext(this@TibiaApp)
            modules(AppModules.getModules())
        }
    }
}