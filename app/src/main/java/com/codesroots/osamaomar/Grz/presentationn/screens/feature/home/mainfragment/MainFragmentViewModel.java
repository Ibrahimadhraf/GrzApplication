package com.codesroots.osamaomar.Grz.presentationn.screens.feature.home.mainfragment;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.codesroots.osamaomar.Grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.Grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.Grz.models.entities.HomeFragment;
import com.codesroots.osamaomar.Grz.models.entities.MainView;
import com.codesroots.osamaomar.Grz.models.entities.Products;
import com.codesroots.osamaomar.Grz.models.usecases.productsUseCase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


class MainFragmentViewModel extends ViewModel {

    public MutableLiveData<HomeFragment> mainViewMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errormessage = new MutableLiveData<>();

    private ProductAndCategries productAndCategriesrepositry;
    private productsUseCase productsUseCase;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

     MainFragmentViewModel(ProductAndCategries productAndCategries,productsUseCase useCase) {
         productAndCategriesrepositry = productAndCategries;
         productsUseCase = useCase;
         getData();
    }

    public void getData() {
        productsUseCase.retrieveHomeFragmentData(mCompositeDisposable,productAndCategriesrepositry,mainViewMutableLiveData,errormessage);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
    }
}
