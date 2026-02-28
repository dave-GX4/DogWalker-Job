package com.updavid.dogwalk_job.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationWrapper(
    navGraphs: Set<FeatureNavGraph>
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Auth
    ){
        navGraphs.forEach {
            featureNavGraph -> featureNavGraph.registerGraph(this, navController)
        }
    }
}