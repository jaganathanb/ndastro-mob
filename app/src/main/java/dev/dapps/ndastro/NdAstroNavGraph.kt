package dev.dapps.ndastro

import android.app.Activity
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.dapps.ndastro.NdAstroDestinationsArgs.KATTAM_ID_ARG
import dev.dapps.ndastro.NdAstroDestinationsArgs.TITLE_ARG
import dev.dapps.ndastro.NdAstroDestinationsArgs.USER_MESSAGE_ARG
import dev.dapps.ndastro.home.ui.HomeView
import dev.dapps.ndastro.kattam.KattamEditView
import dev.dapps.ndastro.kattam.KattamView
import dev.dapps.ndastro.kattam.KattamsView
import dev.dapps.ndastro.profile.ProfileView
import dev.dapps.ndastro.utils.AppModalDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NdAstroNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = NdAstroDestinations.HOME_ROUTE,
    navActions: NdAstroNavigationActions = remember(navController) {
        NdAstroNavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NdAstroDestinations.HOME_ROUTE) {
            HomeView(openDrawer = { coroutineScope.launch { drawerState.open() } })
        }
        composable(
            NdAstroDestinations.KATTAMS_ROUTE,
            arguments = listOf(
                navArgument(USER_MESSAGE_ARG) { type = NavType.IntType; defaultValue = 0 }
            )
        ) { entry ->
            AppModalDrawer(drawerState, currentRoute, navActions) {
              KattamsView(
                    userMessage = entry.arguments?.getInt(USER_MESSAGE_ARG)!!,
                    onUserMessageDisplayed = { entry.arguments?.putInt(USER_MESSAGE_ARG, 0) },
                    onAddKattamClick = { navActions.navigateToAddEditKattam(R.string.add_kattam, null) },
                    onKattamClick = { task -> navActions.navigateToKattamParams(task.id) },
                    openDrawer = { coroutineScope.launch { drawerState.open() } }
                )
            }
        }
        composable(NdAstroDestinations.PROFILE_ROUTE) {
            AppModalDrawer(drawerState, currentRoute, navActions) {
                ProfileView(openDrawer = { coroutineScope.launch { drawerState.open() } })
            }
        }
        composable(
            NdAstroDestinations.ADD_EDIT_KATTAM_ROUTE,
            arguments = listOf(
                navArgument(TITLE_ARG) { type = NavType.IntType },
                navArgument(KATTAM_ID_ARG) { type = NavType.StringType; nullable = true },
            )
        ) { entry ->
            val kattamId = entry.arguments?.getString(KATTAM_ID_ARG)
            KattamEditView(
                topBarTitle = entry.arguments?.getInt(TITLE_ARG)!!,
                onKattamUpdate = {
                    navActions.navigateToKattams(
                        if (kattamId == null) ADD_EDIT_RESULT_OK else EDIT_RESULT_OK
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(NdAstroDestinations.KATTAM_ROUTE) {
            KattamView(
                onEditKattam = { kattamId ->
                    navActions.navigateToAddEditKattam(R.string.edit_kattam, kattamId)
                },
                onBack = { navController.popBackStack() },
                onDeleteKattam = { navActions.navigateToKattams(DELETE_RESULT_OK) }
            )
        }
    }
}

const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3
