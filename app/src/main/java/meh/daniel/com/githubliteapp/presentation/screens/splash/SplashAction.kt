package meh.daniel.com.githubliteapp.presentation.screens.splash

sealed interface SplashAction{
    object RouteToAuth: SplashAction
    object RouteToRepositoryList: SplashAction
}
