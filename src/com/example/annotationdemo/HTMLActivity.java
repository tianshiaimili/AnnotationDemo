package com.example.annotationdemo;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import com.example.annotationdemo.bean.MyObject;

@SuppressLint("JavascriptInterface")
public class HTMLActivity extends Activity {
	private WebView webView = null;
	public Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        
        webView = (WebView)this.findViewById(R.id.webView);
        //设置字符集编码
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        //开启JavaScript支持
        webView.getSettings().setJavaScriptEnabled(true);
        //传递一个Java对象，同时给他命名，这个对象可以在js中调用这个对象的方法
        webView.addJavascriptInterface(new MyObject(this,handler), "myObject");
        //加载assets目录下的文件
        String url = "file:///android_asset/index.html";
        webView.loadUrl(url);
    }
}
