package com.example.kewaapp.home.presentaion.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kewaapp.common.ui.common.Dimensions.BigIconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.components.GradientProgressbar
import com.example.kewaapp.common.ui.theme.KewaAppTheme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
/*@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)*/
@Composable
fun QuizScreen(
    viewModel: QuizViewModel = viewModel()
) {
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
                    viewModel.questionsState.value.size
                }

                GradientProgressbar(
                    modifier = Modifier.padding(10.dp),
                    percentage = (((pagerState.currentPage + 1).toFloat() / getQuestions().size) * 100F)
                )

                QuizPager(
                    modifier = Modifier.weight(1F),
                    pagerState,
                    list = viewModel.questionsState.value
                )

                val coroutineScope = rememberCoroutineScope()

                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if (pagerState.canScrollBackward)
                        IconButton(
                            modifier = Modifier.size(BigIconSize),
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    else
                        Box(modifier = Modifier.size(BigIconSize))



                    if (pagerState.canScrollForward)
                        IconButton(
                            modifier = Modifier.size(BigIconSize),
                            onClick = {

                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = "Next"
                            )
                        }
                    else
                        Box(modifier = Modifier.size(BigIconSize))

                }

            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    list: List<Question>

) {


    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 50.dp),

        verticalAlignment = Alignment.CenterVertically,
    ) { index ->
        val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction


        val scale = lerp(
            start = 0.9f,
            stop = 1f,
            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
        )



        QuizCard(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .scale(scale, scale)
                .clip(
                    RoundedCornerShape(16.dp)
                ),

            question = list[index],
            isLast = (index == getQuestions().size - 1)
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
    viewModel: QuizViewModel = viewModel(),

    isLast: Boolean = false
) {
    Card(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = modifier
                    .then(
                        Modifier.padding(vertical = 40.dp, horizontal = 10.dp)
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,

                ) {
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(Modifier.height(10.dp))

                repeat(question.answers.size) { index ->
                    Row(
                        modifier = Modifier.clickable {
                            viewModel.answerQuestion(questionId = question.questionId, index)
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
                                color = if (question.selectedAnswer == index) MaterialTheme.colorScheme.primary
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
                    modifier = Modifier.padding(PaddingDimensions.xLarge),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = "Submit")
                }

        }
    }
}

