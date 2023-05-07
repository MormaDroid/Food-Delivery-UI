package mohsen.morma.fooddeliveryui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import mohsen.morma.fooddeliveryui.ui.theme.BackgroundColor
import mohsen.morma.fooddeliveryui.ui.theme.FoodDeliveryUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryUITheme {
                Column(Modifier.fillMaxSize().background(color = BackgroundColor)){

                }
            }
        }
    }
}
