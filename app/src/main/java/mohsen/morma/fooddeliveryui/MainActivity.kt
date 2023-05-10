package mohsen.morma.fooddeliveryui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mohsen.morma.fooddeliveryui.bottomNav.BottomNav
import mohsen.morma.fooddeliveryui.bottomNav.BottomNavGraph
import mohsen.morma.fooddeliveryui.ui.theme.FoodDeliveryUITheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryUITheme {

                val controller = rememberNavController()
                val navStackBackEntity by controller.currentBackStackEntryAsState()
                val currentPage = navStackBackEntity?.destination?.route
                val visibleScaffold = remember {
                    mutableStateOf(true)
                }


                BottomNavGraph(navController = controller )
                Column(Modifier.fillMaxSize(), Arrangement.Bottom) {
                    visibleScaffold.value = currentPage != "OrderScreen/{food}"
                    AnimatedVisibility(visible = visibleScaffold.value, enter = slideInVertically(initialOffsetY = {it}), exit = slideOutVertically(targetOffsetY = {it})) {
                        Scaffold(
                            bottomBar = { BottomNav(navController = controller) },
                            modifier = Modifier.requiredHeight(60.dp)
                        ) {}
                    }

                }


            }
        }
    }





}
