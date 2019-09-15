package com.codesroots.osamaomar.grz.models.entities;

import java.util.List;

public class FinalProductdetails {

   private List<Product> relatedproducts ;
   private   Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getRelatedproducts() {
        return relatedproducts;
    }

    public void setRelatedproducts(List<Product> relatedproducts) {
        this.relatedproducts = relatedproducts;
    }
}
