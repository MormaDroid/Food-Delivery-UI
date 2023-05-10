package mohsen.morma.fooddeliveryui.bottomNav

import mohsen.morma.fooddeliveryui.R

sealed class BottomNavBarScreen(
    val route : String,
    val title :String,
    val icon : Int,
    val fillIcon : Int
){

    object HomeScreen : BottomNavBarScreen("HomeScreen","Home", R.drawable.home,R.drawable.fill_home)
    object FavoriteScreen : BottomNavBarScreen("FavoriteScreen","Favorite", R.drawable.heart,R.drawable.fill_heart)
    object ProfileScreen : BottomNavBarScreen("ProfileScreen","Profile", R.drawable.profile,R.drawable.fill_profile)



}


