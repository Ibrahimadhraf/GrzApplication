package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.Product;
import com.codesroots.osamaomar.grz.models.entities.mainData;
import com.codesroots.osamaomar.grz.models.entities.MainView;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.adapters.DepartmentsAdapter;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.adapters.FamousProductsAdapter;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.adapters.SliderPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.codesroots.osamaomar.grz.models.usecases.Publicusecase.makeToas;
import static com.codesroots.osamaomar.grz.models.usecases.Publicusecase.setupviewPager;

public class MainFragment extends Fragment {

    RecyclerView departments,famous_products;
    ViewPager slider;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ProgressBar progress;
    ImageView frontblur;
    Group maingroup;
    int page = 1;
    List<Product> productList;
    FamousProductsAdapter famousProductsAdapter ;
    GridLayoutManager gridLayoutManager;
    ProductsDetailsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        findViewsFromXml(view);
        mViewModel.getData();
        mViewModel.mainViewMutableLiveData.observe(this,this::setDatainViews);
        famous_products.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((GridLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findLastCompletelyVisibleItemPosition();
                if (lastVisibleItem == famousProductsAdapter.getItemCount() - 1) {
                    page++;
                   mViewModel.getDataInPAginate(page);
                }
            }
        });

        return view;
    }

    private MainViewModelFactory getViewModelFactory() {
        return new MainViewModelFactory(this.getActivity().getApplication());

    }
    private void setDatainViews(mainData mainView) {
        progress.setVisibility(View.GONE);
        maingroup.setVisibility(View.VISIBLE);
        PreferenceHelper.setDoller_value(mainView.getDollervalue());
        if (mainView.getSlider()!=null) {
            setupviewPager(slider);
            init(mainView.getSlider());
            slider.setAdapter(new SliderPagerAdapter(getActivity(), mainView.getSlider()));
            indicator.setViewPager(slider);
        }

        if (page == 1) {
            productList = new ArrayList<>(mainView.getProducts());
            famousProductsAdapter = new FamousProductsAdapter(getActivity(),productList);
            famous_products.setAdapter(famousProductsAdapter);
        } else {
            productList.addAll(mainView.getProducts());
            famousProductsAdapter.notifyDataSetChanged();
            famous_products.scrollToPosition(famousProductsAdapter.getItemCount() - 19);
        }

        if (mainView.getCategories()!=null)
        departments.setAdapter(new DepartmentsAdapter(getActivity(),mainView.getCategories()));
    }
    private void init(List<MainView.SlidersBean> slidersBeans) {
        if (slidersBeans.size() >= 1) {
            Glide.with(Objects.requireNonNull(getActivity())).load("http://grzexpress.com/slider_images/"+slidersBeans.get(0).getPhoto()).into(frontblur);
        }
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(4 * density);
        NUM_PAGES = slidersBeans.size();
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == NUM_PAGES) {
                currentPage = 0;
            }
            slider.setCurrentItem(currentPage++, true);
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 5000);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                if (getActivity()!=null)
                Glide.with((getActivity())).load("http://grzexpress.com/slider_images/"+slidersBeans.get(position).getPhoto()).into(frontblur);
            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {}
            @Override
            public void onPageScrollStateChanged(int pos) {}
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProductsDetailsViewModel.class);

        mViewModel.errormessage.observe(this, message ->
        {
            if (page==1)
            { progress.setVisibility(View.GONE);
            makeToas(getContext(), String.valueOf(getText(R.string.erroroccure)));}
        });
    }

    private void findViewsFromXml(View view) {
        departments = view.findViewById(R.id.categoryRecycler);
        slider = view.findViewById(R.id.pager);
        maingroup = view.findViewById(R.id.maingroup);
        indicator = view.findViewById(R.id.indicator);
        famous_products = view.findViewById(R.id.rvproducts);
        progress = view.findViewById(R.id.progressBar);
        frontblur = view.findViewById(R.id.frontblur);
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        famous_products.setLayoutManager(gridLayoutManager);
    }
}
