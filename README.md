# IqraGPT

A simple Android chatbot app (Kotlin + Jetpack Compose) powered by Google's Gemini AI.

## Setup

1. Open the project in Android Studio.
2. Put your Gemini API key in `local.properties`:
   ```
   GEMINI_API_KEY=your_key_here
   ```
3. Sync Gradle and run on an emulator/device with internet access.

## Notes

- Single screen, light mode only.
- Conversation history is kept in memory for the session (no database).
