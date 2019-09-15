package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.MyOrders;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders.MyOrdersFragment;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders.productsinsideorder.ProductsInsideorderFragment;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.codesroots.osamaomar.grz.models.entities.names.ORDER;
import static com.codesroots.osamaomar.grz.models.entities.names.ORDER_ID;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    private Context context;
    private List<MyOrders.DataBean> orderdata;
    private MyOrdersFragment myOrdersFragment;

    public MyOrdersAdapter(Context mcontext, List<MyOrders.DataBean> data, MyOrdersFragment myOrdersFragment1) {
        context = mcontext;
        orderdata = data;
        myOrdersFragment = myOrdersFragment1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myorder_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        try {
            if (PreferenceHelper.getCurrencyValue() > 0)
                holder.orderPrice.setText(new DecimalFormat("##.##").
                        format(orderdata.get(position).getOrder_gtotal()
                                * PreferenceHelper.getCurrencyValue()) + " " + PreferenceHelper.getCurrency());
            else
                holder.orderPrice.setText(orderdata.get(position).getOrder_gtotal() + context.getString(R.string.realcoin));

            holder.orderdate.setText(getdate(orderdata.get(position).getCreated_at()));
            holder.ordernum.setText(String.valueOf(orderdata.get(position).getOrder_id()));
            // holder.payment.setText(orderdata.get(position).getType());
            holder.productCount.setText(String.valueOf(orderdata.get(position).getOrder_details().size()) + " " + context.getText(R.string.num));
            holder.productname.setText(orderdata.get(position).getOrder_details().
                    get(0).getProduct().getName());

            holder.deliverd.setOnClickListener(v -> myOrdersFragment.onOrderEdit(position));

            if (orderdata.get(position).getOrder_details().get(0).getProduct().getTotal_rating().size() > 0) {
                holder.ratingBar.setRating(orderdata.get(position).getOrder_details().
                        get(0).getProduct().getTotal_rating().get(0).getStars() / orderdata.get(position).getOrder_details().
                        get(0).getProduct().getTotal_rating().get(0).getCount());
                holder.ratecount.setText("(" + orderdata.get(position).getOrder_details().
                        get(0).getProduct().getTotal_rating().get(0).getCount() + ")");
            }

            if (orderdata.get(position).getOrder_status() == 1)
                holder.progress1txt.setText(R.string.pending);

            else if (orderdata.get(position).getOrder_status() == 2) {
                holder.progress1txt.setText(R.string.cancelled);
                holder.statues1.setText("2");
            }
            else if (orderdata.get(position).getOrder_status() == 3)
            {   holder.progress1txt.setText(R.string.processing);
                holder.statues1.setText("3");
            }
            else if (orderdata.get(position).getOrder_status() == 4)
            {  holder.progress1txt.setText(R.string.readyforship);
                holder.statues1.setText("4");
            }
            else if (orderdata.get(position).getOrder_status() == 5)
            { holder.progress1txt.setText(R.string.awaiting);
                holder.statues1.setText("5");
            }

            if (orderdata.get(position).getOrder_status() == 6) {
                holder.statues2.setTextColor(Color.WHITE);
                holder.statues2.setBackgroundResource(R.drawable.selected_progress);
            } else if (orderdata.get(position).getOrder_status() == 7) {
                holder.statues2.setTextColor(Color.WHITE);
                holder.statues3.setTextColor(Color.WHITE);
                holder.statues2.setBackgroundResource(R.drawable.selected_progress);
                holder.statues3.setBackgroundResource(R.drawable.selected_progress);
            }

            Glide.with(context).load(context.getText(R.string.base_img_url) + orderdata.get(position).getOrder_details().
                    get(0).getProduct().getProductphotos().get(0).getPhoto()).
                    dontAnimate().placeholder(R.drawable.noimg).into(holder.Image);

        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }


        holder.gotodetails.setOnClickListener(v -> {
            Fragment fragment = new ProductsInsideorderFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ORDER_ID, orderdata.get(position).getOrder_id());
            bundle.putSerializable(ORDER, orderdata.get(position));
            fragment.setArguments(bundle);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return orderdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        private ImageView Image;
        private TextView statues1, statues2, statues3, orderPrice,
                productCount, orderdate, ordernum, productname, ratecount, gotodetails, payment, deliverd,progress1txt;
        private RatingBar ratingBar;

        ViewHolder(View view) {
            super(view);
            mView = view;
            Image = mView.findViewById(R.id.item_img);
            statues1 = mView.findViewById(R.id.progress1);
            statues2 = mView.findViewById(R.id.progress2);
            statues3 = mView.findViewById(R.id.progress3);
            productCount = mView.findViewById(R.id.product_count);
            orderPrice = mView.findViewById(R.id.totalprice);
            orderdate = mView.findViewById(R.id.order_date);
            ordernum = mView.findViewById(R.id.ordernum);
            payment = mView.findViewById(R.id.payment);
            productname = mView.findViewById(R.id.item_name);
            ratecount = mView.findViewById(R.id.rate_count);
            ratingBar = mView.findViewById(R.id.rates);
            gotodetails = mView.findViewById(R.id.gotodetails);
            deliverd = mView.findViewById(R.id.deliverd);
            progress1txt = mView.findViewById(R.id.progress1txt);
        }
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