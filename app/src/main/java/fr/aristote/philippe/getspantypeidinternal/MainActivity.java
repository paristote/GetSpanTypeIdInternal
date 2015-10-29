package fr.aristote.philippe.getspantypeidinternal;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView linkTextView = (TextView)findViewById(R.id.linkTextView);

        String html = "<a href=\"https://developer.android.com\">" +
                        "Click me to crash !" +
                      "</a><br/>" +
                      "(Android 6.0 only)";

        linkTextView.setText(Html.fromHtml(html), TextView.BufferType.SPANNABLE);

        TextUrlSpan.linkify(linkTextView);

    }
}
