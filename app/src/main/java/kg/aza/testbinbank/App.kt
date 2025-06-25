package kg.aza.testbinbank

import android.app.Application
import kg.aza.testbinbank.core.FoundationInfrastructureModule
import kg.aza.testbinbank.core.FoundationPresentationModule
import kg.aza.testbinbank.presentation.MainModule
import kg.balance.reforged.foundation.infrastructure.local.preferences.UserPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            logger(PrintLogger(Level.DEBUG))
            modules(
                FoundationPresentationModule.create(),
                FoundationInfrastructureModule.create(),
                MainModule.create()
            )
        }
        fun KoinComponent.createUserPreferences() = inject<UserPreferences>()
    }
}
