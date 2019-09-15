package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.MainView;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productfragment.ProductsFragment;

import java.util.List;

import static com.codesroots.osamaomar.grz.models.entities.names.CAT_ID;
import static com.codesroots.osamaomar.grz.models.entities.names.CAT_NAME;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.ViewHolder> {

    private Context context;
    private List<MainView.CategoryBean> categories;

    public DepartmentsAdapter(Context mcontext, List<MainView.CategoryBean> categories1) {
        context = mcontext;
        categories = categories1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.depart_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context).load("http://grzexpress.com/ar/./../category_images/"+categories.get(position).getPhoto()).dontAnimate().
                placeholder(R.drawable.dept1).into(holder.Image);
        holder.name.setText(categories.get(position).getName());

        holder.mView.setOnClickListener(v ->
        {
            Bundle bundle = new Bundle();
            bundle.putInt(CAT_ID, categories.get(position).getCategory_id());
            bundle.putString(CAT_NAME, categories.get(position).getName());
            Fragment product_fragment = new ProductsFragment();
            product_fragment.setArguments(bundle);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment, product_fragment).addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        private ImageView Image;
        private TextView name;

        ViewHolder(View view) {
            super(view);
            mView = view;
            Image = mView.findViewById(R.id.item_img);
            name = mView.findViewById(R.id.name);
        }
    }
}