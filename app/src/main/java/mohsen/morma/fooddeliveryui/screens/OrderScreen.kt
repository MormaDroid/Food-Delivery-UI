package mohsen.morma.fooddeliveryui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.Shimmer
import mohsen.morma.fooddeliveryui.R
import mohsen.morma.fooddeliveryui.dataClasses.FoodInfo
import mohsen.morma.fooddeliveryui.dataClasses.FoodSize
import mohsen.morma.fooddeliveryui.ui.theme.BackgroundColor
import mohsen.morma.fooddeliveryui.ui.theme.IronColor
import mohsen.morma.fooddeliveryui.ui.theme.OrangeColor
import mohsen.morma.fooddeliveryui.ui.theme.WhiteColor
import kotlin.math.absoluteValue

lateinit var sizeList: ArrayList<FoodSize>
@Composable
fun OrderScreen(foodGson: String) {

    fillSizeList()

    val pagerState = rememberPagerState(initialPage = 1)
    val foodAsGson = Gson().fromJson(foodGson, FoodInfo::class.java)
    val sliderList = foodAsGson.image

    val const = ConstraintSet {
        val imageSlider = createRefFor("imageSlider")
        val deliverDistance = createRefFor("deliverDistance")
        val txtTitle = createRefFor("txtTitle")
        val favoriteBox = createRefFor("favoriteBox")
        val ratingBar = createRefFor("ratingBar")
        val txtRating = createRefFor("txtRating")
        val descriptionHead = createRefFor("descriptionHead")
        val descriptionText = createRefFor("descriptionText")
        val sizeRow = createRefFor("sizeRow")
        val addToCart = createRefFor("addToCart")
        val guideStart = createGuidelineFromStart(32.dp)
        val guideEnd = createGuidelineFromEnd(32.dp)

        /**Image Slider*/
        constrain(imageSlider) {
            top.linkTo(parent.top, 24.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        /**deliverDistance Row*/
        constrain(deliverDistance) {
            top.linkTo(imageSlider.bottom)
            start.linkTo(guideStart)
            end.linkTo(guideEnd)
            width = Dimension.fillToConstraints
        }
        /**txtTitle*/
        constrain(txtTitle) {
            top.linkTo(deliverDistance.bottom, 24.dp)
            start.linkTo(guideStart)
        }
        /**favoriteBox*/
        constrain(favoriteBox) {
            top.linkTo(txtTitle.top)
            bottom.linkTo(txtTitle.bottom)
            end.linkTo(guideEnd)
        }
        /**ratingBar*/
        constrain(ratingBar) {
            top.linkTo(txtTitle.bottom, 14.dp)
            start.linkTo(guideStart)
        }
        /**txtRating*/
        constrain(txtRating) {
            top.linkTo(ratingBar.top)
            bottom.linkTo(ratingBar.bottom)
            start.linkTo(ratingBar.end, 8.dp)
        }
        /**descriptionHead*/
        constrain(descriptionHead) {
            top.linkTo(ratingBar.bottom, 24.dp)
            start.linkTo(guideStart)
        }
        /**descriptionText*/
        constrain(descriptionText) {
            top.linkTo(descriptionHead.bottom, 14.dp)
            start.linkTo(guideStart)
            end.linkTo(guideEnd)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
        /**addToCart*/
        constrain(addToCart) {
            bottom.linkTo(parent.bottom)
        }
        /**sizeRow*/
        constrain(sizeRow){
            bottom.linkTo(addToCart.top,24.dp)
            start.linkTo(guideStart)
            end.linkTo(guideEnd)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
    }

    ConstraintLayout(constraintSet = const, Modifier.fillMaxSize()) {

        HorizontalPager(
            count = sliderList.size,
            state = pagerState,
            modifier = Modifier.layoutId("imageSlider"),
            contentPadding = PaddingValues(horizontal = 100.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->

            Image(
                painter = painterResource(id = foodAsGson.image[page]),
                contentDescription = null,

                modifier = Modifier
                    .size(300.dp)
                    .graphicsLayer {

                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.6f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )

                    }

            )
        }

        Row(Modifier.layoutId("deliverDistance"), verticalAlignment = Alignment.CenterVertically) {

            /**Distance Views*/
            Icon(
                painter = painterResource(id = R.drawable.distance),
                contentDescription = null,
                Modifier.layoutId("distanceIcon"),
                tint = OrangeColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = foodAsGson.distance,
                Modifier.layoutId("txtDistance"),
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            /**Deliver Views*/
            Icon(
                painter = painterResource(id = R.drawable.deliver),
                contentDescription = null,
                Modifier.layoutId("deliverIcon"),
                tint = OrangeColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = foodAsGson.deliveryTime,
                Modifier.layoutId("txtDeliver"),
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Text(
            text = foodAsGson.title,
            Modifier.layoutId("txtTitle"),
            color = IronColor,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Box(
            modifier = Modifier
                .layoutId("favoriteBox")
                .background(Color.White, CircleShape)
                .clip(CircleShape)
                .size(48.dp), contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(id = R.drawable.fill_heart),
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )

        }


        val rating = remember {
            mutableStateOf(foodAsGson.rate)
        }

        RatingBar(
            rating = rating.value,
            painterEmpty = painterResource(id = R.drawable.star_background),
            painterFilled = painterResource(id = R.drawable.star_foreground),
            modifier = Modifier.layoutId("ratingBar"),
            itemSize = 18.dp,
            animationEnabled = true,
            shimmer = Shimmer(
                colors = listOf(
                    OrangeColor.copy(.9f),
                    WhiteColor.copy(.3f),
                    OrangeColor.copy(.9f)
                )
            )
        )

        Text(
            text = "${foodAsGson.rate} Rating",
            color = Color.Gray,
            modifier = Modifier.layoutId("txtRating"),
            fontSize = 17.sp
        )

        Text(
            text = "Description",
            Modifier.layoutId("descriptionHead"),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = IronColor
        )
        Text(
            text = foodAsGson.Description,
            Modifier.layoutId("descriptionText"),
            fontSize = 16.sp,
            color = Color.Gray
        )

        var selectedIndex by remember {
            mutableStateOf(0)
        }

        Row(
            Modifier.layoutId("sizeRow"),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Text(text = "Size", fontWeight = FontWeight.Bold,color = IronColor, fontSize = 20.sp)
            Row {
                LazyRow {
                    items(sizeList) { item ->
                        Row {
                            Box(Modifier
                                .border(width = if (item.id == selectedIndex)1.dp else (-1).dp,IronColor, RoundedCornerShape(24.dp) )
                                .background(color = if (item.id == selectedIndex) BackgroundColor else WhiteColor,RoundedCornerShape(24.dp) )
                                .clip(RoundedCornerShape(24.dp))
                                .selectable(
                                    selected = item.id == selectedIndex,
                                    onClick = {
                                        selectedIndex = if (selectedIndex != item.id) item.id else 0
                                    }
                                )
                                .size(width = 100.dp, height = 48.dp), contentAlignment = Alignment.Center)
                            {

                                Row (verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = R.drawable.food_size),
                                        contentDescription = null,
                                        Modifier.size(item.imageSize),
                                        colorFilter = ColorFilter.tint(IronColor)
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Text(text = item.size )
                                }

                            }
                            Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
                        }
                    }
                }
            }
        }

        val countFood = remember {
            mutableStateOf(1)
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .layoutId("addToCart")
            .height(160.dp)) {

            Row(Modifier
                    .fillMaxWidth()
                    .padding(10.dp), Arrangement.SpaceBetween,Alignment.CenterVertically) {



                Column (Modifier.padding(10.dp), verticalArrangement = Arrangement.Center){
                    Text(text = "Price", color = Color.Gray, fontSize = 14.sp)
                    Text(text = "${(foodAsGson.discountPrice * countFood.value)} IRR.", color = IronColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Box(modifier = Modifier
                        .clickable(onClick = { if (countFood.value > 1) countFood.value-- })
                        .background(Color.White, CircleShape)
                        .clip(CircleShape)
                        .border(1.dp, IronColor, CircleShape)
                        .size(40.dp),contentAlignment = Alignment.Center){
                        Icon(painter = painterResource(id = R.drawable.remove_vector), contentDescription =null, tint = OrangeColor )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = countFood.value.toString(), color = IronColor, fontSize = 20.sp)
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(modifier = Modifier
                        .clickable(onClick = { countFood.value++ })
                        .background(Color.White, CircleShape)
                        .clip(CircleShape)
                        .border(1.dp, IronColor, CircleShape)
                        .size(40.dp),contentAlignment = Alignment.Center){
                        Icon(painter = painterResource(id = R.drawable.add_vector), contentDescription =null, tint = OrangeColor )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                }

            }

            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)){
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(start = 32.dp, end = 32.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = OrangeColor)
                ) {
                    Text(text = "Add To Cart", color = Color.White, fontWeight = FontWeight.Bold)
                }


            }


        }


    }
}

@Composable
fun fillSizeList(): ArrayList<FoodSize> {
    sizeList = ArrayList()
    sizeList.add(FoodSize(0, 24.dp,"Normal"))
    sizeList.add(FoodSize(1,30.dp,"Large"))

    return sizeList
}



