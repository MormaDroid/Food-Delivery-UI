package mohsen.morma.fooddeliveryui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.Shimmer
import mohsen.morma.fooddeliveryui.R
import mohsen.morma.fooddeliveryui.dataClasses.Category
import mohsen.morma.fooddeliveryui.dataClasses.FoodInfo
import mohsen.morma.fooddeliveryui.ui.theme.IronColor
import mohsen.morma.fooddeliveryui.ui.theme.OrangeColor
import mohsen.morma.fooddeliveryui.ui.theme.WhiteColor

lateinit var categoryList: ArrayList<Category>
lateinit var foodsListInfo: ArrayList<FoodInfo>
var isPopular = true

@Composable
fun HomeScreen(navController: NavHostController) {


    fillCategoryList()
    fillFoodInfo()

    val constraint = ConstraintSet {
        val guideLineStart = createGuidelineFromStart(24.dp)
        val guideLineEnd = createGuidelineFromEnd(24.dp)
        val guideLineTop = createGuidelineFromTop(32.dp)

        val deliverTo = createRefFor("txtDeliverTo")
        val imgLocation = createRefFor("imgLocation")
        val txtLocation = createRefFor("txtLocation")
        val searchBox = createRefFor("searchBox")
        val categoryList = createRefFor("categoryList")
        val txtPopularFood = createRefFor("txtPopularFood")
        val popularList = createRefFor("popularList")
        val txtFavoriteFood = createRefFor("txtFavoriteFood")
        val favoriteList = createRefFor("favoriteList")

        /** Text Deliver To*/
        constrain(deliverTo) {
            top.linkTo(guideLineTop)
            start.linkTo(guideLineStart, 5.dp)
        }
        /** Location Image*/
        constrain(imgLocation) {
            top.linkTo(deliverTo.bottom, 16.dp)
            start.linkTo(guideLineStart)
        }
        /** Text Location*/
        constrain(txtLocation) {
            top.linkTo(imgLocation.top)
            bottom.linkTo(imgLocation.bottom)
            start.linkTo(imgLocation.end, 4.dp)
        }
        /**SearchBox*/
        constrain(searchBox) {
            start.linkTo(guideLineStart)
            end.linkTo(guideLineEnd)
            top.linkTo(txtLocation.bottom, 24.dp)
            width = Dimension.fillToConstraints
            height = Dimension.value(48.dp)

        }
        /**Category List*/
        constrain(categoryList) {
            top.linkTo(searchBox.bottom, 24.dp)
            start.linkTo(guideLineStart)
            end.linkTo(guideLineEnd)
            width = Dimension.fillToConstraints
        }
        /**txtPopular Food*/
        constrain(txtPopularFood) {
            top.linkTo(categoryList.bottom, 20.dp)
            start.linkTo(guideLineStart)
            end.linkTo(guideLineEnd)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
        /**Popular List*/
        constrain(popularList) {
            top.linkTo(txtPopularFood.bottom, 14.dp)
            start.linkTo(guideLineStart)
            end.linkTo(guideLineEnd)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        /**txt Favorite Food*/
        constrain(txtFavoriteFood) {
            top.linkTo(popularList.bottom, 24.dp)
            start.linkTo(guideLineStart)
            end.linkTo(guideLineEnd)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        /**Favorite List*/
        constrain(favoriteList) {
            top.linkTo(txtFavoriteFood.bottom, 14.dp)
            start.linkTo(guideLineStart)
            end.linkTo(guideLineEnd)
            bottom.linkTo(parent.bottom, 80.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }


    }

    ConstraintLayout(
        constraintSet = constraint, modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        Text(
            text = "Deliver to ",
            Modifier.layoutId("txtDeliverTo"),
            fontSize = 18.sp,
            color = Color.Gray
        )

        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            Modifier.layoutId("imgLocation"),
            tint = OrangeColor
        )

        Text(
            text = "Iran, Tehran ",
            Modifier.layoutId("txtLocation"),
            fontSize = 22.sp,
            color = IronColor,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .layoutId("searchBox")
                .background(WhiteColor, RoundedCornerShape(46.dp))
        ) {
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

                Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    Modifier.size(32.dp),
                    tint = Color.Gray
                )

                Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))

                Text(text = "What did you eat today?", fontSize = 18.sp, color = Color.Gray)

            }
        }

        Column(Modifier.layoutId("categoryList")) {

            var selectedIndex by remember {
                mutableStateOf(0)
            }

            LazyRow {
                items(categoryList) { item ->
                    Row {
                        Box(Modifier
                            .background(
                                color = if (item.id == selectedIndex) OrangeColor else WhiteColor,
                                CircleShape
                            )
                            .clip(CircleShape)
                            .selectable(
                                selected = item.id == selectedIndex,
                                onClick = {
                                    selectedIndex = if (selectedIndex != item.id) item.id else 0
                                }
                            )
                            .size(64.dp), contentAlignment = Alignment.Center)
                        {

                            Image(
                                painter = item.icon,
                                contentDescription = null,
                                Modifier.size(36.dp),
                                colorFilter = ColorFilter.tint(if (item.id == selectedIndex) WhiteColor else IronColor)
                            )

                        }
                        Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
                    }
                }
            }

        }

        Row(
            modifier = Modifier.layoutId("txtPopularFood"),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular food",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = IronColor
            )
            Text(text = "See all", fontSize = 16.sp, color = Color.Gray)
        }

        Column(Modifier.layoutId("popularList")) {
            LazyRow {
                items(foodsListInfo) { food ->

                    PopularBox(food,navController)

                }
            }
        }

        Row(
            modifier = Modifier.layoutId("txtFavoriteFood"),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Favorite food",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = IronColor
            )
            Text(text = "See all", fontSize = 16.sp, color = Color.Gray)
        }

        Row(Modifier.layoutId("favoriteList")) {


            LazyRow {
                items(foodsListInfo) { food ->
                    FavoriteBox(food = food, navController)
                }
            }
        }
    }


}

