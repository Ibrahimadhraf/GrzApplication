package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders.productsinsideorder;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.MyOrders;
import com.codesroots.osamaomar.grz.models.entities.Products;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainactivity.MainActivity;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders.productsinsideorder.adapter.AllProductsInsideOrderAdapter;

import static com.codesroots.osamaomar.grz.models.entities.names.ORDER;
import static com.codesroots.osamaomar.grz.models.entities.names.ORDER_ID;

public class ProductsInsideorderFragment extends Fragment {

    private ProductsInsideOrderViewModel mViewModel;
    RecyclerView productsRecycle;
    int orderid;
    private FrameLayout progress;
    private TextView notfound,ordernum;
    private MyOrders.DataBean order;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.products_insideorderfragment, container, false);

        orderid = getArguments().getInt(ORDER_ID, 0);
        order = (MyOrders.DataBean) getArguments().getSerializable(ORDER);
        productsRecycle = view.findViewById(R.id.allProducts);
        progress = view.findViewById(R.id.progress);
        ordernum = view.findViewById(R.id.ordernum);
        if (orderid>0)
        ordernum.setText(String.valueOf(orderid));
        if (order!=null)
             productsRecycle.setAdapter(new AllProductsInsideOrderAdapter(getActivity(),order.getOrder_details()));

        return view;
    }

    private ProductsInsideOrderViewModelFactory getViewModelFactory() {
        return new ProductsInsideOrderViewModelFactory(this.getActivity().getApplication(),orderid);
    }


}
