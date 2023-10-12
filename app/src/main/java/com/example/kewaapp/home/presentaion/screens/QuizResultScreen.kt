package com.example.kewaapp.home.presentaion.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kewaapp.R
import com.example.kewaapp.common.ui.common.Dimensions
import com.example.kewaapp.common.ui.common.Dimensions.IconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.theme.KewaAppTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun QuizResultScreen(
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
                                .size(Dimensions.BigIconSize)
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
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(PaddingDimensions.small)
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingDimensions.xxLarge)
                ) {
                  Column (
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(PaddingDimensions.xxLarge),
                      horizontalAlignment = Alignment.CenterHorizontally
                  ){
                      ComposeLottieAnimation(modifier = Modifier.size(200.dp))
                      Text(
                          text="Congrats",
                          style = MaterialTheme.typography.titleMedium
                      )

                      Text(
                          text = "90% Score",
                          style = MaterialTheme.typography.titleLarge.copy(
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.primary
                              )

                      )



                      Text(
                          text="Quiz completed successfully",
                          style = MaterialTheme.typography.bodyMedium
                      )

                      Row {
                          Text(
                              text = "20 ",
                              style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.secondary)
                          )

                          Text(
                              text = "/ 20",
                              style = MaterialTheme.typography.headlineLarge
                          )

                      }


                      Text(
                          text="Earned coins",
                          style = MaterialTheme.typography.bodyMedium
                      )


                      Row(
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Image(
                              modifier = Modifier.size(IconSize),
                              painter = painterResource(id = R.drawable.coin),
                              contentDescription = "coin"
                          )
                          Spacer(modifier = Modifier.width(PaddingDimensions.small))
                          Text(
                              text="500",
                              style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                          )
                          
                      }


                      Row(
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Button(
                              modifier = Modifier.clip(RoundedCornerShape(PaddingDimensions.small)),
                              onClick = { /*TODO*/ }) {
                              Icon(imageVector = Icons.Filled.Share, contentDescription = "share")
                              Text(text = "Share Result")
                          }
                          Spacer(modifier = Modifier.width(PaddingDimensions.small))
                          ElevatedButton(
                              onClick = { /*TODO*/ }) {
                              Text(text = "Take new quiz")
                          }

                      }




                  }
                }



            }


        }
    }

}


@Composable
fun ComposeLottieAnimation(modifier: Modifier) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_winning_cup))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}


