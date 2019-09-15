package com.codesroots.osamaomar.grz.presentationn.screens.feature.addrate;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.models.entities.DefaultAdd;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class AddRateViewModel extends ViewModel {


    public MutableLiveData<Boolean> AddRateMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ServerGateway serverGateway;


    AddRateViewModel(ServerGateway serverGateway1) {
        serverGateway = serverGateway1;
    }


    public void AddRateToProduct(int userid,int productid,int rate,String comment){
        mCompositeDisposable.add(
                serverGateway.addProductRate(userid,productid,rate,comment,1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postDataResponse,
                                this::postError));
    }


    private void postDataResponse(DefaultAdd addResponse) {
        AddRateMutableLiveData.postValue(addResponse.isSuccess());
    }


    private void postError(Throwable throwable) {
        throwableMutableLiveData.postValue(throwable);
    }

}