@Composable
private fun PopularBox(food: FoodInfo, navController: NavHostController) {

    val gson = Gson()
    val popularFoodAsGson = gson.toJson(food)

    Box(
        Modifier
            .clickable(onClick = {
                isPopular = true
                navController.navigate("OrderScreen/$popularFoodAsGson")

            })
            .size(width = 170.dp, height = 250.dp)
            .padding(end = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .clip(RoundedCornerShape(16.dp))
                .background(WhiteColor)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 75.dp, start = 4.dp, end = 4.dp)
            ) {

                val rating = remember {
                    mutableStateOf(food.rate)
                }

                val imageFill =
                    ImageBitmap.imageResource(id = R.drawable.star_foreground)
                val imageEmpty =
                    ImageBitmap.imageResource(id = R.drawable.star_background)

                Text(
                    text = food.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 4.dp),
                    color = IronColor
                )
                Row {
                    RatingBar(
                        rating = rating.value,
                        imageFilled = imageFill,
                        imageEmpty = imageEmpty,
                        animationEnabled = true,
                        shimmer = Shimmer(
                            colors = listOf(
                                OrangeColor.copy(.9f),
                                WhiteColor.copy(.3f),
                                OrangeColor.copy(.9f)
                            )
                        ),
                        itemSize = 14.dp,
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)

                    )

                    Text(
                        text = rating.value.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = IronColor,
                        modifier = Modifier.padding(start = 6.dp)
                    )


                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.distance),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 4.dp, top = 4.dp)
                            .size(24.dp),
                        tint = OrangeColor
                    )
                    Text(
                        text = food.distance,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 2.dp, top = 4.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.deliver),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 4.dp, top = 4.dp)
                            .size(24.dp),
                        tint = OrangeColor
                    )
                    Text(
                        text = food.deliveryTime,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 2.dp, top = 4.dp)
                    )
                }


            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = food.image[0]),
                contentDescription = food.title,
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            )
        }
    }
}

