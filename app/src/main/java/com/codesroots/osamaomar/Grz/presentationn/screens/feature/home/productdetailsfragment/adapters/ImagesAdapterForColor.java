package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productdetailsfragment.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.ProductDetails;

import java.util.List;


public class ImagesAdapterForColor extends RecyclerView.Adapter<ImagesAdapterForColor.ViewHolder> {

    private Context context;
    public int mSelectedItem = 0;
    public List<ProductDetails.product.ColorBean> colors;

    public ImagesAdapterForColor(Context context, List<ProductDetails.product.ColorBean> productcolors) {
        this.context = context;
        colors = productcolors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_color_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.color.setText(colors.get(position).getColor().getName());

        try {
            if (position == mSelectedItem) {
                holder.linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.linear_background_choice_color));
            }
            else
                holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        } catch (Exception e) {
        }
    }


    @Override
    public int getItemCount() {
        return colors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public TextView color;
        LinearLayout linearLayout;

        public ViewHolder(View view) {

            super(view);
            mView = view;
            color = itemView.findViewById(R.id.textcolor);
            linearLayout = itemView.findViewById(R.id.colorborder);
            View.OnClickListener clickListener = v -> {
                mSelectedItem = getAdapterPosition();
                notifyDataSetChanged();
            };

            itemView.setOnClickListener(clickListener);
            linearLayout.setOnClickListener(clickListener);
        }
    }

}

