package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.osamaomar.grz.datalayer.localdata.product.entities.ProductDB;
import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.grz.models.entities.FinalProductdetails;
import com.codesroots.osamaomar.grz.models.entities.Product;
import com.codesroots.osamaomar.grz.models.entities.mainData;
import com.codesroots.osamaomar.grz.models.usecases.productsUseCase;
import java.util.List;
import io.reactivex.disposables.CompositeDisposable;


public class ProductsDetailsViewModel extends ViewModel {

    public MutableLiveData<mainData> mainViewMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<FinalProductdetails> productMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Product>> offerproductsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errormessage = new MutableLiveData<>();
    private ProductAndCategries productAndCategriesrepositry;
    private productsUseCase productsUseCase;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ProductAndCategries productAndCategries;
    public MutableLiveData<String> statues = new MutableLiveData<>();

    ProductsDetailsViewModel(ProductAndCategries productAndCategries, productsUseCase useCase) {
         productAndCategriesrepositry = productAndCategries;
         productsUseCase = useCase;
    }


    public void AddToCart(ProductDB product)
    {
        productAndCategriesrepositry.checkifExists(product, statues);
    }

    public void getData() {
        productsUseCase.retrieveHomeFragmentData(mCompositeDisposable,productAndCategriesrepositry,mainViewMutableLiveData,errormessage);
    }


    public void getDataInPAginate(int page) {
        productsUseCase.retrieveHomeFragmentDataInPagination(page,mCompositeDisposable,productAndCategriesrepositry,mainViewMutableLiveData,errormessage);
    }





    public void getOffersData() {
        productsUseCase.getOffersData(mCompositeDisposable,productAndCategriesrepositry,offerproductsMutableLiveData,errormessage);
    }

    public void getProductDetailsData(int productid) {
        productsUseCase.retrieveProductDetailsData(mCompositeDisposable,productAndCategriesrepositry,
                productMutableLiveData,errormessage,productid);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
    }


}
