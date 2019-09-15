package com.codesroots.osamaomar.grz.presentationn.screens.feature.payment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.grz.models.entities.OrderModel;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentViewModel extends ViewModel {

    public MutableLiveData<Boolean> myOrdersMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ServerGateway serverGateway;
    private ProductAndCategries productAndCategries;
    public PaymentViewModel(ServerGateway serverGateway1, ProductAndCategries repopsitry) {
        serverGateway = serverGateway1;
        productAndCategries = repopsitry;
    }

    public void cleareCart()
    {
        productAndCategries.deleteAllProduct();
    }

    public void addOrder(OrderModel orderModel) {
        serverGateway.makeOrder(orderModel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                try {
                    if (response != null)
                        myOrdersMutableLiveData.postValue(true);
                } catch (Exception e) {
                   // onError.accept(e.getCause());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (throwableMutableLiveData != null) {
                    throwableMutableLiveData.postValue(t);
                }
            }
        });
    }
}
