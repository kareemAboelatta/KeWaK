package com.example.kewaapp.home.presentaion.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kewaapp.R
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.components.GradientButton
import com.example.kewaapp.common.ui.theme.KewaAppTheme



@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun QuizScreenStarter() {
    KewaAppTheme {
        Scaffold(
            topBar = { QuizAppBar() }, containerColor = MaterialTheme.colorScheme.background
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(PaddingDimensions.small)
            ) {
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                ProfileCard()
                Image(
                    modifier = Modifier.fillMaxSize(
                        0.7f
                    ),
                    painter = painterResource(id = R.drawable.online_edu),
                    contentDescription = null
                )


                val gradientColor = listOf(
                    MaterialTheme.colorScheme.tertiary,
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.primary,
                )
                GradientButton(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    gradientColors = gradientColor,
                    text = "Start Quiz",
                    onClick = {}
                )
            }
        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun QuizAppBar() {
    KewaAppTheme {
        TopAppBar(
            actions = {
                IconButton(modifier = Modifier.padding(5.dp), onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Menu"
                    )
                }
            },
            title = {},
            navigationIcon = {
                IconButton(
                    modifier = Modifier.padding(5.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings, contentDescription = ""
                    )
                }
            })
    }

}


@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ProfileCard() {
    KewaAppTheme {
        Box(
            Modifier.padding(PaddingDimensions.medium)
        ) {
            Box(
                Modifier.padding(
                    top = 50.dp
                )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 40.dp,
                                bottom = 10.dp,
                                start = 10.dp,
                                end = 10.dp,
                            ), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Angel Sara",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            Modifier.padding(10.dp)
                        ) {
                            InfoItem()
                            Divider(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .height(30.dp)
                                    .width(2.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                            InfoItem()
                            Divider(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .height(30.dp)
                                    .width(2.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                            InfoItem()
                        }
                    }

                }
            }

            Image(
                modifier = Modifier
                    .size(100.dp)
                    .shadow(
                        elevation = 20.dp,
                        spotColor = MaterialTheme.colorScheme.tertiary,
                        shape = CircleShape,
                        ambientColor = MaterialTheme.colorScheme.tertiary
                    )
                    .clip(CircleShape)
                    .align(Alignment.TopCenter),
                painter = painterResource(id = R.drawable.art),
                contentDescription = "Profile",
            )

        }

    }
}


@Composable
fun InfoItem() {
    Column(
        Modifier.padding(10.dp)
    ) {
        Text(
            text = "Balance",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = "Rs. 111",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}