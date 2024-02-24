package ru.kraz.jetpackmultimodulity

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.kraz.feature_content.ContentRouter
import ru.kraz.feature_content.ContentViewModel

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(module {
                val navigation = Navigation.Base()
                single<Navigation<Screen>> {
                    navigation
                }

                single<ContentRouter> {
                    navigation
                }

                viewModel<MainViewModel>() {
                    MainViewModel(get())
                }

                viewModel<ContentViewModel>() {
                    ContentViewModel(get())
                }
            })
        }
    }
}