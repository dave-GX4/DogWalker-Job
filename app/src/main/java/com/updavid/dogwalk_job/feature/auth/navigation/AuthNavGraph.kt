package com.updavid.dogwalk_job.feature.auth.navigation

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.updavid.dogwalk_job.core.navigation.Auth
import com.updavid.dogwalk_job.core.navigation.FeatureNavGraph
import com.updavid.dogwalk_job.core.navigation.Maps
import com.updavid.dogwalk_job.core.navigation.Registre
import com.updavid.dogwalk_job.feature.auth.presentation.pages.AuthPage
import com.updavid.dogwalk_job.feature.auth.presentation.pages.RegistrePage
import com.updavid.dogwalk_job.feature.auth.presentation.viewmodel.RegistreViewModel
import com.updavid.dogwalk_user.feature.auth.presentation.viewmodel.AuthViewModel
import javax.inject.Inject

class AuthNavGraph @Inject constructor(): FeatureNavGraph{
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<Auth> {
            val viewModel: AuthViewModel = hiltViewModel()

            AuthPage(
                viewModel = viewModel,
                onNavigateToRegister = { navController.navigate(Registre) },
                onLoginSuccess = { navController.navigate(Maps) }
            )
        }

        navGraphBuilder.composable<Registre> {
            val viewModel: RegistreViewModel = hiltViewModel()

            RegistrePage(
                viewModel = viewModel,
                onBack = { navController.navigateUp() }
            )
        }
    }
}