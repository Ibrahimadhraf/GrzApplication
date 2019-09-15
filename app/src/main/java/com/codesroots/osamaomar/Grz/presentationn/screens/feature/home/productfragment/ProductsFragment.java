package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productfragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.entities.ProductDB;
import com.codesroots.osamaomar.grz.models.entities.Product;
import com.codesroots.osamaomar.grz.models.helper.AddorRemoveCallbacks;
import com.codesroots.osamaomar.grz.models.helper.AddorRemoveToCartCallbacks;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.MainViewModelFactory;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productfragment.adapters.AllProductsAdapter;

import java.util.List;
import java.util.Objects;

import static com.codesroots.osamaomar.grz.models.entities.names.CAT_ID;
import static com.codesroots.osamaomar.grz.models.entities.names.CAT_NAME;
import static com.codesroots.osamaomar.grz.models.entities.names.SUBCATES_NAME;

public class ProductsFragment extends Fragment implements AddorRemoveToCartCallbacks {

    private ProductsViewModel mViewModel;
    RecyclerView productsRecycle;
    ConstraintLayout filter_option;
    ImageView changeSpane, filter;
    boolean RecycleIsHorizental = true;
    int CategryId, page = 1;
    private List<Product> productsData;
    private FrameLayout progress;
    private TextView notfound, cates_name, spillingfilter, pricefilter;
    private AllProductsAdapter AllProductsAdapter;
    private String title, name;
    private boolean getDataNow = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.products_fragment, container, false);
        initialize(view);
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProductsViewModel.class);
        //mViewModel.getData(CategryId);

        name = getArguments().getString("name");
        if (name != null)
            mViewModel.getSearchProductData("ar", name);
        else
            mViewModel.getData(CategryId, page);


        mViewModel.productsMutableLiveData.observe(this, products ->
        {
            mViewModel.setResultData(products);
            progress.setVisibility(View.GONE);
            notfound.setVisibility(View.GONE);
            if (products.size() > 0) {
                if (page == 1) {
                    productsData = products;
                    productsRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    AllProductsAdapter = new AllProductsAdapter(getActivity(), 0, productsData, this);
                    productsRecycle.setAdapter(AllProductsAdapter);
                } else {
                    getDataNow = false;

                    productsData.addAll(products);
                    AllProductsAdapter.notifyDataSetChanged();
                    productsRecycle.scrollToPosition(AllProductsAdapter.getItemCount() - 19);
                }
            } else {
                notfound.setVisibility(View.VISIBLE);
                changeSpane.setEnabled(false);
                filter.setEnabled(false);
            }
        });

        mViewModel.statues.observe(this, s ->
        {
            if (s.matches("0"))
                Toast.makeText(getContext(), getText(R.string.aleady_exists), Toast.LENGTH_SHORT).show();
            else if (s.matches("1"))
                Toast.makeText(getContext(), getText(R.string.addtocartsuccess), Toast.LENGTH_SHORT).show();
            else if (s.matches("2"))
                Toast.makeText(getContext(), getText(R.string.erroroccure), Toast.LENGTH_SHORT).show();
        });


        mViewModel.errorMessage.observe(this, throwable ->
        {
            if (page==1) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getActivity(), throwable, Toast.LENGTH_SHORT).show();
            }
        });
        pricefilter.setOnClickListener(v ->
        {
            mViewModel.comparewithprice();
            filter_option.setVisibility(View.GONE);
            hideView(filter_option, 0);
        });
        spillingfilter.setOnClickListener(v ->
        {
            mViewModel.comparewithspilling();
            filter_option.setVisibility(View.GONE);
            hideView(filter_option, 0);
        });

        productsRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findLastVisibleItemPosition();
                Log.d("lastVisible", String.valueOf(lastVisibleItem));
                if (lastVisibleItem == (AllProductsAdapter.getItemCount() - 1)) {
                    if (!getDataNow) {
                        page++;
                        mViewModel.getData(CategryId, page);
                        getDataNow = true;
                    }
                }
            }
        });
        return view;
    }

    private void initialize(View view) {
        productsRecycle = view.findViewById(R.id.allProducts);
        changeSpane = view.findViewById(R.id.change_span);
        filter = view.findViewById(R.id.filter);
        filter_option = view.findViewById(R.id.filter_option);
        progress = view.findViewById(R.id.progress);
        spillingfilter = view.findViewById(R.id.spilingfilter);
        pricefilter = view.findViewById(R.id.pricefilter);
        notfound = view.findViewById(R.id.product_notfound);
        cates_name = view.findViewById(R.id.cates_name);
        title = getArguments().getString(CAT_NAME);
        cates_name.setText(title);
        CategryId = getArguments().getInt(CAT_ID, 0);
        changeSpane.setOnClickListener(onClickListener);
        filter.setOnClickListener(onFilterClickListener);
    }


    private MainViewModelFactory getViewModelFactory() {
        return new MainViewModelFactory(this.getActivity().getApplication());
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!RecycleIsHorizental) {
                productsRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                AllProductsAdapter = new AllProductsAdapter(getActivity(), 0, productsData, ProductsFragment.this);
                productsRecycle.setAdapter(AllProductsAdapter);
                RecycleIsHorizental = true;
            } else {
                productsRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                AllProductsAdapter = new AllProductsAdapter(getActivity(), 1, productsData, ProductsFragment.this);
                productsRecycle.setAdapter(AllProductsAdapter);
                RecycleIsHorizental = false;
            }
        }
    };

    private void hideView(final View view, int states) {
        Animation animation = null;
        if (states == 1)
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animy);
        else
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animyforimg);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        view.startAnimation(animation);
    }

    private View.OnClickListener onFilterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (filter_option.getVisibility() == View.GONE) {
                filter_option.setVisibility(View.VISIBLE);
                hideView(filter_option, 1);
            } else {
                filter_option.setVisibility(View.GONE);
                hideView(filter_option, 0);
            }
        }
    };

    @Override
    public void onAddProduct(int pid, int cid, int sid,String colorname,String sizename) {
        ProductDB product = new ProductDB(pid, cid, sid,colorname,sizename);
        ((AddorRemoveCallbacks) getActivity()).onAddProduct();
        mViewModel.AddToCart(product);
    }

    @Override
    public void onRemoveProduct(int pid, int pos) {
    }

    @Override
    public void onChangeCart() {

    }
}
