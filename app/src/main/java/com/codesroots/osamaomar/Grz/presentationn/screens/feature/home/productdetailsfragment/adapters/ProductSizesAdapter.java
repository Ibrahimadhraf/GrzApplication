package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productdetailsfragment.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.ProductDetails;
import com.codesroots.osamaomar.grz.models.entities.StoreSetting;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productdetailsfragment.ProductDetailsFragment;
import java.util.List;


public class ProductSizesAdapter extends RecyclerView.Adapter<ProductSizesAdapter.ViewHolder> {

    private Context context;
    ProductDetailsFragment fragment;
     public List<ProductDetails.product.ProductsizesBean> productsizes;
    public int mSelectedItem = 0;
    public String mSelectedItemname = " ";

    public ProductSizesAdapter(Context mcontext, List<ProductDetails.product.ProductsizesBean> sizes,
                               ProductDetailsFragment detailsFragment) {
        context = mcontext;
        productsizes = sizes;
        fragment = detailsFragment;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.size_item_adapter, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.text.setText(productsizes.get(position).getSize().getSize_title());
    //    fragment.amount.setText(context.getText(R.string.remendier) + " " + String.valueOf(productsizes.get(mSelectedItem).getAmount()) + " " + context.getText(R.string.num));

        if (position == mSelectedItem) {
            holder.text.setBackgroundResource(R.drawable.linear_background_for_selected_size);
        }
        else
            holder.text.setBackgroundResource(R.drawable.linear_background_for_size);
    }

    @Override
    public int getItemCount() {
        if (productsizes!=null)
        return productsizes.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        private TextView text;

        ViewHolder(View view) {
            super(view);
            mView = view;
            text = mView.findViewById(R.id.size);

            @SuppressLint("SetTextI18n") View.OnClickListener clickListener = v -> {
               mSelectedItem = getAdapterPosition();
                notifyDataSetChanged();
            };
            itemView.setOnClickListener(clickListener);
        }
    }
}