package com.marlonmafra.segmentedtab.demo.navigation

sealed class DemoRoute(val route: String) {
    data object ExampleList : DemoRoute("ExampleList")
    data object TitleOnly : DemoRoute("TitleOnly")
    data object TitleWithIcon : DemoRoute("TitleWithIcon")
    data object SwipeableContent : DemoRoute("SwipeableContent")
}