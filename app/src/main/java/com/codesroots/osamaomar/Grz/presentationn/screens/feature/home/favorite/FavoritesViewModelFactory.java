package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.favorite;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.osamaomar.grz.datalayer.apidata.ApiClient;
import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    private int subcategry_id,user_Id,type;

    public FavoritesViewModelFactory(Application application1,int userid) {
        application = application1;
        user_Id = userid;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == FavoritesViewModel.class)
        {
            return (T) new FavoritesViewModel(getApiService(),user_Id);
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    private ServerGateway getApiService() {
        return ApiClient.getClient().create(ServerGateway.class);
    }

}
