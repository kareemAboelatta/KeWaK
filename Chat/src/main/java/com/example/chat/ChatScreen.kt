package com.example.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ChatScreen() {
    Scaffold (
        topBar = {}
    ){paddingValues->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(text = "Chat Screen")
        }
    }
}


@Preview
@Composable
fun Body() {

}