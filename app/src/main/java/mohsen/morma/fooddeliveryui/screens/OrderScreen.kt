package mohsen.morma.fooddeliveryui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.gson.Gson
import mohsen.morma.fooddeliveryui.dataClasses.FoodInfo

@Composable
fun OrderScreen(foodGson:String) {

    Column(Modifier.fillMaxSize()) {
        val foodAsGson = Gson().fromJson(foodGson,FoodInfo::class.java)
        Text(text = foodAsGson.deliveryTime)
    }
}