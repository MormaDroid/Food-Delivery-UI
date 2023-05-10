package mohsen.morma.fooddeliveryui.bottomNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mohsen.morma.fooddeliveryui.screens.FavoriteScreen
import mohsen.morma.fooddeliveryui.screens.HomeScreen
import mohsen.morma.fooddeliveryui.screens.OrderScreen
import mohsen.morma.fooddeliveryui.screens.ProfileScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomNavBarScreen.HomeScreen.route) {
        composable(BottomNavBarScreen.HomeScreen.route) { HomeScreen(navController) }
        composable(
            "OrderScreen" + "/{food}",
            arguments = listOf(
                navArgument("food"){type = NavType.StringType}
            )
        ) { OrderScreen(
                it.arguments?.getString("food").toString()
        ) }
        composable(BottomNavBarScreen.FavoriteScreen.route) { FavoriteScreen() }
        composable(BottomNavBarScreen.ProfileScreen.route) { ProfileScreen() }
    }


}