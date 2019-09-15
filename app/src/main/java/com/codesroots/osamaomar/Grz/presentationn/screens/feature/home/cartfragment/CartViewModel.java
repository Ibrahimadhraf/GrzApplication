package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.cartfragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.entities.ProductDB;
import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.grz.models.entities.CartItems;
import com.codesroots.osamaomar.grz.models.entities.Product;
import com.codesroots.osamaomar.grz.models.usecases.productsUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CartViewModel extends ViewModel {

    public MutableLiveData<List<Product>> cartItemsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<ProductDB>> listMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> noItemsFound = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ServerGateway serverGateway;
    private ArrayList<Integer> product_ids = new ArrayList<>();
    private List<ProductDB> productDBS = new ArrayList<>();
    private productsUseCase productsUseCase = new productsUseCase();
    private ProductAndCategries productAndCategries;

    public CartViewModel(ServerGateway serverGateway1, ProductAndCategries repositry,
                         productsUseCase useCase) {
        serverGateway = serverGateway1;
        productAndCategries = repositry;
        productsUseCase = useCase;
        getAllProducts();
    }


    public void getAllProducts() {
        productAndCategries.getAllProduct().subscribe(new SingleObserver<List<ProductDB>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(List<ProductDB> products) {
                productDBS = products;
                for (int i = 0; i < products.size(); i++)
                    product_ids.add(products.get(i).getProduct_id());
                if (product_ids.size() > 0) {
                    listMutableLiveData.postValue(products);
                    getCartProducts();
                } else
                    noItemsFound.postValue(true);
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }


    public void deleteItem(int productDB) {
        productAndCategries.deleteItemFromCart(productDB);
        productAndCategries.getAllProduct();
    }

    private void getCartProducts() {
        mCompositeDisposable.add(
                serverGateway.getCartProducts(product_ids)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postDataResponse,
                                this::postError));
    }


    private void postDataResponse(CartItems cartItems) {
        List<Product> products = productsUseCase.reshapProducts(cartItems.getData());
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setColorname(productDBS.get(i).getColor_name());
            products.get(i).setSizename(productDBS.get(i).getSize_name());
        }
        cartItemsMutableLiveData.postValue(products);
    }


    private void postError(Throwable throwable) {
        throwableMutableLiveData.postValue(throwable);
    }

}
