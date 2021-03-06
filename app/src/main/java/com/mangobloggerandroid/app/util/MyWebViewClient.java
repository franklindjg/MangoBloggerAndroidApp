package com.mangobloggerandroid.app.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by ujjawal on 9/10/17.
 */

public class MyWebViewClient extends WebViewClient {

    private Context context;

    public MyWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().equals("www.mangoblogger.com")) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
        }
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
        return true;
    }


    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        view.loadUrl("javascript:document.querySelector('header').setAttribute('style','display:none;');");
        view.loadUrl("javascript:document.querySelector('footer').setAttribute('style','display:none;');");
        view.loadUrl("javascript:(function(){ document.body.style.paddingTop = '50px'})();");

    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Toast.makeText(context, "Failed to Load,Please Try again!", Toast.LENGTH_SHORT).show();
    }

}