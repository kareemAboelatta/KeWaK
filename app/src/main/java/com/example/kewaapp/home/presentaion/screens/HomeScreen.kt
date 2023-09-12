package com.example.kewaapp.home.presentaion.screens

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kewaapp.R
import com.example.kewaapp.common.ui.common.Dimensions.IconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.theme.KewaAppTheme


@Preview(
    showBackground = true,
    backgroundColor = 0xfffff
)
@Composable
fun HomeScreen() {
    Column (
        Modifier.padding(PaddingDimensions.small)
    ){

        Header("For You", imageVector = Icons.Filled.AccountCircle)
        FavoriteSubjectsGrid()

    }
}


@Composable
fun Header(
    title: String,
    imageVector: ImageVector = Icons.Rounded.Menu
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(size = IconSize)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,

            )
    }
}


// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteSubjectsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(188.dp)
    ) {
        itemsIndexed(subjects) { _, item ->
            SubjectCard(
                modifier = Modifier.fillMaxHeight(),
                drawable = item.image,
                text = item.name,
            )
        }
    }
}


@Preview(
    showBackground = true,
    heightDp = 90
)
@Composable
fun FavoriteCollectionCardPreview() {
    KewaAppTheme {
        SubjectCard(
            text = "Math",
            modifier = Modifier.padding(8.dp),
            drawable = R.drawable.math,
        )
    }
}


// Step: Favorite collection card - Material Surface
@Composable
fun SubjectCard(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = text,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}


val subjects = listOf(
    Subject(
        id = "1",
        name = "Mathematics",
        description = "Study the world of numbers, equations, and calculations.",
        image = R.drawable.math
    ),
    Subject(
        id = "2",
        name = "Science",
        description = "Explore the mysteries of the natural world.",
        image = R.drawable.science
    ),
    Subject(
        id = "3",
        name = "History",
        description = "Learn about the past events and civilizations.",
        image = R.drawable.history
    ),
    Subject(
        id = "4",
        name = "English",
        description = "Improve your language and communication skills.",
        image = R.drawable.english
    ),
    Subject(
        id = "5",
        name = "Computer Science",
        description = "Discover the world of programming and technology.",
        image = R.drawable.computer
    ),
    Subject(
        id = "6",
        name = "Physics",
        description = "Explore the fundamental principles of the physical world.",
        image = R.drawable.physics
    ),
    Subject(
        id = "7",
        name = "Art",
        description = "Express yourself through creative forms of art and design.",
        image = R.drawable.art
    ),
    Subject(
        id = "8",
        name = "Geography",
        description = "Study the Earth's landscapes, environments, and cultures.",
        image = R.drawable.geography
    )
    // Add more subjects as needed
)


data class Subject(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val image: Int,
)



