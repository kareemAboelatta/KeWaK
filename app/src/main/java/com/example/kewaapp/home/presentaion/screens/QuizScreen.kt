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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kewaapp.R
import com.example.kewaapp.auth.presentation.screens.auth.LoginDataState
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
                    viewModel.questionsState.value
                        .size
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizPager(
    pagerState: PagerState,
    viewModel: QuizViewModel = viewModel()

) {




    HorizontalPager(state = pagerState) { index ->
        val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction

        val imageSize by animateFloatAsState(
            targetValue = if (pageOffset != 0.0F) 0.9f else 1f, label = "",
            animationSpec = tween(durationMillis = 200)
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



                Spacer(Modifier.height(10.dp))

                repeat(question.answers.size) { index ->
                    Row(
                        modifier=Modifier.clickable {
                            question.selectedAnswer = index
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
                    modifier= Modifier.padding(PaddingDimensions.xLarge),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription ="Submit" )
                }

        }
    }
}

