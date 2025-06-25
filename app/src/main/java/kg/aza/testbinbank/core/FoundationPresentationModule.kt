package kg.aza.testbinbank.core

import android.content.Context
import android.content.res.Configuration
import kg.balance.reforged.foundation.infrastructure.local.preferences.UserPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.Locale

val foundationModule = module {
    includes(FoundationInfrastructureModule.create())
    factory { UIText(updateLocale(get(), androidContext())) }
}

private fun updateLocale(userPreferences: UserPreferences, context: Context): Context {
    val configuration = Configuration(context.resources.configuration)
    return context.createConfigurationContext(configuration)
}

object FoundationPresentationModule : KoinModule() {
    override fun create(): Module {
        return module {
            includes(FoundationInfrastructureModule.create())
            factory { UIText(updateLocale(get(), androidContext())) }
        }
    }

    private fun updateLocale(userPreferences: UserPreferences, context: Context): Context {
        val configuration = Configuration(context.resources.configuration)
        return context.createConfigurationContext(configuration)
    }
}