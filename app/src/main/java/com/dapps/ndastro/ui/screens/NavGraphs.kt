package com.dapps.ndastro.ui.screens

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dapps.ndastro.ui.screens.dashboard.DashboardView
import com.dapps.ndastro.ui.screens.login.LoginView
import com.dapps.ndastro.ui.screens.registration.RegistrationView


/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = Navigations.Unauthenticated.NavigationRoute.route,
        startDestination = Navigations.Unauthenticated.Login.route
    ) {

        // Login
        composable(route = Navigations.Unauthenticated.Login.route) {
            LoginView (
                onNavigateToRegistration = {
                    navController.navigate(route = Navigations.Unauthenticated.Registration.route)
                },
                onNavigateToForgotPassword = {},
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = Navigations.Authenticated.NavigationRoute.route) {
                        popUpTo(route = Navigations.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        // Registration
        composable(route = Navigations.Unauthenticated.Registration.route) {
            RegistrationView(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = Navigations.Authenticated.NavigationRoute.route) {
                        popUpTo(route = Navigations.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

/**
 * Authenticated screens nav graph builder
 */
fun NavGraphBuilder.authenticatedGraph() {
    navigation(
        route = Navigations.Authenticated.NavigationRoute.route,
        startDestination = Navigations.Authenticated.Dashboard.route
    ) {
        // Dashboard
        composable(route = Navigations.Authenticated.Dashboard.route) {
            DashboardView()
        }
    }
}
