package com.example.marcelo.mybrowser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class mWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
