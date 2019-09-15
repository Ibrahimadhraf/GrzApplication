package com.codesroots.osamaomar.grz.models.entities;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name, photo, description, price,currentcurrency,afteroffer,enddate,notes,colorname,sizename;
    private float rate, offerpercentage,pricewithoutcoin=0,discountpercentage,originalprice;
    private int ratecount, productid, amount, favid, offerid, colorid = 0, sizeid = 0,remenderdayes;
    private List<com.codesroots.osamaomar.grz.models.entities.ProductDetails.product.ProductphotosBean> photos = new ArrayList<>();
    private List<com.codesroots.osamaomar.grz.models.entities.ProductDetails.product.ProductsizesBean> product_sizes = new ArrayList<>();
    private List<com.codesroots.osamaomar.grz.models.entities.ProductDetails.product.ColorBean> colores = new ArrayList<>();
    private List<Product> related = new ArrayList<>();

    public int getRemenderdayes() {
        return remenderdayes;
    }

    public void setRemenderdayes(int remenderdayes) {
        this.remenderdayes = remenderdayes;
    }

    public List<Product> getRelated() {
        return related;
    }

    public float getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(float originalprice) {
        this.originalprice = originalprice;
    }

    public void setRelated(List<Product> related) {
        this.related = related;
    }

    public int getColorid() {
        return colorid;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    public String getSizename() {
        return sizename;
    }

    public void setSizename(String sizename) {
        this.sizename = sizename;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCurrentcurrency() {
        return currentcurrency;
    }

    public void setCurrentcurrency(String currentcurrency) {
        this.currentcurrency = currentcurrency;
    }

    public float getPricewithoutcoin() {
        return pricewithoutcoin;
    }

    public void setPricewithoutcoin(float pricewithoutcoin) {
        this.pricewithoutcoin = pricewithoutcoin;
    }

    public void setColorid(int colorid) {
        this.colorid = colorid;
    }

    public int getSizeid() {
        return sizeid;
    }

    public void setSizeid(int sizeid) {
        this.sizeid = sizeid;
    }

    private boolean freecharge;

    public int getOfferid() {
        return offerid;
    }

    public void setOfferid(int offerid) {
        this.offerid = offerid;
    }

    public float getDiscountpercentage() {
        return discountpercentage;
    }

    public void setDiscountpercentage(float discountpercentage) {
        this.discountpercentage = discountpercentage;
    }

    public float getOfferpercentage() {
        return offerpercentage;
    }

    public void setOfferpercentage(float offerpercentage) {
        this.offerpercentage = offerpercentage;
    }

    public int getFavid() {
        return favid;
    }

    public void setFavid(int favid) {
        this.favid = favid;
    }

    public boolean isFavoret() {
        return favoret;
    }

    public void setFavoret(boolean favoret) {
        this.favoret = favoret;
    }

    private boolean favoret;
    private String charge_rules;
    private boolean hasoffer = false;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<ProductDetails.product.ColorBean> getColores() {
        return colores;
    }

    public void setColores(List<ProductDetails.product.ColorBean> colores) {
        if (colores != null) {
            if (colores.size() > 0) {
                this.setColorid(colores.get(0).getUid());
                this.setColorname(colores.get(0).getColor().getName());
            }
        }
        this.colores = colores;
    }

    public List<ProductDetails.product.ProductsizesBean> getSizes() {
        return product_sizes;
    }

    public void setSizes(List<ProductDetails.product.ProductsizesBean> sizes) {
        if (sizes != null) {
            if (sizes.size() > 0) {
                this.setSizeid(sizes.get(0).getUid());
                this.setSizename(sizes.get(0).getSize().getSize_title());
            }
        }
        this.product_sizes = sizes;
    }

    public List<ProductDetails.product.ProductphotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ProductDetails.product.ProductphotosBean> photos) {
        this.photos = photos;
    }


    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public boolean isFreecharge() {
        return freecharge;
    }

    public void setFreecharge(boolean freecharge) {
        this.freecharge = freecharge;
    }

    public String getCharge_rules() {
        return charge_rules;
    }

    public void setCharge_rules(String charge_rules) {
        this.charge_rules = charge_rules;
    }

    public boolean isHasoffer() {
        return hasoffer;
    }

    public void setHasoffer(boolean hasoffer) {
        this.hasoffer = hasoffer;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getPrice() {
        if (price == null)
            return "0";
        else
            return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAfteroffer() {
        return afteroffer;
    }

    public void setAfteroffer(String afteroffer) {
        this.afteroffer = afteroffer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRatecount() {
        return ratecount;
    }

    public void setRatecount(int ratecount) {
        this.ratecount = ratecount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
