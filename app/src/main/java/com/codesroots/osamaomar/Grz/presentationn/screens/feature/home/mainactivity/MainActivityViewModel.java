package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainactivity;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.entities.ProductDB;
import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.grz.models.entities.Sidemenu;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


class MainActivityViewModel extends ViewModel {


    public MutableLiveData<Integer> cartItemsCount = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private ServerGateway serverGateway;
    private ProductAndCategries productAndCategries;


     MainActivityViewModel(ServerGateway serverGateway1, ProductAndCategries repositry) {
        serverGateway = serverGateway1;
         productAndCategries = repositry;

     }




    public void retrieveCount()
    {
        productAndCategries.getAllProduct().subscribe(new SingleObserver<List<ProductDB>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(List<ProductDB> integers) {
                cartItemsCount.postValue(integers.size());
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }


}
