package dev.dapps.ndastro

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

import dev.dapps.ndastro.NdAstroScreens.KATTAMS_SCREEN
import dev.dapps.ndastro.NdAstroScreens.PROFILE_SCREEN
import dev.dapps.ndastro.NdAstroScreens.KATTAM_PARAMS_SCREEN
import dev.dapps.ndastro.NdAstroScreens.ADD_EDIT_KATTAM_SCREEN
import dev.dapps.ndastro.NdAstroDestinationsArgs.USER_MESSAGE_ARG
import dev.dapps.ndastro.NdAstroDestinationsArgs.KATTAM_ID_ARG
import dev.dapps.ndastro.NdAstroDestinationsArgs.TITLE_ARG
import dev.dapps.ndastro.NdAstroScreens.HOME


/**
 * Screens used in [NdAstroDestinations]
 */
private object NdAstroScreens {
    const val HOME = "home"
    const val KATTAMS_SCREEN = "kattams"
    const val KATTAM_SCREEN = "kattam"
    const val PROFILE_SCREEN = "profile"
    const val KATTAM_PARAMS_SCREEN = "kattamParams"
    const val ADD_EDIT_KATTAM_SCREEN = "addEditKattam"
}

/**
 * Arguments used in [NdAstroDestinations] routes
 */
object NdAstroDestinationsArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val KATTAM_ID_ARG = "kattamId"
    const val TITLE_ARG = "title"
}

object NdAstroDestinations {
    const val HOME_ROUTE = HOME
    const val KATTAMS_ROUTE = "$KATTAMS_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val PROFILE_ROUTE = PROFILE_SCREEN
    const val KATTAM_ROUTE = "$KATTAM_PARAMS_SCREEN/{$KATTAM_ID_ARG}"
    const val ADD_EDIT_KATTAM_ROUTE = "$ADD_EDIT_KATTAM_SCREEN/{$TITLE_ARG}?$KATTAM_ID_ARG={$KATTAM_ID_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class NdAstroNavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(NdAstroDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    fun navigateToKattams(userMessage: Int = 0) {
        val navigatesFromDrawer = userMessage == 0
        navController.navigate(
            KATTAMS_SCREEN.let {
                if (userMessage != 0) "$it?$USER_MESSAGE_ARG=$userMessage" else it
            }
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = !navigatesFromDrawer
                saveState = navigatesFromDrawer
            }
            launchSingleTop = true
            restoreState = navigatesFromDrawer
        }
    }

    fun navigateToProfiles() {
        navController.navigate(NdAstroDestinations.PROFILE_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToKattamParams(kattamId: String) {
        navController.navigate("$KATTAM_PARAMS_SCREEN/$kattamId")
    }

    fun navigateToAddEditKattam(title: Int, kattamId: String?) {
        navController.navigate(
            "$ADD_EDIT_KATTAM_SCREEN/$title".let {
                if (kattamId != null) "$it?$KATTAM_ID_ARG=$kattamId" else it
            }
        )
    }
}
