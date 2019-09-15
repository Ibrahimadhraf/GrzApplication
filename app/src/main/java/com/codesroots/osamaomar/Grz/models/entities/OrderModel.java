package com.codesroots.osamaomar.grz.models.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderModel implements Serializable {

    @SerializedName("customer_id")
    int user_id;

    @SerializedName("billing_id")
    String billing_id;

    @SerializedName("order_status")
    int order_status=2;

    @SerializedName("status")
    int status=1;

    @SerializedName("currency_id")
    int currency_id;


    @SerializedName("order_subtotal")
    String order_subtotal;


    @SerializedName("order_gtotal")
    String order_gtotal;


    @SerializedName("order_shipping")
    String order_shipping="0";


    @SerializedName("order_notes")
    String order_notes="";


    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrder_gtotal() {
        return order_gtotal;
    }

    public void setOrder_gtotal(String order_gtotal) {
        this.order_gtotal = order_gtotal;
    }

    public String getOrder_shipping() {
        return order_shipping;
    }

    public void setOrder_shipping(String order_shipping) {
        this.order_shipping = order_shipping;
    }

    public String getOrder_notes() {
        return order_notes;
    }

    public void setOrder_notes(String order_notes) {
        this.order_notes = order_notes;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }



    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }


    public String getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(String billing_id) {
        this.billing_id = billing_id;
    }

    public String getOrder_subtotal() {
        return order_subtotal;
    }

    public void setOrder_subtotal(String order_subtotal) {
        this.order_subtotal = order_subtotal;
    }

    public List<productSize> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<productSize> orderdetails) {
        this.orderdetails = orderdetails;
    }

    @SerializedName("orderDetails")
    List<productSize> orderdetails;

    public static  class  productSize implements Serializable
    {
        @SerializedName("product_id")
        int product_Id;

        public productSize(int productsize_id) {
            this.product_Id = productsize_id;
        }

        @SerializedName("product_qty")
        int amount = 1;

        @SerializedName("color")
        int color = 0;

        @SerializedName("size")
        int size = 0;

        @SerializedName("product_price")
        String total;

        @SerializedName("notice")
        String notice;

        float originalTotal;

        public float getOriginalTotal() {
            return originalTotal;
        }

        public void setOriginalTotal(float originalTotal) {
            this.originalTotal = originalTotal;
        }


        public int getProductsize_id() {
            return product_Id;
        }

        public void setProductsize_id(int productsize_id) {
            this.product_Id = productsize_id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }
    }


}
