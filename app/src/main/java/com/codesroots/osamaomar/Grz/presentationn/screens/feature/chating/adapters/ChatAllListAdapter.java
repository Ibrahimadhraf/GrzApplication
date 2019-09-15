package com.codesroots.osamaomar.grz.presentationn.screens.feature.chating.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.ChatList;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.models.helper.ResourceUtil;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.chating.MessagesChatingActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ChatAllListAdapter extends RecyclerView.Adapter<ChatAllListAdapter.CustomView> {

    private Context context;
    PreferenceHelper preferenceHelper;

    public ChatAllListAdapter(FragmentActivity activity, ChatList chatList) {
        this.context = activity;
        preferenceHelper = new PreferenceHelper(context);
    }


    @Override
    public ChatAllListAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_contacts_chat, parent, false);

        return new ChatAllListAdapter.CustomView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        holder.mView.setOnClickListener(v -> context.startActivity(new Intent(context, MessagesChatingActivity.class)));
        holder.ivCall.setOnClickListener(v ->
                ResourceUtil.callNumber("122545",context));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage, unSeen, ivCall, ivLocation;
        private TextView message, time, tvNameC;
        LinearLayout cardwithimage;
        CardView cardwithmessage;

        private CustomView(View view) {
            super(view);
            mView = view;


            message = mView.findViewById(R.id.tvMessageC);
            time = mView.findViewById(R.id.tvDateC);
            tvNameC = mView.findViewById(R.id.tvNameC);
            itemImage = mView.findViewById(R.id.image);
            unSeen = mView.findViewById(R.id.ivUnSeen);
            ivCall = mView.findViewById(R.id.ivCallC);
        }
    }


    private String getdate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj = sdf.parse(date);
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
