package com.example.lmotisi.myapp;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsInterface {

    @JavascriptInterface
    public void test() {
        Log.d("JS", "toto");
    }
}
