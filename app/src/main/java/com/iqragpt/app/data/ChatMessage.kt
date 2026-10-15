package com.iqragpt.app.data

/**
 * A single chat message.
 *
 * @param text    The message text.
 * @param isUser  true if the user sent it, false if IqraGPT sent it.
 */
data class ChatMessage(
    val text: String,
    val isUser: Boolean
)
