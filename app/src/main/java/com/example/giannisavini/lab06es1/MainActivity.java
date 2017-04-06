package com.example.giannisavini.lab06es1;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.wbv_example);
        btnStart = (Button) findViewById(R.id.btn_start);

        initWebView();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://www.corsomobile.it");
            }
        });
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Toast.makeText(MainActivity.this, "Inizio Caricamento", Toast.LENGTH_SHORT).show();
                super.onPageStarted(view, url, favicon);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                Toast.makeText(MainActivity.this, "Fine Caricamento", Toast.LENGTH_SHORT).show();
                super.onPageFinished(view, url);
            }

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Toast.makeText(MainActivity.this, "Url: " + url, Toast.LENGTH_SHORT).show();

                if (Uri.parse(url).getHost().equals("www.google.it")) {
                    return super.shouldOverrideUrlLoading(view, url);
                } else {
                    Intent openUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(openUrl);
                    return true;
                }

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrideUrlLoading(view, request.toString());
            }


        });

        webView.addJavascriptInterface(new InterfaceJS(MainActivity.this), "app");
    }
}
