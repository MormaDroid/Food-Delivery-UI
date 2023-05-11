package mohsen.morma.fooddeliveryui.dataClasses


data class FoodInfo(
    val image : List<Int>,
    val title : String,
    val price : String,
    val discountPrice : Int,
    val restaurantName :String,
    val deliveryTime : String,
    val distance :String,
    val rate : Float,
    val Description : String
)
