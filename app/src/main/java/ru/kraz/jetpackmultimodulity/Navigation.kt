package ru.kraz.jetpackmultimodulity

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kraz.feature_content.ContentRouter

interface Navigation<T> {
    fun read(): StateFlow<T>
    fun update(value: T)
    fun coup()

    class Base : Navigation<Screen>, ContentRouter {
        private val screen = MutableStateFlow<Screen>(Screen.Start)

        override fun read(): StateFlow<Screen> = screen.asStateFlow()

        override fun update(value: Screen) {
            screen.value = value
        }

        override fun openMarsAPI() {
            update(MarsAPI())
        }

        override fun coup() {
            update(Screen.Coup)
        }
    }
}

interface Screen {
    fun show(navController: NavController) = Unit

    abstract class Replace(
        private val route: String,
    ): Screen {
        override fun show(navController: NavController) = navController.navigate(route)
    }

    data object Start : Screen
    data object Coup : Screen
}

class MainContent : Screen.Replace("Main")
class MarsAPI : Screen.Replace("MarsAPI")