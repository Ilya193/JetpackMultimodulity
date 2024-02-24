package ru.kraz.jetpackmultimodulity

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val navigation: Navigation<Screen>,
) : ViewModel() {

    fun read() = navigation.read()

    fun coup() = navigation.coup()
}