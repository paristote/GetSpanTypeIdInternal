package fr.aristote.philippe.getspantypeidinternal;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


/**
 * Created by paristote on 10/29/15.
 */
public class TextUrlSpan extends ClickableSpan implements ParcelableSpan {
    private String mURL;

    public TextUrlSpan(String url) {
        mURL = url;
    }

    public TextUrlSpan(Parcel src) {
        mURL = src.readString();

    }

    public int describeContents() {

        return 0;

    }

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mURL);

    }

    public String getURL() {

        return mURL;

    }

    @Override
    public void onClick(View widget) {
        Context context = widget.getContext();
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("URL", getURL());
        context.startActivity(intent);
    }

    @Override
    public int getSpanTypeId() {
        return 11;
    }

    /**
     * This method makes URLs clickable and open in WebViewActivity
     */
    public static void linkify(TextView textView) {
        URLSpan[] list = textView.getUrls();
        if (list != null) {
            try {
                Spannable spannable = (Spannable) textView.getText();
                for (URLSpan span : list) {
                    int start = spannable.getSpanStart(span);
                    int stop = spannable.getSpanEnd(span);
                    int flags = spannable.getSpanEnd(span);
                    String spanUrl = span.getURL();
                    spannable.removeSpan(span);
                    TextUrlSpan myUrlSpan = new TextUrlSpan(spanUrl);
                    spannable.setSpan(myUrlSpan, start, stop, flags);
                    textView.setText(spannable);
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                }
            } catch (ClassCastException e) {
                // textView.getText() can fail to be casted to Spannable
                Log.w(TextUrlSpan.class.getSimpleName(), e.getMessage(), e);
            }
        }
    }

//
//  Will crash on Android 6.0 if commented out

//  public int getSpanTypeIdInternal() {
//    return getSpanTypeId();
//  }
//
//  public void writeToParcelInternal(Parcel dest, int flags) {
//    writeToParcel(dest, flags);
//  }
}
