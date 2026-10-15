package com.iqragpt.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iqragpt.app.ui.theme.IqraGPTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IqraGPTTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val viewModel: ChatViewModel = viewModel()
                    ChatScreen(viewModel = viewModel)
                }
            }
        }
    }
}
