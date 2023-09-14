package com.example.kewaapp.home.presentaion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.*
import com.example.kewaapp.R
import com.example.kewaapp.common.ui.theme.KewaAppTheme
import kotlin.math.min
import kotlin.math.sqrt


private fun getNames() = List(51) { i -> "Kareem # $i" }









@Preview
@Composable
fun TextShapes() {

    Box(modifier = Modifier
        .size(100.dp)
        .padding(5.dp)
        .drawWithContent {
            drawContent()
            drawPath(
                path = drawCustomHexagonPath(size),
                color = Color.Red,
                style = Stroke(
                    width = 10.dp.toPx(),
                    pathEffect = PathEffect.cornerPathEffect(40f)
                )
            )
        }
        .wrapContentSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.art),
            contentDescription = "My Hexagon Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .wrapContentSize()
                .graphicsLayer {
                    shadowElevation = 8.dp.toPx()
                    shape = hexagonShape
                    clip = true
                }
                .background(color = Color.Cyan)
        )

    }
}








@Composable
fun HexagonList() {





    KewaAppTheme {
        LazyColumn(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy((-92).dp)

        ) {
            itemsIndexed(getNames()) { index, it ->
                HexagonItem(it, index)
            }
        }
    }

}


@Composable
fun HexagonItem(name: String = "Kareem", index: Int) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = if (index % 2 == 0) Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .rotate(90f)
                .fillMaxWidth(0.62f)
                .height(185.dp)
                .clip(hexagonShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .rotate(-90f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
            }
        }
    }
}


val hexagonShape = HexagonShape()

class HexagonShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawCustomHexagonPath(size)
        )
    }
}

fun drawCustomHexagonPath(size: Size): Path {
    return Path().apply {
        val radius = min(size.width / 2f, size.height / 2f)
        customHexagon(radius, size)
    }
}

fun Path.customHexagon(radius: Float, size: Size) {
    val triangleHeight = (sqrt(3.0) * radius / 2)
    val centerX = size.width / 2
    val centerY = size.height / 2

    moveTo(centerX, centerY + radius)
    lineTo((centerX - triangleHeight).toFloat(), centerY + radius/2)
    lineTo((centerX - triangleHeight).toFloat(), centerY - radius/2)
    lineTo(centerX, centerY - radius)
    lineTo((centerX + triangleHeight).toFloat(), centerY - radius/2)
    lineTo((centerX + triangleHeight).toFloat(), centerY + radius/2)

    close()
}


