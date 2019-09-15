package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productfragment.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.Product;
import com.codesroots.osamaomar.grz.models.helper.AddorRemoveCallbacks;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productdetailsfragment.ProductDetailsFragment;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productfragment.ProductsFragment;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.rate.RateActivity;

import java.util.List;

import static com.codesroots.osamaomar.grz.models.entities.names.PRODUCT_ID;
import static com.codesroots.osamaomar.grz.models.entities.names.PRODUCT_RATE;


public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.ViewHolder> {

    private Context context;
    private int type = 0;
    private List<Product> productsbysubcats;
    ProductsFragment productsFragment;
    int userid = PreferenceHelper.getUserId();
    Fragment fragment = new ProductDetailsFragment();
    Bundle bundle = new Bundle();

    public AllProductsAdapter(Context mcontext, int viewType, List<Product> productsbysubcats1,
                              ProductsFragment fragment) {
        context = mcontext;
        type = viewType;
        productsbysubcats = productsbysubcats1;
        productsFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (type == 0)
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_item_adapter, parent, false);
        else
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizental_product_item_adapter, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        if (productsbysubcats.get(position).getPhotos().size() > 0)
            Glide.with(context.getApplicationContext())
                    .load(context.getText(R.string.base_img_url) + productsbysubcats.get(position).getPhotos().get(0).getPhoto()).
                    placeholder(circularProgressDrawable).dontAnimate()
                    .into(holder.Image);

        holder.name.setText(productsbysubcats.get(position).getName());
        holder.ratingBar.setRating(productsbysubcats.get(position).getRate());
        holder.rateCount.setText("(" + productsbysubcats.get(position).getRatecount() + ")");

        holder.amount.setText(context.getText(R.string.remendier) + " " +
                String.valueOf(productsbysubcats.get(position).getAmount()) + " " + context.getText(R.string.num));
        holder.price.setText(productsbysubcats.get(position).getPrice());

        fragment.setArguments(bundle);
        holder.mView.setOnClickListener(v ->
        {
            bundle.putInt(PRODUCT_ID, productsbysubcats.get(position).getProductid());
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment, fragment)
                    .addToBackStack(null).commit();
        });

        holder.ratingBar.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Intent intent = new Intent(context, RateActivity.class);
                intent.putExtra(PRODUCT_ID, productsbysubcats.get(position).getProductid());
                intent.putExtra(PRODUCT_RATE, productsbysubcats.get(position).getRate());
                context.startActivity(intent);
            }
            return true;
        });


        holder.add_to_cart.setOnClickListener(v -> {
            productsFragment.onAddProduct(productsbysubcats.get(position).getProductid(),
                    productsbysubcats.get(position).getColorid(),
                    productsbysubcats.get(position).getSizeid(),
                    productsbysubcats.get(position).getColorname()
                    ,productsbysubcats.get(position).getSizename());
        });
    }

    @Override
    public int getItemCount() {
        return productsbysubcats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        public ImageView Image, favorite;
        public TextView name, rateCount, amount, price, add_to_cart;
        public RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            Image = mView.findViewById(R.id.item_img);
            name = mView.findViewById(R.id.item_name);
            price = mView.findViewById(R.id.item_price);
            amount = mView.findViewById(R.id.quentity);
            rateCount = mView.findViewById(R.id.rate_count);
            ratingBar = mView.findViewById(R.id.rates);
            favorite = mView.findViewById(R.id.favorite);
            add_to_cart = mView.findViewById(R.id.add_to_cart);
        }
    }
}