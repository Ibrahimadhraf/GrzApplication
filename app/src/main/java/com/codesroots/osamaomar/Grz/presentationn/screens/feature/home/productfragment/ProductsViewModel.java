package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productfragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.codesroots.osamaomar.grz.datalayer.apidata.ServerGateway;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.entities.ProductDB;
import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.grz.models.entities.AddToFavModel;
import com.codesroots.osamaomar.grz.models.entities.DefaultAdd;
import com.codesroots.osamaomar.grz.models.entities.Product;
import com.codesroots.osamaomar.grz.models.usecases.productsUseCase;
import java.util.Collections;
import java.util.List;
import io.reactivex.disposables.CompositeDisposable;

public class ProductsViewModel extends ViewModel {

    public MutableLiveData<List<Product>> productsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<AddToFavModel> addToFavMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<DefaultAdd> deleteToFavMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<String> throwablefav = new MutableLiveData<>();
    public MutableLiveData<String> statues = new MutableLiveData<>();
    private ServerGateway serverGateway;
    private int subcattegry_id, userid,type;
    private  List<Product> resultData;
    public int current_item = 0;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private productsUseCase productsUseCase;
    private ProductAndCategries productAndCategries;

    public List<Product> getResultData() {
        return resultData;
    }

    public void setResultData(List<Product> resultData) {
        this.resultData = resultData;
    }

    public ProductsViewModel(ProductAndCategries repositry, productsUseCase useCase ) {
       productAndCategries = repositry;
        productsUseCase = useCase;
    }

    public void getData (int id,int page)
    {
        productsUseCase.retrieveProductsData(mCompositeDisposable,productAndCategries,productsMutableLiveData,
                errorMessage,id,page,resultData);
    }



    public void AddToCart(ProductDB product)
    {

        productAndCategries.checkifExists(product, statues);
    }

    public void getSearchProductData (String type1,String key)
    {
        productsUseCase.retrieveSearchProductsData
                (mCompositeDisposable,productAndCategries,productsMutableLiveData,errorMessage,key,type1,resultData);
    }


    public  void comparewithprice()
    {
        Collections.sort(resultData, (o1, o2) -> {
            return Float.valueOf(o1.getPricewithoutcoin()).compareTo(o2.getPricewithoutcoin());
        });
        productsMutableLiveData.postValue(resultData);
    }

    public  void comparewithspilling()
    {
        Collections.sort(resultData, (o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
        productsMutableLiveData.postValue(resultData);
    }


}
