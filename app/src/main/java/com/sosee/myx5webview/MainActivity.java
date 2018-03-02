package com.sosee.myx5webview;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.sosee.myx5webview.myview.ProgressWebView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String url = "http://www.youth.cn/";
    private ProgressWebView webView;
    private WebSettings webSettings;
    private WebChromeClient webChromeClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // 网页中的视频，上屏幕的时候，可能出现闪烁的情况，需要如下设置
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //避免输入法界面弹出后遮挡输入光标的问题
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        MyWeb(url);

    }

    //WebView
    private void MyWeb(String url) {
        webView = (ProgressWebView) findViewById(R.id.wb);
        webView.setDrawingCacheEnabled(true);
        webChromeClient = new WebChromeClient();
        webView.setWebChromeClient(webChromeClient);
        webSettings = webView.getSettings();

        // 修改ua使得web端正确判断(加标识+++++++++++++++++++++++++++++++++++++++++++++++++++++)
//        String ua = webSettings.getUserAgentString();
//        webSettings.setUserAgentString(ua + "这里是增加的标识");

        // 网页内容的宽度是否可大于WebView控件的宽度
        webSettings.setLoadWithOverviewMode(false);
        // 保存表单数据
        webSettings.setSaveFormData(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);

        webView.requestFocus(); //此句可使html表单可以接收键盘输入
        webView.setFocusable(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        // 启动应用缓存
        webSettings.setAppCacheEnabled(false);
        // 设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置此属性，可任意比例缩放。
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        //  页面加载好以后，再放开图片
        //mSettings.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        webSettings.setDomStorageEnabled(true);
        // 排版适应屏幕
        webSettings.setLayoutAlgorithm(com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // WebView是否支持多个窗口。
        webSettings.setSupportMultipleWindows(true);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setAllowFileAccess(true); // 允许访问文件
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //其他细节操作
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setDomStorageEnabled(true);//JS在HTML里面设置了本地存储localStorage，java中使用localStorage则必须打开
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true); //自适应屏幕

        //以下接口禁止(直接或反射)调用，避免视频画面无法显示：
        //webView.setLayerType();
        webView.setDrawingCacheEnabled(true);

        //去除QQ浏览器推广广告
        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                ArrayList<View> outView = new ArrayList<View>();
                getWindow().getDecorView().findViewsWithText(outView,"QQ浏览器",View.FIND_VIEWS_WITH_TEXT);
                if(outView.size()>0){
                    outView.get(0).setVisibility(View.GONE);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {



                webView.loadUrl(s);
                return true;
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);

            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
            }

        });

        webView.loadUrl(url);

    }

}
