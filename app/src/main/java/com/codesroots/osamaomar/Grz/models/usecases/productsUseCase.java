package com.codesroots.osamaomar.grz.models.usecases;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.datalayer.repositries.ProductAndCategries;
import com.codesroots.osamaomar.grz.models.entities.AddToFavModel;
import com.codesroots.osamaomar.grz.models.entities.DefaultAdd;
import com.codesroots.osamaomar.grz.models.entities.FinalProductdetails;
import com.codesroots.osamaomar.grz.models.entities.MainView;
import com.codesroots.osamaomar.grz.models.entities.ProductDetails;
import com.codesroots.osamaomar.grz.models.entities.Products;
import com.codesroots.osamaomar.grz.models.entities.mainData;
import com.codesroots.osamaomar.grz.models.entities.Product;
import com.codesroots.osamaomar.grz.models.entities.offers;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class productsUseCase {


    public productsUseCase() {
    }

    Context context;

    public productsUseCase(Context context) {
        this.context = context;
    }

    @SuppressLint("CheckResult")
    public void retrieveHomeFragmentData(CompositeDisposable mCompositeDisposable,
                                         ProductAndCategries productAndCategries, MutableLiveData<mainData> data,
                                         MutableLiveData<String> errormessage) {
        productAndCategries.retrieveHomeFragmentData(0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mainView ->
                this.postDataResponse(mainView, data), throwable -> postError(throwable, errormessage));
    }


    @SuppressLint("CheckResult")
    public void retrieveHomeFragmentDataInPagination(int page, CompositeDisposable mCompositeDisposable,
                                                     ProductAndCategries productAndCategries, MutableLiveData<mainData> data,
                                                     MutableLiveData<String> errormessage) {
        productAndCategries.retrieveHomeFragmentData(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mainView ->
                this.postDataResponse(mainView, data), throwable -> postError(throwable, errormessage));
    }


    @SuppressLint("CheckResult")
    public void retrieveProductDetailsData(CompositeDisposable mCompositeDisposable,
                                           ProductAndCategries productAndCategries, MutableLiveData<FinalProductdetails> data,
                                           MutableLiveData<String> errormessage, int productid) {

        productAndCategries.retrieveDetailsObservable(productid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mainView ->
                this.postProductDetailsData(mainView, data), throwable -> postError(throwable, errormessage));
    }


    @SuppressLint("CheckResult")
    public void retrieveProductsData(CompositeDisposable mCompositeDisposable,
                                     ProductAndCategries productAndCategries, MutableLiveData<List<Product>> data,
                                     MutableLiveData<String> errormessage, int catid, int page, List<Product> resultData) {

        productAndCategries.retrieveProductsData(catid, page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mainView ->
                this.postProducsData(mainView, data, resultData), throwable -> postError(throwable, errormessage));
    }


    @SuppressLint("CheckResult")
    public void retrieveSearchProductsData(CompositeDisposable mCompositeDisposable,
                                           ProductAndCategries productAndCategries, MutableLiveData<List<Product>> data,
                                           MutableLiveData<String> errormessage, String searchkey, String type, List<Product> resultData) {

        productAndCategries.retrieveSearchProductsData(searchkey, type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mainView ->
                this.postProducsData(mainView, data, resultData), throwable ->
                postError(throwable, errormessage));
    }

    private void postProducsData(Products products, MutableLiveData<List<Product>> data, List<Product> resultData) {
        data.postValue(reshapProducts(products.getProductsbycategory()));
    }

    private void postDataResponse(MainView mainViewData, MutableLiveData<mainData> data) {

        mainData mainData = new mainData();
        mainData.setSlider(mainViewData.getSliders());
        mainData.setCategories(mainViewData.getCategory());
        if (mainViewData.getCurrency()!=null)
        mainData.setDollervalue(mainViewData.getCurrency().getValue());
        mainData.setProducts(reshapProducts(mainViewData.getProductsbyrate()));
        data.postValue(mainData);
    }

    private void postProductDetailsData(ProductDetails mainViewData, MutableLiveData<FinalProductdetails> data) {
        FinalProductdetails finalProductdetails = new FinalProductdetails();
        finalProductdetails.setRelatedproducts(reshapProducts(mainViewData.getRelated()));
        finalProductdetails.setProduct(reshapProducts(mainViewData.getProductdetails()).get(0));
        data.postValue(finalProductdetails);
    }

    public void getOffersData(CompositeDisposable mCompositeDisposable,
                              ProductAndCategries productAndCategries, MutableLiveData<List<Product>> data,
                              MutableLiveData<String> errormessag) {

        mCompositeDisposable.add(
                productAndCategries.retrieveOffer()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(offers -> this.postOffersData(offers, data),
                                throwable -> this.postError(throwable, errormessag)));
    }

    private void postOffersData(offers productRates, MutableLiveData<List<Product>> offers) {
        List<ProductDetails.product> products = new ArrayList<>();
        for (int i = 0; i < productRates.getData().size(); i++) {
            if (productRates.getData().get(i).getProduct() != null)
                products.add((productRates.getData().get(i).getProduct()));
        }
        offers.postValue(reshapProducts(products));
    }


    public List<Product> reshapProducts(List<ProductDetails.product> productsbyrate) {

        List<Product> products = new ArrayList<>();

        try {
            for (int i = 0; i < productsbyrate.size(); i++) {
                Product product = new Product();
                product.setProductid(productsbyrate.get(i).getId());
                product.setAmount(productsbyrate.get(i).getAmount());
                product.setNotes(productsbyrate.get(i).getProduct_notes());
                product.setPricewithoutcoin(productsbyrate.get(i).getCurrentPrice());
                product.setOriginalprice(productsbyrate.get(i).getCurrentPrice());

                if (PreferenceHelper.getCurrencyValue() > 0) {
                    product.setPrice(productsbyrate.get(i).getCurrentPrice()* PreferenceHelper.getCurrencyValue() + " " +
                            PreferenceHelper.getCurrency());

                    product.setPricewithoutcoin(productsbyrate.get(i).getCurrentPrice() * PreferenceHelper.getCurrencyValue());


                product.setPrice(new DecimalFormat("##.##").format(productsbyrate.get(i).getCurrentPrice() *
                        PreferenceHelper.getCurrencyValue())+ " " +
                        PreferenceHelper.getCurrency());

                    product.setCurrentcurrency(PreferenceHelper.getCurrency());
                } else {
                    product.setPrice(String.valueOf(productsbyrate.get(i).getCurrentPrice())+ " "  + context.getText(R.string.realcoin));
                    product.setCurrentcurrency(context.getText(R.string.realcoin).toString());
                }

                product.setSizes(productsbyrate.get(i).getProductsizes());
                product.setColores(productsbyrate.get(i).getProduct_colors());
                product.setName(productsbyrate.get(i).getName());

                if (productsbyrate.get(i).getTotal_rating() != null) {
                    calcaulateRate(productsbyrate.get(i).getTotal_rating(), product);
                } else {
                    product.setRate(0);
                    product.setRatecount(0);
                }
                if (productsbyrate.get(i).getProductphotos() != null) {
                    if (productsbyrate.get(i).getProductphotos().size() > 0) {
                        product.setPhotos(productsbyrate.get(i).getProductphotos());
                        product.setPhoto(productsbyrate.get(i).getProductphotos().get(0).getPhoto());
                    }
                }
                if (productsbyrate.get(i).getDescription() != null)
                    product.setDescription(productsbyrate.get(i).getDescription());
                else
                    product.setDescription("غير متوفر");

                if (productsbyrate.get(i).getOffers() != null) {
                    if (productsbyrate.get(i).getOffers().size() > 0) {
                        product.setOfferid(productsbyrate.get(i).getId());
                        product.setHasoffer(true);
                        product.setPricewithoutcoin(productsbyrate.get(i).getCurrentPrice() -
                                productsbyrate.get(i).getCurrentPrice() *
                                        productsbyrate.get(i).getOffers().get(0).getPercentage() / 100);
                        product.setOriginalprice(product.getPricewithoutcoin());
                        product.setDiscountpercentage(productsbyrate.get(i).getOffers().get(0).getPercentage());
                        product.setEnddate(getdate(productsbyrate.get(i).getOffers().get(0).getTo_discount()));
                        product.setRemenderdayes(getDiffDays(getendDateAsMillisec(productsbyrate.get(i).getOffers().get(0).getTo_discount())));
                        if (PreferenceHelper.getCurrencyValue() > 0) {

                            product.setAfteroffer(new DecimalFormat("##.##").format(product.getPricewithoutcoin() *
                                    PreferenceHelper.getCurrencyValue())+ " " +
                                    PreferenceHelper.getCurrency());
//
//                            product.setAfteroffer(product.getPricewithoutcoin() * PreferenceHelper.getCurrencyValue() + " " +
//                                    PreferenceHelper.getCurrency());
                            product.setCurrentcurrency(PreferenceHelper.getCurrency());
                        } else {
                            product.setAfteroffer(String.valueOf(product.getPricewithoutcoin())+" " + context.getText(R.string.realcoin));
                            product.setCurrentcurrency(context.getText(R.string.realcoin).toString());
                        }
                    }
                }
                products.add(product);
            }
        } catch (Exception e) {
            Log.d("sda", e.getMessage());
        }
        return products;
    }

    private void calcaulateRate(List<ProductDetails.product.TotalRatingBean> total_rating, Product product) {
        if (total_rating.size() > 0) {
            product.setRate(total_rating.get(0).getStars() / total_rating.get(0).getCount());
            product.setRatecount(total_rating.get(0).getCount());
        } else {
            product.setRate(0);
            product.setRatecount(0);
        }
    }

    private void postError(Throwable throwable, MutableLiveData<String> errormessage) {
        errormessage.postValue(throwable.toString());
    }

    @SuppressLint("CheckResult")
    public void addFavToProduc(CompositeDisposable compositeDisposable, ProductAndCategries productAndCategries,
                               int productid, int uderid,
                               MutableLiveData<AddToFavModel> add, MutableLiveData<String> error) {
        productAndCategries.addToFav(uderid, productid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mainView ->
                this.postAddToFav(mainView, add), throwable -> postError(throwable, error));
    }


    @SuppressLint("CheckResult")
    public void deleteFavToProduc(CompositeDisposable compositeDisposable, ProductAndCategries productAndCategries, int favid,
                                  MutableLiveData<DefaultAdd> add, MutableLiveData<String> error) {
        productAndCategries.deleteFav(favid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mainView ->
                this.postAddToDelete(mainView, add), throwable -> postError(throwable, error));
    }


    private void postAddToDelete(DefaultAdd mainViewData, MutableLiveData<DefaultAdd> data) {
        data.postValue(mainViewData);
    }

    private void postAddToFav(AddToFavModel mainViewData, MutableLiveData<AddToFavModel> data) {
        data.postValue(mainViewData);
    }

    private String getdate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj = sdf.parse(date);
            Log.d("newdatein", dateObj.getTime() + "");
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Long getendDateAsMillisec(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj = sdf.parse(date);
           Long calendar= dateObj.getTime();
           return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getDiffDays (Long thatDay)
    {
        Calendar today = Calendar.getInstance();
        long diff = thatDay - today.getTimeInMillis();
        Log.d("asec",today.getTimeInMillis()+"");
        long days = diff / (24 * 60 * 60 * 1000);
        return (int) days;
    }
}
