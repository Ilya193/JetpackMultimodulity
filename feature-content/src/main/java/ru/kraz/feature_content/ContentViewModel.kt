package ru.kraz.feature_content

import androidx.lifecycle.ViewModel

class ContentViewModel(
    private val router: ContentRouter
) : ViewModel() {

    fun openMarsAPI() = router.openMarsAPI()
}