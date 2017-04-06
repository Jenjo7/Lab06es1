package com.example.giannisavini.lab06es1;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by gianni.savini on 06/04/2017.
 */
public class InterfaceJS {

    private Activity activity;

    public InterfaceJS(Context context) {
        if(context instanceof Activity) {
            this.activity = (Activity) context;
        }
    }
    @JavascriptInterface
    public void example(final String param) {
        this.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, param, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
