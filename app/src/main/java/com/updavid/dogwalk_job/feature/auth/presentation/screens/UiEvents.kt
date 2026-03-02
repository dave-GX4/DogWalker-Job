package com.updavid.dogwalk_job.feature.auth.presentation.screens

sealed class UiEvents {
    data class ShowError(val message: String) : UiEvents()
    data object NavigateToMap : UiEvents()
}