package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private Button button;
    private String abc = "";
////    private String url = "https://mlbrains.com/call/test/";
////    private String url = "http://mlbrains.com/call/";
//    private String url = "http://mlbrains.com/call/?action=start_call&uid=xAta6uMm9kdwxcC2rG7K5aTb4Jf1";
////    private String url = "https://webcamtests.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 1);

        webView = findViewById(R.id.webView);
        button = findViewById(R.id.button);
        webView.addJavascriptInterface(new WebAppInterface(this), "JSBridge");

        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(PermissionRequest request) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    if (request.getResources().contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE)) {
                        // Handle camera permission request within the WebView
                request.grant(request.getResources());
//                    }
//                }
            }
        });

        webSettings.setJavaScriptEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        webView.loadUrl(getIntent().getStringExtra("url"));

        button.setOnClickListener(v -> webView.evaluateJavascript("androidData(\"Bu11tton Click\")", null));
//        button.setOnClickListener(v -> webView.evaluateJavascript("(function() { window.dispatchEvent(new CustomEvent('dataToWeb', {detail: { data:\"Button Click\"},}),); })();", null));
//        button.setOnClickListener(v -> webView.evaluateJavascript("abcd", null));

//        webView.evaluateJavascript();
    }

    public class WebAppInterface {
        private Context context;

        WebAppInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            abc = toast;
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
        }
    }
}