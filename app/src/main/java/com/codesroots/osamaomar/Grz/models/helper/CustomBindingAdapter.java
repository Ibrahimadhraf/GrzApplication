package com.codesroots.osamaomar.grz.models.helper;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.grz.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomBindingAdapter {

    @BindingAdapter("android:image")
    public static void setImage(ImageView view, String url) {
        if (view != null && !TextUtils.isEmpty(url)) {
            Glide.with(view.getContext()).load(url).into(view);
        }
    }


    @BindingAdapter("android:color")
    public static void setTextColor(CircleImageView circleImageView, boolean s) {

        Context context = circleImageView.getContext();

        circleImageView.setBorderColor(s ? context.getResources().getColor(R.color.colorPrimary) :
                context.getResources().getColor(R.color.black));
    }

    @BindingAdapter("typeFace")
    public static void setTypeFace(View view, String type) {
        if (!TextUtils.isEmpty(type) && view instanceof TextView) {
            ((TextView) view).setTypeface(Typeface.createFromAsset(view.getContext().getAssets(), type));
        }
    }


    @BindingAdapter("android:setdate")
    public  void setDate(TextView textView, String datevalue) {
        textView.setText(getdate(datevalue));
    }


    private String getdate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj = sdf.parse(date);
            Log.d("newdatein", dateObj.getTime() + "");
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
