package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.offerfragment;

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
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.MainViewModelFactory;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.ProductsDetailsViewModel;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.offerfragment.adapter.AllOffersAdapter;


public class OffersFragment extends Fragment {

    private ProductsDetailsViewModel mViewModel;
    RecyclerView alloffers;
    private FrameLayout progress;
    private TextView notfound;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.offers_fragment, container, false);
        alloffers = view.findViewById(R.id.alloffers);
        progress = view.findViewById(R.id.progress);
        notfound = view.findViewById(R.id.offers_notfound);

        ProductsDetailsViewModel mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProductsDetailsViewModel.class);
        mViewModel.getOffersData();

        mViewModel.offerproductsMutableLiveData.observe(this, products ->
        {
            progress.setVisibility(View.GONE);
            if (products.size() > 0)
                alloffers.setAdapter(new AllOffersAdapter(getActivity(), products));
            else
                notfound.setVisibility(View.VISIBLE);
        });

        mViewModel.errormessage.observe(this, throwable ->
        {
            progress.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getText(R.string.erroroccure), Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private MainViewModelFactory getViewModelFactory() {
        return new MainViewModelFactory(this.getActivity().getApplication());
    }
}
