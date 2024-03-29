package com.codesroots.osamaomar.Grz.presentationn.screens.feature.home.offerfragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.osamaomar.Grz.datalayer.apidata.ApiClient;
import com.codesroots.osamaomar.Grz.datalayer.apidata.ServerGateway;

public class OffersViewModelFactory implements ViewModelProvider.Factory {


    private Application application;

    public OffersViewModelFactory(Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == OffersViewModel.class)
        {
            return (T) new OffersViewModel(getApiService());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }

    private ServerGateway getApiService() {
        return ApiClient.getClient().create(ServerGateway.class);
    }

}
