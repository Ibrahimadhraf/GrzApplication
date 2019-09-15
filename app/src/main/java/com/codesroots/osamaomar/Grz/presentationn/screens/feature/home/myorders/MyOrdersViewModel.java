package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.models.entities.MyOrders;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MyOrdersViewModel extends ViewModel {

    public MutableLiveData<MyOrders> myOrdersMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> edit = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ServerGateway serverGateway;
    private int user_id;
    private int COMPLETES_STATUES=3;
    public MyOrdersViewModel(ServerGateway serverGateway1, int id) {
        serverGateway = serverGateway1;
        user_id = id;
        getMyOrders();
    }

    private void getMyOrders(){
        mCompositeDisposable.add(
                serverGateway.retrieveUserOrders(user_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( this::postDataResponse,
                                this::postError));
    }

    public void editOrder(int orderid){
        mCompositeDisposable.add(
                serverGateway.editOrderStatues(orderid,COMPLETES_STATUES)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(editorder ->  edit.postValue(editorder.isSuccess()),
                                this::postError));
    }



    private void postDataResponse(MyOrders productRates) {
        myOrdersMutableLiveData.postValue(productRates);
    }

    private void postError(Throwable throwable) {
        throwableMutableLiveData.postValue(throwable);
    }

}
