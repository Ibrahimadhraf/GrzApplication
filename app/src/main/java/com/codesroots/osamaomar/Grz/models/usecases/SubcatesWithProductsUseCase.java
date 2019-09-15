package com.codesroots.osamaomar.grz.models.usecases;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.grz.models.entities.ProductDetails;
import com.codesroots.osamaomar.grz.models.entities.SubCategriesWithProducts;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SubcatesWithProductsUseCase {


    public productsUseCase productsUseCase;

    public SubcatesWithProductsUseCase(productsUseCase productsUseCase1) {
        this.productsUseCase = productsUseCase1;
    }

//    @SuppressLint("CheckResult")
//    public void retrieveSubCatesWithproductData(CompositeDisposable mCompositeDisposable,
//                                                ProductAndCategries productAndCategries, MutableLiveData<SubCategriesWithProducts> data,
//                                                MutableLiveData<String> errormessage, int categry, int userid) {
//
//        productAndCategries.retrieveSubCatesWithProduct(categry, userid).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(mainView ->
//                this.postDataResponse(mainView, data), throwable -> postError(throwable, errormessage));
//    }


    private void postDataResponse(SubCategriesWithProducts categriesWithProducts,
                                  MutableLiveData<SubCategriesWithProducts> data) {

        List<ProductDetails.product> products = new ArrayList<>();
        for (int i = 0; i < categriesWithProducts.getProductsbyrate().size(); i++) {
            if (categriesWithProducts.getProductsbyrate().get(i).getProduct() != null)
                products.add(categriesWithProducts.getProductsbyrate().get(i).getProduct());
        }
        categriesWithProducts.setProducts(productsUseCase.reshapProducts(products));
        data.postValue(categriesWithProducts);
    }

    private void postError(Throwable throwable, MutableLiveData<String> errormessage) {
        errormessage.postValue(throwable.toString());
    }

}
