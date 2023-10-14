package com.example.kewaapp.home.presentaion.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.kewaapp.common.NavConstants
import com.example.kewaapp.common.ui.common.Dimensions.BigIconSize
import com.example.kewaapp.common.ui.common.Dimensions.VeryBigIconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.components.GradientProgressbar
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    navController: NavController,
    quizId:String,
    viewModel: QuizViewModel
) {
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
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        val pagerState = rememberPagerState {
            viewModel.questionsState.value.size
        }
        val showSubmitButton by remember {
            derivedStateOf {
                pagerState.currentPage == viewModel.questionsState.value.size - 1
            }
        }



        Box(
            contentAlignment = Alignment.BottomEnd
        ) {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(PaddingDimensions.small)
            ) {


                GradientProgressbar(
                    modifier = Modifier.padding(10.dp),
                    percentage = (((pagerState.currentPage + 1).toFloat() / getQuestions().size) * 100F)
                )

                QuizPager(
                    modifier = Modifier.weight(1F),
                    pagerState,
                    list = viewModel.questionsState.value,
                    viewModel=viewModel
                )

                val coroutineScope = rememberCoroutineScope()




                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    AnimatedVisibility(visible = pagerState.canScrollBackward) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }) {
                            Icon(
                                modifier = Modifier.size(VeryBigIconSize),
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }



                    AnimatedVisibility(visible = pagerState.canScrollForward) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }) {
                            Icon(
                                modifier = Modifier.size(VeryBigIconSize),
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = "Next"
                            )
                        }
                    }

                }

            }

            val context = LocalContext.current

            AnimatedVisibility(visible = showSubmitButton) {
                FloatingActionButton(
                    modifier = Modifier.padding(PaddingDimensions.xLarge),
                    onClick = {
                        val isAllQuestionsAnswered =viewModel.isAllQuestionsAnswered()
                        if (isAllQuestionsAnswered){
                            navController.navigate(NavConstants.HomeRoutes.routeNameQuizResultScreen)
                        }else{

                            Toast.makeText(context, "Answer all Questions", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = "Submit")
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
    list: List<Question>,
    viewModel: QuizViewModel,
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
                .padding(top = 5.dp)
                .fillMaxSize()
                .scale(scale, scale)
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            index = index,
            question = list[index],
            viewModel = viewModel
        )
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
    viewModel: QuizViewModel,
    index: Int = 0,
    isLast: Boolean = false
) {


    Box(modifier = modifier) {


        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = PaddingDimensions.xxLarge + 12.dp)
        ) {
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {

                Column(
                    modifier = Modifier
                        .then(
                            Modifier.padding(
                                vertical = PaddingDimensions.xxLarge + 12.dp,
                                horizontal = PaddingDimensions.small
                            )
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
                                text = "${index + 1}.",
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


            }
        }


        Text(
            modifier = Modifier
                .padding(top = PaddingDimensions.small)
                .shadow(elevation = 20.dp)
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(PaddingDimensions.large))
                .background(MaterialTheme.colorScheme.background)
                .padding(PaddingDimensions.large),
            text = (index + 1).toString(),
            style = MaterialTheme.typography.titleLarge
        )


    }
}

