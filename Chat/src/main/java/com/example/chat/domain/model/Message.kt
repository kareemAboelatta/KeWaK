package com.example.chat.domain.model

sealed class Message {
    abstract val id: String
    abstract val sender: User
    abstract val timestamp: Long
}


data class TextMessage(
    override val id: String,
    override val sender: User,
    override val timestamp: Long,
    val text: String
) : Message()

data class PhotoMessage(
    override val id: String,
    override val sender: User,
    override val timestamp: Long,
    val photoUrl: String,
    val text: String

) : Message()

// Continue for PDF, Video, Quiz, etc.


data class PdfMessage(
    override val id: String,
    override val sender: User,
    override val timestamp: Long,
    val pdfUrl: String,            // URL where the PDF is hosted
    val title: String              // Title or description of the PDF
) : Message()


data class VideoMessage(
    override val id: String,
    override val sender: User,
    override val timestamp: Long,
    val videoUrl: String,          // URL where the video is hosted
    val thumbnailUrl: String,      // Thumbnail image for the video preview
    val title: String              // Title or description of the video
) : Message()




data class QuizMessage(
    override val id: String,
    override val sender: User,
    override val timestamp: Long,
    val title: String,             // Title of the quiz
    val questions: List<Question>, // List of questions in the quiz
    val deadline: Long?,           // Optional deadline for quiz completion
    val description: String?       // Optional short description or instructions
) : Message()

data class Question(
    val id: String,                // Unique identifier for the question
    val text: String,              // The question text
    val options: List<String>,     // Answer options
    val correctOptionIndex: Int    // Index of the correct answer in the options list
)