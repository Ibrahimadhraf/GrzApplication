package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.Product;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productdetailsfragment.ProductDetailsFragment;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.rate.RateActivity;
import java.util.List;
import static com.codesroots.osamaomar.grz.models.entities.names.PRODUCT_ID;
import static com.codesroots.osamaomar.grz.models.entities.names.PRODUCT_RATE;

public class FamousProductsAdapter extends RecyclerView.Adapter<FamousProductsAdapter.ViewHolder> {

    private Context context;
    List<Product> famousProduct;
    public FamousProductsAdapter(Context context, List<Product> productsbyrate) {
        this.context = context;
        this.famousProduct = productsbyrate;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_more_rate_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(famousProduct.get(position).getName());
        CircularProgressDrawable circularProgressDrawable =new  CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(context.getApplicationContext()).
                load(context.getString(R.string.base_img_url)+famousProduct.get(position).getPhoto()).
                placeholder(circularProgressDrawable).into(holder.item_img);
        holder.ratingBar.setRating(famousProduct.get(position).getRate());

        holder.price.setText(famousProduct.get(position).getPrice());
        holder.ratecount.setText("("+String.valueOf(famousProduct.get(position).getRatecount())+")");

        holder.mView.setOnClickListener(v ->
        {
            Fragment fragment = new ProductDetailsFragment();
            Bundle bundle = new Bundle();
                bundle.putInt(PRODUCT_ID, famousProduct.get(position).getProductid());
            fragment.setArguments(bundle);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment, fragment)
                    .addToBackStack(null).commit();
        });

        holder.ratingBar.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Intent intent = new Intent(context, RateActivity.class);
                intent.putExtra(PRODUCT_ID, famousProduct.get(position).getProductid());
                intent.putExtra(PRODUCT_RATE, famousProduct.get(position).getRate());
                context.startActivity(intent);
            }
            return true;
        });

    }

    @Override
    public int getItemCount() {
        if (famousProduct != null)
            return famousProduct.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView item_img;
        TextView price, name,ratecount;
        RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            item_img = view.findViewById(R.id.item_img);
            name = view.findViewById(R.id.item_name);
            price = view.findViewById(R.id.price);
            ratingBar = view.findViewById(R.id.rates);
            ratecount = view.findViewById(R.id.rate_count);
        }
    }
}
