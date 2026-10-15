package com.example.qimenapp;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);

        webView.getSettings().setJavaScriptEnabled(true);

        // 注册 JS 接口，供网页里的“退出应用”调用
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void exit() {
                runOnUiThread(() -> finish());
            }
        }, "Android");

        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        // 手机物理返回键：优先交给网页处理
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
