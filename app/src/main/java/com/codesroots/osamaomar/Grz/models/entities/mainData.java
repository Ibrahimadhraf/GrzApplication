package com.codesroots.osamaomar.grz.models.entities;

import java.util.ArrayList;
import java.util.List;

public class mainData {


    public mainData() {
    }

    List<MainView.SlidersBean> Slider = new ArrayList<>();
    public List<Product> products = new ArrayList<>();
    List<MainView.CategoryBean> categories = new ArrayList<>();
    private float dollervalue = 1;

    public float getDollervalue() {
        return dollervalue;
    }

    public void setDollervalue(float dollervalue) {
        this.dollervalue = dollervalue;
    }

    public List<MainView.SlidersBean> getSlider() {
        return Slider;
    }

    public void setSlider(List<MainView.SlidersBean> slider) {
        Slider = slider;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<MainView.CategoryBean> getCategories() {
        return categories;
    }

    public void setCategories(List<MainView.CategoryBean> categories) {
        this.categories = categories;
    }
}

