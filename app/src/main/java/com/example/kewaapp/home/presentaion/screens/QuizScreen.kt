package com.example.kewaapp.home.presentaion.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kewaapp.R
import com.example.kewaapp.common.ui.common.Dimensions.BigIconSize
import com.example.kewaapp.common.ui.common.Dimensions.IconSize
import com.example.kewaapp.common.ui.common.PaddingDimensions
import com.example.kewaapp.common.ui.components.GradientButton
import com.example.kewaapp.common.ui.components.GradientProgressbar
import com.example.kewaapp.common.ui.components.GradientProgressbarPreview
import com.example.kewaapp.common.ui.theme.KewaAppTheme
import com.example.kewaapp.home.data.Quiz


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



            }
        }
    }

}
















