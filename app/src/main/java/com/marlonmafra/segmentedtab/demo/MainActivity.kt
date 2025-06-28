package com.marlonmafra.segmentedtab.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marlonmafra.segmentedtab.demo.navigation.DemoRoute

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Content() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        var selectedItemTitle by remember { mutableStateOf("Segmented Tab Example") }

        LaunchedEffect(navBackStackEntry?.destination?.route) {
            selectedItemTitle = when (navBackStackEntry?.destination?.route) {
                DemoRoute.TitleOnly.route -> "Title Only"
                DemoRoute.TitleWithIcon.route -> "Title with Icon"
                DemoRoute.SwipeableContent.route -> "Swipeable Content"
                else -> "Segmented Tab Example"
            }
        }
        ThemeAppTheme(content = {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(selectedItemTitle) },
                        navigationIcon = {
                            if (navBackStackEntry?.isExampleListRoute() == false) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        },
                    )
                },
            ) { paddingValue ->
                NavHost(
                    modifier = Modifier.padding(paddingValue),
                    navController = navController,
                    startDestination = DemoRoute.ExampleList.route
                ) {
                    composable(route = DemoRoute.ExampleList.route) {
                        ExampleList { navController.navigate(it) }
                    }

                    composable(route = DemoRoute.TitleOnly.route) {
                        TitleOnly()
                    }

                    composable(route = DemoRoute.TitleWithIcon.route) {
                        TabWithIcon()
                    }

                    composable(route = DemoRoute.SwipeableContent.route) {
                        SwipeableContent()
                    }
                }
            }
        })

    }

    private fun NavBackStackEntry?.isExampleListRoute(): Boolean {
        return this?.destination?.route == DemoRoute.ExampleList.route
    }

    @Preview(showBackground = true)
    @Composable
    fun MainActivityPreview() {
        Content()
    }
}
