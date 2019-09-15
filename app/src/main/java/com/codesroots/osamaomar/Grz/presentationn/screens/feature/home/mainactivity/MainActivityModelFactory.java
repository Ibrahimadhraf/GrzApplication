package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainactivity;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.osamaomar.grz.datalayer.apidata.ApiClient;
import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.datalayer.localdata.LocalDatabase;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.deo.ProductDao;
import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;

public class MainActivityModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public MainActivityModelFactory(Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == MainActivityViewModel.class)
        {
            return (T) new MainActivityViewModel(getApiService(),getRepopsitry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    private ServerGateway getApiService() {
        return ApiClient.getClient().create(ServerGateway.class);
    }

    private ProductAndCategries getRepopsitry() {
        return new ProductAndCategries(getApiService(),getProductDeo());
    }

    private ProductDao getProductDeo() {
        return LocalDatabase.getInstance(application).productDeo();
    }

}
