package dev.dapps.ndastro.utils


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.dapps.ndastro.NdAstroDestinations
import dev.dapps.ndastro.NdAstroNavigationActions
import dev.dapps.ndastro.R
import dev.dapps.ndastro.ui.theme.NDAstroTheme
import dev.dapps.ndastro.ui.theme.primaryDarkColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalResources
import dev.dapps.ndastro.ui.theme.primaryLight

@Composable
fun AppModalDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navigationActions: NdAstroNavigationActions,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute,
                navigateToHome = { navigationActions.navigateToHome() },
                navigateToKattams = { navigationActions.navigateToKattams() },
                navigateToProfile = { navigationActions.navigateToProfiles() },
                closeDrawer = { coroutineScope.launch { drawerState.close() } }
            )
        }
    ) {
        content()
    }
}

@Composable
private fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToKattams: () -> Unit,
    navigateToProfile: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val drawerWidth = LocalResources.current.getFloat(R.dimen.drawer_width_fraction)

    Surface(color = MaterialTheme.colorScheme.background, modifier = modifier.fillMaxWidth(drawerWidth)) {
        Column(modifier = modifier.fillMaxSize()) {
            DrawerHeader()
            DrawerButton(
                painter = painterResource(id = R.drawable.baseline_home_filled_24),
                label = stringResource(id = R.string.home),
                isSelected = currentRoute == NdAstroDestinations.HOME_ROUTE,
                action = {
                    navigateToHome()
                    closeDrawer()
                }
            )
            DrawerButton(
                painter = painterResource(id = R.drawable.baseline_home_filled_24),
                label = stringResource(id = R.string.home),
                isSelected = currentRoute == NdAstroDestinations.KATTAMS_ROUTE,
                action = {
                    navigateToKattams()
                    closeDrawer()
                }
            )
            DrawerButton(
                painter = painterResource(id = R.drawable.baseline_person_24),
                label = stringResource(id = R.string.profile_title),
                isSelected = currentRoute == NdAstroDestinations.PROFILE_ROUTE,
                action = {
                    navigateToProfile()
                    closeDrawer()
                }
            )
        }
    }
}

@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(primaryDarkColor)
            .height(dimensionResource(id = R.dimen.header_height))
            .padding(dimensionResource(id = R.dimen.header_padding))
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_person_32),
            contentDescription =
                stringResource(id = R.string.drawer_home_description),
            modifier = Modifier.requiredSize(dimensionResource(id = R.dimen.header_image_width))
        )
        Text(
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
private fun DrawerButton(
    painter: Painter,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tintColor = if (isSelected) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    }

    TextButton(
        onClick = action,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painter,
                contentDescription = null, // decorative
                tint = tintColor
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = tintColor
            )
        }
    }
}

@Preview("Drawer contents")
@Composable
fun PreviewAppDrawer() {
    NDAstroTheme {
        Surface {
            AppDrawer(
                currentRoute = NdAstroDestinations.KATTAMS_ROUTE,
                navigateToHome = {},
                navigateToKattams = {},
                navigateToProfile = {},
                closeDrawer = {}
            )
        }
    }
}
