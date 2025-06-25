package kg.aza.testbinbank.presentation

import android.content.Context
import android.content.SharedPreferences
import kg.aza.testbinbank.core.KoinModule
import kg.aza.testbinbank.data.MainInfrastructureModule
import kg.aza.testbinbank.domain.MainLogicModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import kg.aza.testbinbank.presentation.view.mainScreen.BankViewModel
import kg.balance.reforged.foundation.infrastructure.local.preferences.UserPreferences
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext

object MainModule : KoinModule() {
    override fun create(): Module {
        return module {
            includes(
                MainInfrastructureModule.create(),
                MainLogicModule.create(),
            )
            viewModelOf(::BankViewModel)
            single<SharedPreferences> {
                androidContext().getSharedPreferences("your_prefs_name", Context.MODE_PRIVATE)
            }

            single<Json> {
                Json { ignoreUnknownKeys = true }
            }

            single {
                UserPreferences(
                    preferences = get(),
                    json = get()
                )
            }

        }
    }
}