package com.codesroots.osamaomar.grz.presentationn.screens.feature.contact;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.models.entities.AddLocation;
import com.codesroots.osamaomar.grz.models.entities.Contact;
import com.codesroots.osamaomar.grz.models.entities.UserLocations;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ContactViewModel extends ViewModel {

    public MutableLiveData<Contact> contactMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> error = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ServerGateway serverGateway;

    public ContactViewModel(ServerGateway serverGateway) {
        this.serverGateway = serverGateway;
        getContactsData();
    }


    private void getContactsData()
    {
       mCompositeDisposable.add(serverGateway.getContacts().
               observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                subscribe(contact -> contactMutableLiveData.postValue(contact),
                        throwable -> error.postValue(throwable)));
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
    }
}
