package com.codesroots.osamaomar.Grz.presentationn.screens.feature.home.offerfragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.osamaomar.Grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.Grz.models.entities.offers;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


class OffersViewModel extends ViewModel {


    public MutableLiveData<offers> offersMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ServerGateway serverGateway;

    OffersViewModel(ServerGateway serverGateway1) {
        serverGateway = serverGateway1;
        getOffersData();
    }

    private void getOffersData(){
        mCompositeDisposable.add(
                serverGateway.retrieveOffers()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postDataResponse,
                                this::postError));
    }

    private void postDataResponse(offers productRates) {
        offersMutableLiveData.postValue(productRates);
    }

    private void postError(Throwable throwable) {
        throwableMutableLiveData.postValue(throwable);
    }


}
