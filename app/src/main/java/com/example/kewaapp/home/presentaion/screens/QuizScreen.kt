package com.example.kewaapp.home.presentaion.screens

import android.content.res.Configuration
import android.widget.Gallery
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kewaapp.R
import com.example.kewaapp.common.ui.common.Dimensions.BigIconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.components.GradientProgressbar
import com.example.kewaapp.common.ui.theme.KewaAppTheme
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
                    images.size
                }


                GradientProgressbar(
                    modifier = Modifier.padding(10.dp),
                    percentage = (((pagerState.currentPage+1).toFloat() / images.size ) * 100F)
                )

                GalleryPager(pagerState)

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

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun QuizPager(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState
    ) { page ->
        Box(Modifier
            .graphicsLayer {
                val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                // translate the contents by the size of the page, to prevent the pages from sliding in from left or right and stays in the center
                translationX = pageOffset * size.width
                // apply an alpha to fade the current page in and the old page out
                alpha = 1 - pageOffset.absoluteValue
            }
            .fillMaxSize()) {
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
        }
    }

}


val images = listOf(
    R.drawable.image,
    R.drawable.art,
    R.drawable.math,
    R.drawable.english,
    R.drawable.computer,
    R.drawable.geography,
    R.drawable.history,

)

// extension method for current page offset
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.pagerFadeTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
        translationX = pageOffset * size.width
        alpha = 1 - pageOffset.absoluteValue
    }


@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DribbbleInspirationPager() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
    ) {
        val pagerState = rememberPagerState(pageCount = { images.size })
        HorizontalPager(
            pageSpacing = 16.dp,
            beyondBoundsPageCount = 2,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(modifier = Modifier.fillMaxSize()) {
                // Contains Image and Text composables
                SongInformationCard(
                    modifier = Modifier
                        .align(Alignment.Center),
                    pagerState = pagerState,
                    page = page,
                )
            }

        }
    }
}


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
                    /* other modifiers */
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryPager( pagerState: PagerState) {


    val matrix = remember {
        ColorMatrix()
    }



    HorizontalPager(state = pagerState) { index ->
        val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction

        val imageSize by animateFloatAsState(
            targetValue = if (pageOffset != 0.0F) 0.75f else 1f, label = "",
            animationSpec = tween(durationMillis = 300)
        )


        LaunchedEffect(key1 = imageSize,){


        }
        val saturation = animateFloatAsState(
            targetValue = if (pageOffset != 0.0F) 0.2F else 1F, label = ""
        )
        matrix.setToSaturation(saturation.value)

        AsyncImage(
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
            model = ImageRequest.Builder(LocalContext.current)
                .data(images[index])
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(matrix)
        )
    }
}








