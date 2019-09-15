package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
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
import com.codesroots.osamaomar.grz.databinding.MyOrdersFragmentBinding;
import com.codesroots.osamaomar.grz.models.entities.MyOrders;
import com.codesroots.osamaomar.grz.models.helper.EditOrder;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders.adapters.MyOrdersAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment implements EditOrder {

    private MyOrdersViewModel mViewModel;
    private int userid = PreferenceHelper.getUserId();
    private MyOrdersAdapter myOrdersAdapter;
    private MyOrdersFragmentBinding myOrdersFragmentBinding;
    private List<MyOrders.DataBean> allOrders = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        myOrdersFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.my_orders_fragment, container, false);
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MyOrdersViewModel.class);
        mViewModel.myOrdersMutableLiveData.observe(this, myOrders ->
        {
            allOrders = myOrders.getData();
            myOrdersFragmentBinding.progress.setVisibility(View.GONE);
            if (myOrders.getData().size() > 0) {
                {
                    myOrdersAdapter = new MyOrdersAdapter(getActivity(), allOrders, this);
                    myOrdersFragmentBinding.setAdapter(myOrdersAdapter);
                }
            } else
                myOrdersFragmentBinding.productNotfound.setVisibility(View.VISIBLE);
        });

        mViewModel.throwableMutableLiveData.observe(this, throwable ->
        {
            myOrdersFragmentBinding.progress.setVisibility(View.GONE);
            Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });

        return myOrdersFragmentBinding.getRoot();
    }


    private MyOrdersViewModelFactory getViewModelFactory() {
        return new MyOrdersViewModelFactory(getActivity().getApplication(), userid);
    }

    @Override
    public void onOrderEdit(int index) {
        myOrdersFragmentBinding.progress.setVisibility(View.VISIBLE);
        mViewModel.editOrder(allOrders.get(index).getOrder_id());
        mViewModel.edit.observe(this, aBoolean ->
                {
                    if (aBoolean) {
                        myOrdersFragmentBinding.progress.setVisibility(View.GONE);
                        allOrders.get(index).setOrder_status(3);
                        myOrdersFragmentBinding.getAdapter().notifyItemChanged(index);
                    }
                }
        );
    }
}
