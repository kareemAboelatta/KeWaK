package com.example.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chat.data.exampleUiState


@Composable
fun ChatScreen() {
    Scaffold(
        topBar = {}
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            ConversationContent(
                uiState = exampleUiState,
                navigateToProfile = { }
            )
        }
    }

}


@Preview
@Composable
fun Body() {

}