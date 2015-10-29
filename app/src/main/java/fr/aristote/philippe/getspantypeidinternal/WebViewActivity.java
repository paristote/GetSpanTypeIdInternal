package fr.aristote.philippe.getspantypeidinternal;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setTitle("Web View Activity");

        WebView wv = (WebView)findViewById(R.id.webView);

        if (getIntent() != null) {
            String url = getIntent().getStringExtra("URL");
            wv.loadUrl(url);
        } else {
            wv.loadUrl("https://www.exoplatform.com");
        }

    }

}
