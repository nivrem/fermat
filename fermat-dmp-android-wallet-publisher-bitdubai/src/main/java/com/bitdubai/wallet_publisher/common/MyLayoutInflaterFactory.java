package com.bitdubai.wallet_publisher.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.bitdubai.wallet_publisher.R;
import com.bitdubai.wallet_publisher.common.classes.MyApplication;

/**
 * Created by toshiba on 27/04/2015.
 */
public class MyLayoutInflaterFactory implements LayoutInflater.Factory {

    @Override
    public View onCreateView(String name, Context context,
                             AttributeSet attrs) {

        if (name.equalsIgnoreCase(
                "com.android.internal.view.menu.IconMenuItemView")) {
            try {
                LayoutInflater li = LayoutInflater.from(context);
                final View view = li.createView(name, null, attrs);
                new Handler().post(new Runnable() {
                    public void run() {
                        // set the background drawable if you want that
                        //or keep it default -- either an image, border
                        //gradient, drawable, etc.
                        view.setBackgroundResource(R.drawable.banner_kid_yellow_blue);
                        ((TextView) view).setTextSize(20);

                        // set the text color
                        Typeface face = MyApplication.getDefaultTypeface();
                        ((TextView) view).setTypeface(face);
                        ((TextView) view).setTextColor(Color.WHITE);
                    }
                });
                return view;
            } catch (InflateException e) {
                //Handle any inflation exception here
            } catch (ClassNotFoundException e) {
                //Handle any ClassNotFoundException here
            }
        }
        return null;
    }

}