@Composable
fun FavoriteBox(food: FoodInfo, navController: NavHostController) {

    val gson = Gson()
    val favoriteFoodAsGson = gson.toJson(food)


    Box(
        Modifier
            .clickable(onClick = {
                isPopular = false
                navController.navigate("OrderScreen/$favoriteFoodAsGson")
            })
            .size(width = 320.dp, height = 140.dp)
            .padding(horizontal = 10.dp)
            .background(color = WhiteColor, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .width(64.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                Modifier
                    .background(
                        Color.Red,
                        shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .clip(RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp)),
            ) {
                Text(
                    text = "50% OFF",
                    color = WhiteColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }

        }

        Row(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Image(
                painter = painterResource(id = food.image[0]),
                contentDescription = food.title,
                Modifier
                    .padding(start = 8.dp)
                    .size(120.dp)
            )

            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = food.title, color = IronColor, fontWeight = FontWeight.Bold)
                Text(text = food.restaurantName, color = Color.Gray, fontSize = 14.sp)
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 4.dp, top = 10.dp), color = Color.LightGray, thickness = 2.dp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = food.discountPrice,
                        color = OrangeColor,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = food.price,
                        color = Color.Gray,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(start = 4.dp),
                        textDecoration = TextDecoration.LineThrough
                    )
                }

            }


        }
    }
}


@Composable
fun fillFoodInfo() {
    foodsListInfo = arrayListOf()

    foodsListInfo.add(
        FoodInfo(
            listOf(R.drawable.salad_on_dish,R.drawable.fruits_salad2,R.drawable.fruits_salad3,R.drawable.fruits_salad4),
            "Fruits Salad",
            "120000 IRR.",
            "60000 IRR.",
            "Vegan Restaurant ",
            "10 min Delivery Time",
            "2Km Distance Away",
            4.8f,
            "a cold dish consisting of various types of small or cut-up fruit, usually served as a dessert or first course."
        )
    )

    foodsListInfo.add(
        FoodInfo(
            listOf(R.drawable.kebab_chicken_kebab,R.drawable.kebab_chicken_kebab2,R.drawable.chicken_kebab),
            "Mix Kebab",
            "100000 IRR.",
            "50000 IRR.",
            "Alborz Restaurant ",
            "25 min Delivery Time",
            "100m Distance Away ",
            4.6f,
            "Chicken kebabs are so simple to make, they are basically marinaded chicken, threaded onto skewers and cooked through. For this recipe we use a marinade of yoghurt with paprika, cumin, a little cinnamon, chilli flakes, garlic and lemon."
        )
    )

    foodsListInfo.add(
        FoodInfo(
            listOf(R.drawable.potato_pizza,R.drawable.potato_pizza2,R.drawable.potato_pizza3),
            "Potato Pizza",
            "120000 IRR.",
            "60000 IRR.",
            "Pizzar Restaurant ",
            "15 min Delivery Time",
            "5Km Distance Away",
            5.0f,
            "There are so many versions of this tasty, simple rustic pizza that can be found all across Italy, and it is considered a great example of peasant food since it is both filling and inexpensive to make. In many regions, thinly sliced potatoes are used to top basic pizza dough most often with no tomato sauce. Instead, the pizza is created by simply combining some shredded cheese, thin slices of potatoes, fresh herbs and a drizzle of extra virgin olive oil."

        )
    )

    foodsListInfo.add(
        FoodInfo(
            listOf(R.drawable.kebab,R.drawable.chelo_kebab,R.drawable.chelo),
            "Iranian Kebab",
            "90000 IRR.",
            "45000 IRR.",
            "Heeva Cafe Kebab ",
            "30 min Delivery Time",
            "500m Distance Away ",
            4.2f,
            "Kabab koobideh is grilled on wide, flat skewers, traditionally over hot coals, and is served with chelow (plain white rice with oil, salt and saffron), accompanied by grilled tomatoes and onions. Sumac is usually served as a tableside garnishing spice.\n"
        )
    )

}


@Composable
fun fillCategoryList(): ArrayList<Category> {
    categoryList = ArrayList()
    categoryList.add(Category(0, painterResource(id = R.drawable.all)))
    categoryList.add(Category(1, painterResource(id = R.drawable.salad)))
    categoryList.add(Category(2, painterResource(id = R.drawable.pizza)))
    categoryList.add(Category(3, painterResource(id = R.drawable.meat)))

    return categoryList
}