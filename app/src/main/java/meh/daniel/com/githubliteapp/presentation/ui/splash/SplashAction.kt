package meh.daniel.com.githubliteapp.presentation.ui.splash

sealed interface SplashAction{
    object RouteToAuth: SplashAction
    object RouteToRepositoryList: SplashAction
}
