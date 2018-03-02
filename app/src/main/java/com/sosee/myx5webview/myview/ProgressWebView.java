package com.sosee.myx5webview.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebStorage;
import com.tencent.smtt.sdk.WebView;

/**
 *ProgressWebView
 *@author gubin
 *@author date: 2017/9/27
 *@author csdn:http://blog.csdn.net/eueheuen
 *
 */
public class ProgressWebView extends WebView {

    ProgressView mProgressview;
    int progressColor = 0xFFFF4081;


    public ProgressWebView(Context context) {
        this(context, null);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    private void initViews() {
        mProgressview = new ProgressView(getContext());
        mProgressview.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 6));
        mProgressview.setDefaultColor(progressColor);
        addView(mProgressview);
        this.setWebChromeClient(new ProgressWebChromeClient(null));
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
      //  LayoutParams lp = (LayoutParams) mProgressview.getLayoutParams();
        super.onScrollChanged(l, t, oldl, oldt);
    }


//        @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        LayoutParams lp = (LayoutParams) mProgressview.getLayoutParams();
//        lp.x = l;
//        lp.y = t;
//        mProgressview.setLayoutParams(lp);
//        super.onScrollChanged(l, t, oldl, oldt);
//    }

    @Override
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        super.setWebChromeClient(new ProgressWebChromeClient(webChromeClient));
    }

    public class ProgressWebChromeClient extends WebChromeClient {
        WebChromeClient readClient;
        public ProgressWebChromeClient(WebChromeClient readClient) {
            this.readClient = readClient==null?new WebChromeClient():readClient;
        }

        @Override
        public Bitmap getDefaultVideoPoster() {
            return readClient.getDefaultVideoPoster();
        }

        @Override
        public View getVideoLoadingProgressView() {
            return readClient.getVideoLoadingProgressView();
        }

        @Override
        public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
            readClient.getVisitedHistory(valueCallback);
        }

        @Override
        public void onCloseWindow(WebView webView) {
            readClient.onCloseWindow(webView);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return readClient.onConsoleMessage(consoleMessage);
        }

        @Override
        public boolean onCreateWindow(WebView webView, boolean b, boolean b1, Message message) {
            return readClient.onCreateWindow(webView, b, b1, message);
        }

        @Override
        public void onExceededDatabaseQuota(String s, String s1, long l, long l1, long l2, WebStorage.QuotaUpdater quotaUpdater) {
            readClient.onExceededDatabaseQuota(s, s1, l, l1, l2, quotaUpdater);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            readClient.onGeolocationPermissionsHidePrompt();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String s, GeolocationPermissionsCallback geolocationPermissionsCallback) {
            super.onGeolocationPermissionsShowPrompt(s, geolocationPermissionsCallback);
            readClient.onGeolocationPermissionsShowPrompt(s,geolocationPermissionsCallback);
        }

        //        @Override
//        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
//            readClient.onGeolocationPermissionsShowPrompt(origin, (GeolocationPermissionsCallback) callback);
//        }

        @Override
        public void onHideCustomView() {
            readClient.onHideCustomView();
        }

        @Override
        public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
            return readClient.onJsAlert(webView, s, s1, jsResult);
        }

        @Override
        public boolean onJsBeforeUnload(WebView webView, String s, String s1, JsResult jsResult) {
            return readClient.onJsBeforeUnload(webView, s, s1, jsResult);
        }

        @Override
        public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
            return readClient.onJsConfirm(webView, s, s1, jsResult);
        }

        @Override
        public boolean onJsPrompt(WebView webView, String s, String s1, String s2, JsPromptResult jsPromptResult) {
            return readClient.onJsPrompt(webView, s, s1, s2, jsPromptResult);
        }

        @Override
        public boolean onJsTimeout() {
            return readClient.onJsTimeout();
        }

        @Override
        public void onReachedMaxAppCacheSize(long l, long l1, WebStorage.QuotaUpdater quotaUpdater) {
            readClient.onReachedMaxAppCacheSize(l, l1, quotaUpdater);
        }

        @Override
        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            readClient.onReceivedIcon(webView, bitmap);
        }

        @Override
        public void onReceivedTitle(WebView webView, String s) {
            readClient.onReceivedTitle(webView, s);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView webView, String s, boolean b) {
            readClient.onReceivedTouchIconUrl(webView, s, b);
        }

        @Override
        public void onRequestFocus(WebView webView) {
            readClient.onRequestFocus(webView);
        }


        @Override
        public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback callback) {
            readClient.onShowCustomView(view, callback);
        }


        @Override
        public void onShowCustomView(View view, int requestedOrientation, IX5WebChromeClient.CustomViewCallback callback) {
            readClient.onShowCustomView(view, requestedOrientation, callback);
        }

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
            return readClient.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }

//
//        @Override
//        public void openFileChooser(ValueCallback<Uri> valueCallback, String s, String s1) {
//            readClient.openFileChooser(valueCallback, s, s1);
//        }
//

        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            readClient.onProgressChanged(webView, newProgress);
            Log.i("progress", "onProgressChanged: " + newProgress);
            mProgressview.setProgress(newProgress);
        }
    }

}

