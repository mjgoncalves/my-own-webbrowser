package com.example.marcelo.mybrowser;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    WebView browser;
    EditText edtUrl;
    Button btnGo, btnForward, btnBack, btnReload, btnClear;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        edtUrl = findViewById(R.id.edt_url);
        btnGo = findViewById(R.id.btn_go);
        btnGo.setOnClickListener(this);

        btnForward = findViewById(R.id.btn_fwd);
        btnForward.setOnClickListener(this);

        btnBack = findViewById(R.id.btn_bck);
        btnBack.setOnClickListener(this);

        btnReload = findViewById(R.id.btn_reload);
        btnReload.setOnClickListener(this);

        btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(this);

        browser = findViewById(R.id.wv_browser);
        browser.loadUrl("https://www.brainpickings.org");
        browser.setWebViewClient(new mWebViewClient());
        browser.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView webView, int newProgress){
                progressBar.setProgress(newProgress);

                if (newProgress == 100){progressBar.setVisibility(View.GONE);}
                else {progressBar.setVisibility(View.VISIBLE);}
            }
        });

        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go:
                String edtValue = edtUrl.getText().toString();
                if (!edtValue.startsWith("http://")){

                    edtValue = "http://" + edtValue;
                }
                browser.loadUrl(edtValue);
                InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(edtUrl.getWindowToken(), 0);

            break;

            case R.id.btn_fwd:
                if (browser.canGoForward()){
                    browser.goForward();
                }
            break;

            case R.id.btn_bck:
                if (browser.canGoBack()){
                    browser.goBack();
                }

                break;

            case R.id.btn_reload:
                browser.reload();
            break;

            case R.id.btn_clear:
                browser.clearHistory();
            break;
        }

    }
}
