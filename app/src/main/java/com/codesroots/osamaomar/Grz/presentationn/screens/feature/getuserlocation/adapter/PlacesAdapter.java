package com.codesroots.osamaomar.grz.presentationn.screens.feature.getuserlocation.adapter;

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
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.models.helper.ResourceUtil;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.chating.MessagesChatingActivity;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.CustomView> {

    private Context context;
    //  private List<ChhatList.MyChat> allchats;
    PreferenceHelper preferenceHelper;

    public PlacesAdapter(FragmentActivity activity) {
        this.context = activity;
        preferenceHelper = new PreferenceHelper(context);
    }


    @Override
    public PlacesAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);

        return new PlacesAdapter.CustomView(view);

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



}
