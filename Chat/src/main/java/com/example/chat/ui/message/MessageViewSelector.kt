package com.example.chat.ui.message

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.chat.domain.model.Message
import com.example.chat.domain.model.PdfMessage
import com.example.chat.domain.model.PhotoMessage
import com.example.chat.domain.model.QuizMessage
import com.example.chat.domain.model.TextMessage
import com.example.chat.domain.model.VideoMessage


@Composable
fun DisplayMessage(message: Message) {
    when (message) {
        is TextMessage -> DisplayTextMessage(message)
        is PhotoMessage -> DisplayPhotoMessage(message)
        is PdfMessage -> DisplayPdfMessage(message)
        is VideoMessage -> DisplayVideoMessage(message)
        is QuizMessage -> DisplayQuizMessage(message)
    }
}

@Composable
fun DisplayTextMessage(message: TextMessage) {
    // This will simply be the given UI as it perfectly caters for text messages.
    Text(text = "TextMessage")
}

@Composable
fun DisplayPhotoMessage(message: PhotoMessage) {
    // This will be similar to the text message UI, but with an added image component.
    // Modify the ChatItemBubble to display the image or directly use the given UI if the image component exists.

    Text(text = "PhotoMessage")

}

@Composable
fun DisplayPdfMessage(message: PdfMessage) {
    // Design a message UI with a small PDF icon and title for user to understand it's a PDF.
    // You can enhance this by providing a direct link or button to download or view the PDF.
    Text(text = "PdfMessage")

}

@Composable
fun DisplayVideoMessage(message: VideoMessage) {
    // Design a message UI with a video thumbnail and possible play icon overlay.
    // On clicking the thumbnail, the video can be played.
    Text(text = "VideoMessage")

}

@Composable
fun DisplayQuizMessage(message: QuizMessage) {
    // Design a unique UI for quiz messages. Maybe a question mark icon with the quiz title, indicating users can click to start the quiz.
    Text(text = "QuizMessage")

}
