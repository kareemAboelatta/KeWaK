/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.chat

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.toMutableStateList
import com.example.chat.EMOJIS.EMOJI_FLAMINGO
import com.example.chat.EMOJIS.EMOJI_PINK_HEART
import com.example.chat.R
import com.example.chat.domain.model.Message
import com.example.chat.domain.model.PdfMessage
import com.example.chat.domain.model.PhotoMessage
import com.example.chat.domain.model.Question
import com.example.chat.domain.model.QuizMessage
import com.example.chat.domain.model.TextMessage
import com.example.chat.domain.model.User
import com.example.chat.domain.model.UserRole
import com.example.chat.domain.model.VideoMessage

class ConversationUiState(
    val channelName: String,
    val channelMembers: Int,
    initialMessages: List<Message>
) {
    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages

    fun addMessage(msg: Message) {
        _messages.add(0, msg) // Add to the beginning of the list
    }
}

val fakeUsers = listOf(
    User(
        id = "u1",
        username = "Alice",
        email = "alice@example.com",
        profileImageUrl = "https://imgv3.fotor.com/images/cover-photo-image/a-beautiful-girl-with-gray-hair-and-lucxy-neckless-generated-by-Fotor-AI.jpg",
        role = UserRole.TEACHER,
        lastSeen = System.currentTimeMillis(),
        dateJoined = System.currentTimeMillis() - 10000000,
        bio = "Mathematics Teacher"
    ),
    User(
        id = "u2",
        username = "Bob",
        email = "bob@example.com",
        profileImageUrl = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?auto=format&fit=crop&q=80&w=1000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aHVtYW58ZW58MHx8MHx8fDA%3D",
        role = UserRole.STUDENT,
        lastSeen = System.currentTimeMillis(),
        dateJoined = System.currentTimeMillis() - 20000000,
        bio = "Physics Enthusiast"
    ),
    User(
        id = "u3",
        username = "Kareem Aboelatta",
        email = "kareemaboelatta@example.com",
        profileImageUrl = "https://media.licdn.com/dms/image/D4D03AQEH1uGIwbkgxg/profile-displayphoto-shrink_800_800/0/1692981476295?e=2147483647&v=beta&t=njEiJPmnIs5jexw8S4o6Ltpt5zTqVTJCl8s4idq8ang",
        role = UserRole.STUDENT,
        lastSeen = System.currentTimeMillis(),
        dateJoined = System.currentTimeMillis() - 20000000,
        bio = "Android developer"
    ),
)

val currentTimestamp = System.currentTimeMillis()

val fakeMessages = listOf<Message>(
    TextMessage(
        id = "m0",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 900000,
        text = "Hello class! $EMOJI_PINK_HEART"
    ),TextMessage(
        id = "m1",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 900000,
        text = "Hello class! $EMOJI_PINK_HEART"
    ),
    TextMessage(
        id = "m2",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 850000,
        text = "$EMOJI_FLAMINGO Hello teacher!"
    ),
    PhotoMessage(
        id = "m3",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 800000,
        photoUrl = "https://images.pexels.com/photos/2853432/pexels-photo-2853432.jpeg?cs=srgb&dl=pexels-ethan-sees-2853432.jpg&fm=jpg",
        text = "Look at this graph"
    ),

    VideoMessage(
        id = "m4",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 750000,
        videoUrl = "https://example.com/video1.mp4",
        thumbnailUrl = "https://images.pexels.com/photos/2853432/pexels-photo-2853432.jpeg?cs=srgb&dl=pexels-ethan-sees-2853432.jpg&fm=jpg",
        title = "Introduction to Quantum Physics"
    ),
    PdfMessage(
        id = "m5",
        sender = fakeUsers[0],
        timestamp = currentTimestamp - 700000,
        pdfUrl = "https://example.com/document1.pdf",
        title = "Quantum Physics Notes"
    ),

    TextMessage(
        id = "m1",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 900000,
        text = "Hello class!"
    ),
    TextMessage(
        id = "m2",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 850000,
        text = "Hello teacher!"
    ),




    VideoMessage(
        id = "m4",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 750000,
        videoUrl = "https://example.com/video1.mp4",
        thumbnailUrl = "https://images.unsplash.com/photo-1453728013993-6d66e9c9123a?auto=format&fit=crop&q=80&w=1000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bGVuc3xlbnwwfHwwfHx8MA%3D%3D",
        title = "Introduction to Quantum Physics"
    ),


    QuizMessage(
        id = "m6",
        sender = fakeUsers[0],
        timestamp = currentTimestamp - 650000,
        title = "Quantum Physics Quiz",
        questions = listOf(
            Question(
                id = "q1",
                text = "Who is known as the father of Quantum Physics?",
                options = listOf("Newton", "Einstein", "Planck", "Bohr"),
                correctOptionIndex = 2
            ),
            Question(
                id = "q2",
                text = "Which principle states that you cannot know both the position and momentum of a particle with certainty?",
                options = listOf("Planck's Principle", "Heisenberg Uncertainty Principle", "Bohr's Principle", "Einstein's Relativity"),
                correctOptionIndex = 1
            )
        ),
        deadline = currentTimestamp + 86400000, // +1 day
        description = "Answer all questions"
    ),
    TextMessage(
        id = "m7",
        sender = fakeUsers.random(),
        timestamp = currentTimestamp - 600000,
        text = "I'm working on the quiz!"
    ),

)



object EMOJIS {
    // EMOJI 15
    const val EMOJI_PINK_HEART = "\uD83E\uDE77"

    // EMOJI 14 🫠
    const val EMOJI_MELTING = "\uD83E\uDEE0"

    // ANDROID 13.1 😶‍🌫️
    const val EMOJI_CLOUDS = "\uD83D\uDE36\u200D\uD83C\uDF2B️"

    // ANDROID 12.0 🦩
    const val EMOJI_FLAMINGO = "\uD83E\uDDA9"

    // ANDROID 12.0  👉
    const val EMOJI_POINTS = " \uD83D\uDC49"
}

