package com.codesroots.osamaomar.Grz.models.entities;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment {


        public HomeFragment() {
        }

        List<MainView.SlidersBean> Slider = new ArrayList<>();
      public   List<Product> products = new ArrayList<>();
        List<MainView.CategoryBean> categories = new ArrayList<>();

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

