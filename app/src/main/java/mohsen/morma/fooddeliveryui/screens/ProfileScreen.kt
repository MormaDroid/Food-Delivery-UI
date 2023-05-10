package mohsen.morma.fooddeliveryui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mohsen.morma.fooddeliveryui.bottomNav.BottomNavBarScreen

@Composable
fun ProfileScreen() {


    Column(Modifier.fillMaxSize(),Arrangement.Center,Alignment.CenterHorizontally) {
        Text(text = "Profile", fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}