package mohsen.morma.fooddeliveryui.bottomNav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import mohsen.morma.fooddeliveryui.ui.theme.BackgroundColor
import mohsen.morma.fooddeliveryui.ui.theme.IronColor
import mohsen.morma.fooddeliveryui.ui.theme.OrangeColor
import mohsen.morma.fooddeliveryui.ui.theme.WhiteColor

@Composable
fun BottomNav(navController: NavHostController) {
    val screens = listOf(
        BottomNavBarScreen.HomeScreen,
        BottomNavBarScreen.FavoriteScreen,
        BottomNavBarScreen.ProfileScreen
    )

    val navStackBackEntity by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntity?.destination

    Row(
        Modifier
            .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)
            .background(WhiteColor)
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun AddItem(
    screen: BottomNavBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val contentColor = if (selected) OrangeColor else IronColor

    val backgroundColor = if (selected) BackgroundColor else Color.Transparent

    Box(
        Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .height(40.dp)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            Arrangement.spacedBy(4.dp),
            Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.fillIcon else screen.icon),
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(height = 48.dp, width = 24.dp)
            )
            AnimatedVisibility(visible = selected) {
                Text(text = screen.title, fontWeight = FontWeight.Bold, color = contentColor)
            }
        }
    }

}