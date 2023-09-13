package com.example.kewaapp.home.presentaion.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kewaapp.common.ui.common.Dimensions.IconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.theme.KewaAppTheme
import com.example.kewaapp.home.data.Quiz
import com.example.kewaapp.home.data.fakeQuizzes
import com.example.kewaapp.home.data.subjects


/*
@Preview()
@Composable
fun HomeScreenPreview(){
    KewaAppTheme {
        HomeScreen()
    }
}
*/

@Composable
fun HomeScreen() {
    Column(
        Modifier.padding(PaddingDimensions.small)
    ) {

        Header("For You", imageVector = Icons.Filled.AccountCircle)
        ForYouSubjectsGrid()
        Spacer(modifier = Modifier.height(10.dp))
        Header("Recently Viewed", imageVector = Icons.Filled.Menu)
        Spacer(modifier = Modifier.height(5.dp))

        RecentSubjectsGrid()

    }
}


@Preview
@Composable
fun QuizItemPreview() {
    KewaAppTheme {
        QuizItem()
    }
}

@Composable
fun QuizItem() {
    Card {
        Column(
            modifier = Modifier.padding(PaddingDimensions.large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(PaddingDimensions.small),
                text = "Quiz 1",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "10 Question",
                style = MaterialTheme.typography.bodyMedium,
            )


        }
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
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}


// Step: Favorite collections grid - LazyGrid
@Composable
fun ForYouSubjectsGrid(
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

@Composable
fun RecentSubjectsGrid(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        itemsIndexed(subjects.reversed()) { _, item ->
            RecentlySubjectCard(
                modifier = Modifier.fillMaxHeight(),
                drawable = item.image,
                text = item.name,
            )
        }
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
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp

            )
        }
    }
}

@Composable
fun RecentlySubjectCard(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            )
        }
    }
}


@Preview
@Composable
fun QuizItemCardPrev() {
    KewaAppTheme {
        QuizItemCard(quiz = fakeQuizzes[0])
    }
}
@Composable
fun QuizItemCard(quiz: Quiz) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = quiz.title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = quiz.description,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Questions: ${quiz.numberOfQuestions}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Author: ${quiz.author}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                quiz.categoryTags.forEach { tag ->
                    SuggestionChip(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = { },
                        label = { Text(tag, fontSize = 12.sp) }
                    )
                }
            }
        }
    }
}




