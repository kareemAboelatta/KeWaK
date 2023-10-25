package com.example.chat.ui.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.chat.ChatBubbleShape
import com.example.chat.R
import com.example.chat.domain.model.Message
import com.example.chat.domain.model.PdfMessage
import com.example.chat.domain.model.PhotoMessage
import com.example.chat.domain.model.QuizMessage
import com.example.chat.domain.model.TextMessage
import com.example.chat.domain.model.VideoMessage
import com.example.chat.fakeMessages
import com.example.chat.ui.components.SymbolAnnotationType
import com.example.chat.ui.components.messageFormatter
import com.example.common.common.Dimensions.BigIconSize
import com.example.common.common.PaddingDimensions
import io.eyram.iconsax.IconSax


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


@Preview
@Composable
fun DisplayMessageView(message: Message = fakeMessages[5]) {
    // This will simply be the given UI as it perfectly caters for text messages.

    val backgroundBubbleColor = if (true) { // is me
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Column(
        modifier = Modifier.padding(PaddingDimensions.large)
    ) {
        Surface(
            color = backgroundBubbleColor,
            shape = ChatBubbleShape
        ) {
            DisplayMessage(message = message)
        }
    }

}

@Composable
fun DisplayTextMessage(message: TextMessage) {
    // This will simply be the given UI as it perfectly caters for text messages.

    val uriHandler = LocalUriHandler.current
    val styledMessage = messageFormatter(
        text = message.text,
        primary = true
    )
    Column {
        ClickableText(
            text = styledMessage,
            style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
            modifier = Modifier.padding(16.dp),
            onClick = {
                styledMessage
                    .getStringAnnotations(start = it, end = it)
                    .firstOrNull()
                    ?.let { annotation ->
                        when (annotation.tag) {
                            SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
                            //                        SymbolAnnotationType.PERSON.name -> authorClicked(annotation.item)
                            else -> Unit
                        }
                    }
            }
        )
    }

}


@Composable
fun DisplayPhotoMessage(message: PhotoMessage) {
    // This will be similar to the text message UI, but with an added image component.
    // Modify the ChatItemBubble to display the image or directly use the given UI if the image component exists.

    // Make the photo curved using .clip modifier
    val photoShape = RoundedCornerShape(8.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingDimensions.medium)
    ) {
        Image(
            painterResource(id = R.drawable.someone_else), // Replace this with loading the image from message.photoUrl
            contentDescription = null, // Replace with a meaningful description
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(photoShape)
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.Gray) // This can be a placeholder color or you can set an actual placeholder
        )

        Spacer(modifier = Modifier.height(8.dp)) // Adds spacing between image and text

        Text(
            text = message.text,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
    }

}

@Composable
fun DisplayPdfMessage(message: PdfMessage) {
    // Shape for the background of the PDF icon for added design
    val pdfIconShape = RoundedCornerShape(PaddingDimensions.small)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingDimensions.large)
            .clip(pdfIconShape),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.pdf_file), // Load the PDF icon
            contentDescription = "PDF Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(BigIconSize)
                .background(Color.Transparent),  // Define the size of the PDF icon. Adjust as needed.
        )

        Spacer(modifier = Modifier.height(4.dp)) // Spacing between icon and title

        Text(
            text = message.title,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}


@Composable
fun DisplayVideoMessage(message: VideoMessage) {
    val playButtonIcon = painterResource(IconSax.Bold.Play) // Load the play icon

    Box(
        modifier = Modifier
            .padding(PaddingDimensions.large)
            .clip(RoundedCornerShape(8.dp))
            .height(200.dp)
            .background(MaterialTheme.colorScheme.surface) // Background color for the video thumbnail
            .clickable(onClick = {
                // Handle video play here. You can integrate with a video player library to play the video.
            }),
        contentAlignment = Alignment.Center
    ) {
        // Display the video thumbnail using Coil or any other image loading library
        AsyncImage(

            message.thumbnailUrl,
            contentDescription = "Video Thumbnail",
            contentScale = ContentScale.Crop, // Crop the image to fit
            modifier = Modifier
                .graphicsLayer {
                    alpha = 0.2f
                },
        )

        // Overlay a play button in the center
        Icon(
            painter = playButtonIcon,
            contentDescription = "Play Video",
            tint = Color.White,
            modifier = Modifier.size(BigIconSize) // Adjust the size of the play button as needed
        )
    }
}


@Composable
fun DisplayQuizMessage(message: QuizMessage) {
    // Design a unique UI for quiz messages. Maybe a question mark icon with the quiz title, indicating users can click to start the quiz.
    Text(text = "QuizMessage")

}
