package com.updavid.dogwalk_job

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.updavid.dogwalk_job.core.navigation.FeatureNavGraph
import com.updavid.dogwalk_job.core.navigation.NavigationWrapper
import com.updavid.dogwalk_job.core.ui.theme.DogWalkJobTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var featureGraph: Set<@JvmSuppressWildcards FeatureNavGraph>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogWalkJobTheme {
                NavigationWrapper(navGraphs = featureGraph)
            }
        }
    }
}