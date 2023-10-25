

package com.example.chat.data

import com.example.chat.ConversationUiState
import com.example.chat.R
import com.example.chat.data.EMOJIS.EMOJI_CLOUDS
import com.example.chat.data.EMOJIS.EMOJI_FLAMINGO
import com.example.chat.data.EMOJIS.EMOJI_MELTING
import com.example.chat.data.EMOJIS.EMOJI_PINK_HEART
import com.example.chat.data.EMOJIS.EMOJI_POINTS
import com.example.chat.fakeMessages


val exampleUiState = ConversationUiState(
    initialMessages = fakeMessages,
    channelName = "#composers",
    channelMembers = 42
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
