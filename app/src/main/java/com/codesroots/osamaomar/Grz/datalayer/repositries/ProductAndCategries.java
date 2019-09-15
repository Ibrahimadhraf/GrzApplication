package com.codesroots.osamaomar.grz.datalayer.repositries;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.deo.ProductDao;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.entities.ProductDB;
import com.codesroots.osamaomar.grz.models.entities.AddToFavModel;
import com.codesroots.osamaomar.grz.models.entities.DefaultAdd;
import com.codesroots.osamaomar.grz.models.entities.MainView;
import com.codesroots.osamaomar.grz.models.entities.ProductDetails;
import com.codesroots.osamaomar.grz.models.entities.Products;
import com.codesroots.osamaomar.grz.models.entities.SubCategriesWithProducts;
import com.codesroots.osamaomar.grz.models.entities.offers;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class ProductAndCategries {

    ServerGateway serverGateway;
    ProductDao productDao;

    public ProductAndCategries(ServerGateway serverGateway, ProductDao PDeo) {
        this.serverGateway = serverGateway;
        this.productDao = PDeo;
    }

    public Observable<MainView> retrieveHomeFragmentData(int page) {
        if (page>1)
            return serverGateway.getProductsPaginationinmainPage(page);
        else
        return serverGateway.getMainViewData();
    }


    public Observable<ProductDetails> retrieveDetailsObservable(int productid) {
        return serverGateway.getProductDetails(productid);
    }

    public Observable<Products> retrieveProductsData(int catid,int page) {
        return serverGateway.getProducts(catid,page);
    }

    public Observable<offers> retrieveOffer() {
        return serverGateway.retrieveOffers();
    }


    public Observable<Products> retrieveSearchProductsData(String searchkey, String type) {
        return serverGateway.getSearchResult(searchkey, type);
    }




    public Observable<AddToFavModel> addToFav(int userid, int productid) {
        return serverGateway.addToFave(userid, productid);
    }

    public void addProductToCart(ProductDB product, MutableLiveData<String> statues) {
        Observable.fromCallable(() -> productDao.insertProduct(product)).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long aLong) {
            }

            @Override
            public void onError(Throwable e) {
                statues.postValue("2");
            }

            @Override
            public void onComplete() {
                statues.postValue("1");
            }
        });
    }

    public void checkifExists(ProductDB product, MutableLiveData<String> statues) {
        Single.fromCallable(() -> productDao.checkifexists(product.getProduct_id())).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Integer integer) {
                if (integer > 0)
                    statues.postValue("0");
                else
                    addProductToCart(product, statues);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void deleteItemFromCart(int product) {
        Single.fromCallable(() -> productDao.deleteProduct(product)).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Integer integer) {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }


    public Single<List<ProductDB>> getAllProduct() {
        return productDao.getproductids().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public void deleteAllProduct() {
        Completable.fromAction(() -> productDao.deleteall()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();
     //   return productDao.deleteall().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DefaultAdd> deleteFav(int favid) {
        return serverGateway.DeleteFav(favid);
    }

}
