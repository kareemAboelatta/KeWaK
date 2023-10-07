package com.example.kewaapp.home.presentaion.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kewaapp.R
import com.example.kewaapp.common.ui.common.Dimensions.BigIconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.components.GradientProgressbar
import com.example.kewaapp.common.ui.theme.KewaAppTheme
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
/*@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)*/
@Composable
fun QuizScreen() {
    KewaAppTheme {
        Scaffold(

            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Quiz",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier
                                .size(BigIconSize)
                                .padding(5.dp),
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack, contentDescription = ""
                            )
                        }
                    })
            },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(PaddingDimensions.small)
            ) {

                val pagerState = rememberPagerState {
                    getQuestions().size
                }


                GradientProgressbar(
                    modifier = Modifier.padding(10.dp),
                    percentage = (((pagerState.currentPage + 1).toFloat() / getQuestions().size) * 100F)
                )

                QuizPager(pagerState)

            }
        }
    }

}


/*
@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun QuizPager() {
    val pagerState = rememberPagerState(pageCount = {
        10
    })
    HorizontalPager(
        modifier = Modifier
            .fillMaxSize()

        ,

        state = pagerState,
        pageSpacing = 5.dp,
        pageSize = object : PageSize {
            override fun Density.calculateMainAxisPageSize(
                availableSpace: Int,
                pageSpacing: Int
            ): Int {
                return ((availableSpace - 1.5 * pageSpacing) * 0.5f).toInt()
            }
        },

    ) { page ->
        Card(
            Modifier
                .size(300.dp)
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            // Card content
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.art),
                contentDescription = null
            )
        }
    }
}
*/


// extension method for current page offset
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}


/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongInformationCard(
    pagerState: PagerState,
    page: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(modifier = modifier) {
            val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "",

                modifier = modifier
                    .fillMaxSize()
                    */
/* other modifiers *//*

                    .graphicsLayer {
                        // get a scale value between 1 and 1.75f, 1.75 will be when its resting,
                        // 1f is the smallest it'll be when not the focused page
                        val scale = lerp(1f, 2f, pageOffset)
                        // apply the scale equally to both X and Y, to not distort the image
                        scaleX = scale
                        scaleY = scale
                    },
                //..
            )

        }
    }
}

*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizPager(pagerState: PagerState) {


    HorizontalPager(state = pagerState) { index ->
        val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction

        val imageSize by animateFloatAsState(
            targetValue = if (pageOffset != 0.0F) 0.75f else 1f, label = "",
            animationSpec = tween(durationMillis = 300)
        )



        QuizCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .graphicsLayer {
                    scaleX = imageSize
                    scaleY = imageSize
                }
                .animateContentSize()
                .clip(RoundedCornerShape(16.dp)),
            question = getQuestions()[index],
            isLast = (index == getQuestions().size-1)
        )
    }
}


@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun QuizCardPrev() {
    KewaAppTheme {
        QuizCard()
    }
}

@Composable
fun QuizCard(
    modifier: Modifier = Modifier,
    question: Question = Question(
        "q1",
        "What does API stand for in Android development?",
        listOf(
            "Application Programming Interface",
            "Android Programming Interface",
            "Application Program Interface",
            "Android Program Interface"
        ),
        0
    ),
    isSelected: Boolean = false,
    isLast : Boolean = false
) {
    Card(
        modifier = modifier
    ) {
        Box (
            contentAlignment= Alignment.BottomEnd
        ){
            Column(
                modifier = modifier.then(
                    Modifier.padding(10.dp)
                ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)

                )

                var selectedAnswer by remember {
                    mutableIntStateOf(-1)
                }

                Spacer(Modifier.height(10.dp))

                repeat(question.answers.size) { index ->
                    Row(
                        modifier=Modifier.clickable {
                            selectedAnswer = index
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$index.",
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PaddingDimensions.small),
                            border = BorderStroke(
                                width = 2.dp,
                                color = if (selectedAnswer == index) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(PaddingDimensions.medium),
                                text = question.answers[index],
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }


            }

            if (isLast)
                FloatingActionButton(
                    modifier= Modifier.padding(PaddingDimensions.xLarge),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription ="Submit" )
                }

        }
    }
}


data class Question(
    val questionId: String,
    val question: String,
    val answers: List<String>,
    val numberOfCorrectAnswer: Int,
)

fun getQuestions(): List<Question> {
    return listOf(
        Question(
            "q1",
            "What does API stand for in Android development?",
            listOf(
                "Application Programming Interface",
                "Android Programming Interface",
                "Application Program Interface",
                "Android Program Interface"
            ),
            0
        ),
        Question(
            "q2",
            "Which layout is used to align the children of a RelativeLayout relative to each other?",
            listOf("LinearLayout", "FrameLayout", "GridLayout", "ConstraintLayout"),
            3
        ),
        Question(
            "q3",
            "What is the purpose of the 'adb' tool in Android development?",
            listOf(
                "Android Debug Bridge",
                "Application Development Bridge",
                "Android Development Builder",
                "Application Debugging Bridge"
            ),
            0
        ),
        Question(
            "q4",
            "In Android, what is an Intent used for?",
            listOf(
                "To display an activity",
                "To perform an action",
                "To declare permissions",
                "To define layouts"
            ),
            1
        ),
        Question(
            "q5",
            "What is the purpose of the 'AndroidManifest.xml' file?",
            listOf(
                "Define the app's layout",
                "Specify app permissions",
                "Declare activities",
                "Handle user input"
            ),
            2
        ),
        Question(
            "q6",
            "Which component is responsible for managing the app's lifecycle in Android?",
            listOf("Activity", "Fragment", "Service", "Application"),
            3
        ),
        Question(
            "q7",
            "What is the minimum Android API level required for using Jetpack Compose?",
            listOf("API level 21", "API level 19", "API level 14", "API level 26"),
            0
        ),
        Question(
            "q8",
            "In Android, what is the purpose of the 'ViewModel' class?",
            listOf(
                "Handle UI-related data",
                "Perform network operations",
                "Define app layouts",
                "Handle user input"
            ),
            0
        ),
        Question(
            "q9",
            "Which Gradle file is used to configure dependencies in an Android project?",
            listOf(
                "build.gradle (Project)",
                "build.gradle (Module)",
                "settings.gradle",
                "gradle.properties"
            ),
            1
        ),
        Question(
            "q10",
            "What is the purpose of the 'R' class in Android?",
            listOf(
                "Handle resources",
                "Define layouts",
                "Manage permissions",
                "Run background tasks"
            ),
            0
        )
    )
}















