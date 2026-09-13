package com.traintracker.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

suspend fun doHttpRequest(
    url: String,
    ): String?
{
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                response.body?.string()
            } else {
                null
            }
        }
    }
}
