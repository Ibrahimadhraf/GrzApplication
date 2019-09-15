package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.osamaomar.grz.datalayer.apidata.ApiClient;
import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.datalayer.localdata.LocalDatabase;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.deo.ProductDao;
import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.grz.models.usecases.SubcatesWithProductsUseCase;
import com.codesroots.osamaomar.grz.models.usecases.productsUseCase;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.chating.ChatViewModel;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.contact.ContactViewModel;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.cartfragment.CartViewModel;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productfragment.ProductsViewModel;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.payment.PaymentViewModel;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.userlocations.UserLocationsViewModel;

    public class MainViewModelFactory implements ViewModelProvider.Factory {


    private Application application;

    public MainViewModelFactory(Application application1) {
        application = application1;

    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.ProductsDetailsViewModel.class) {
            return (T) new com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.ProductsDetailsViewModel(getRepopsitry(), getUseCase());
        } else if (modelClass == ProductsViewModel.class) {
            return (T) new ProductsViewModel(getRepopsitry(), getUseCase());
        } else if (modelClass == UserLocationsViewModel.class) {
            return (T) new UserLocationsViewModel(getApiService());
        }
        else if (modelClass == CartViewModel.class) {
            return (T) new CartViewModel(getApiService(),getRepopsitry(),getUseCase());
        }
        else if (modelClass == ChatViewModel.class) {
            return (T) new ChatViewModel(getApiService());
        }
        else  if (modelClass == PaymentViewModel.class)
        {
            return (T) new PaymentViewModel(getApiService(),getRepopsitry());
        }
        else  if (modelClass == ContactViewModel.class)
        {
            return (T) new ContactViewModel(getApiService());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }


    private ServerGateway getApiService() {
        return ApiClient.getClient().create(ServerGateway.class);
    }


    private productsUseCase getUseCase() {
        return new productsUseCase(application.getBaseContext());
    }

    private SubcatesWithProductsUseCase getProductUseCase() {
        return new SubcatesWithProductsUseCase(getUseCase());
    }


    private ProductAndCategries getRepopsitry() {
        return new ProductAndCategries(getApiService(),getProductDeo());
    }

    private ProductDao getProductDeo() {
        return LocalDatabase.getInstance(application).productDeo();
    }

}
