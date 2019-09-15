package com.codesroots.osamaomar.grz.presentationn.screens.feature.userlocations.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.UserLocations;
import com.codesroots.osamaomar.grz.models.entities.names;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.getuserlocation.GetUserLocationActivity;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.userlocations.UserLocationsFragment;

import java.util.List;


public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    private Context context;
    private List<UserLocations.DataBean> places;
    private UserLocationsFragment userLocationsFragment;


    public LocationsAdapter(Context mcontext, List<UserLocations.DataBean> dataBeans, UserLocationsFragment fragment) {
        context = mcontext;
        places = dataBeans;
        userLocationsFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.location.setText(
                places.get(position).getState_country() + "," +
                        places.get(position).getTown_city() + "," + places.get(position).getAddress());
        holder.mView.setOnClickListener(v ->
                userLocationsFragment.onlocationchoicw(places.get(position)));

        holder.editloc.setOnClickListener(v ->
                {
                    Intent intent = new Intent(context, GetUserLocationActivity.class);
                    intent.putExtra(names.BILLING_ID, places.get(position).getBilling_id());
                    userLocationsFragment.startActivityForResult(intent,115);
                }
        );
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        private TextView location;
        private ImageView editloc;

        ViewHolder(View view) {
            super(view);
            mView = view;
            location = mView.findViewById(R.id.location);
            editloc = mView.findViewById(R.id.editloc);
        }
    }
}