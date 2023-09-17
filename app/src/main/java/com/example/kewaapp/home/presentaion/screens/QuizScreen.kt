package com.example.kewaapp.home.presentaion.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kewaapp.common.ui.common.Dimensions.BigIconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.components.GradientButton

import com.example.kewaapp.common.ui.components.GradientProgressbar
import com.example.kewaapp.common.ui.components.GradientProgressbarPreview
import com.example.kewaapp.common.ui.theme.KewaAppTheme
import com.example.kewaapp.home.data.Quiz
import kotlin.math.absoluteValue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun QuizScreen() {
    KewaAppTheme {
        Scaffold(

            topBar = {
                TopAppBar(
                    title = {
                        Row (
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
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

                GradientProgressbar(
                    modifier = Modifier.padding(10.dp)
                )

                QuizPager()

            }
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun QuizPager() {
    val state: PagerState = rememberPagerState ( 1 )
    val animationScope = rememberCoroutineScope()
    Column {
        HorizontalPager(state = state, pageCount = 20) { page ->
            Card(
                Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (state.currentPage - page) + state
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // We animate the alpha, between 50% and 100%
                        alpha = 0.5F
                    }
            ) {
                // Card content
            }
        }
    }
}
















