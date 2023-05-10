package mohsen.morma.fooddeliveryui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteScreen() {

    Column(Modifier.fillMaxSize(),Arrangement.Center,Alignment.CenterHorizontally) {
        Text(text = "Favorite", fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}