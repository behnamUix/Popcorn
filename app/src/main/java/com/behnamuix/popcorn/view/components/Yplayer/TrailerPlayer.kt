package com.behnamuix.popcorn.view.components.Yplayer

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun TrailerPlayer(url: String) {
    //baraye namayesh trailer bar asase key

    AndroidView(
        factory = { context ->
            //baraye namayesh player
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.mediaPlaybackRequiresUserGesture = false
                webChromeClient = WebChromeClient()
                loadUrl(url)

            }
        }, update = { webView ->
            webView.loadUrl(url)
        }

    )


}