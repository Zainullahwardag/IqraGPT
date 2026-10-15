package com.iqragpt.app.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.iqragpt.app.BuildConfig
import com.iqragpt.app.data.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel that talks to Google's Gemini model.
 * It keeps the chat history in memory so IqraGPT remembers earlier messages.
 */
class ChatViewModel : ViewModel() {

    // List of messages shown on the screen.
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    // True while waiting for the AI reply.
    private val _isTyping = MutableStateFlow(false)
    val isTyping: StateFlow<Boolean> = _isTyping.asStateFlow()

    // Create the Gemini model. The API key comes from BuildConfig (read from local.properties).
    private val generativeModel = GenerativeModel(
        modelName = "gemini-3.5-flash-lite",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    // startChat() keeps conversation history so the bot remembers earlier messages.
    private val chat = generativeModel.startChat()

    init {
        // Welcome message shown when the app opens.
        _messages.value = listOf(
            ChatMessage(
                text = "Assalam-o-Alaikum! I'm IqraGPT, your AI assistant. How can I help you today?",
                isUser = false
            )
        )
    }

    /** Send a user message and get a reply from Gemini. */
    fun sendMessage(userText: String) {
        val trimmed = userText.trim()
        if (trimmed.isEmpty()) return

        // Show the user's message immediately.
        _messages.value = _messages.value + ChatMessage(trimmed, isUser = true)

        // Show typing indicator while waiting.
        _isTyping.value = true

        viewModelScope.launch {
            try {
                val response = chat.sendMessage(trimmed)
                val reply = response.text
                    ?: "Sorry, I couldn't get a response. Please try again."
                _messages.value = _messages.value + ChatMessage(reply, isUser = false)
            } catch (e: Exception) {
                // Friendly error message if the API call fails.
                _messages.value = _messages.value + ChatMessage(
                    "Sorry, I couldn't connect. Please try again.",
                    isUser = false
                )
            } finally {
                _isTyping.value = false
            }
        }
    }
}
