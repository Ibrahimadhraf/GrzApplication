package com.codesroots.osamaomar.grz.models.entities;

import java.io.Serializable;
import java.util.List;

public class MyOrders {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * order_id : 1
         * user_id : 1
         * billing_id : 1
         * order_subtotal : 123
         * order_shipping : 12
         * order_gtotal : 1231
         * order_notes : 123sd
         * tracking_number : 123
         * status : 2
         * customer_confirmed : 0
         * created_at : 2019-05-07T02:08:52+0000
         * updated_at : 2019-05-11T02:25:05+0000
         * order_details : [{"uid":1,"order_id":1,"product_id":1,"product_price":12,"product_qty":12,"size":1,"color":1,"status":0,"created_at":"2019-05-07T02:09:12+0000","updated_at":"2019-05-07T02:09:12+0000","product":{"product_id":1,"currentPrice":"12","product_price":12,"name":"شسيشسيشسي","total_rating":[{"product_id":1,"stars":23,"count":3}],"productphotos":[{"product_id":1,"photo":"http://shopgate.codesroots.com/library/attachment/15567457991223780712.jpg"}]}},{"uid":2,"order_id":1,"product_id":1,"product_price":12,"product_qty":1,"size":1,"color":null,"status":0,"created_at":"2019-05-09T23:53:29+0000","updated_at":"2019-05-11T02:09:07+0000","product":{"product_id":1,"currentPrice":"12","product_price":12,"name":"شسيشسيشسي","total_rating":[{"product_id":1,"stars":23,"count":3}],"productphotos":[{"product_id":1,"photo":"http://shopgate.codesroots.com/library/attachment/15567457991223780712.jpg"}]}}]
         */

        private int order_id;
        private int user_id;
        private int billing_id;
        private int order_status;
        private float order_subtotal;
        private float order_shipping;
        private float order_gtotal;
        private String order_notes;
        private String tracking_number;
        private int status;
        private int customer_confirmed;
        private String created_at;
        private String updated_at;
        private List<OrderDetailsBean> order_details;

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getBilling_id() {
            return billing_id;
        }

        public void setBilling_id(int billing_id) {
            this.billing_id = billing_id;
        }

        public float getOrder_subtotal() {
            return order_subtotal;
        }

        public void setOrder_subtotal(float order_subtotal) {
            this.order_subtotal = order_subtotal;
        }

        public float getOrder_shipping() {
            return order_shipping;
        }

        public void setOrder_shipping(int order_shipping) {
            this.order_shipping = order_shipping;
        }

        public float getOrder_gtotal() {
            return order_gtotal;
        }

        public void setOrder_gtotal(float order_gtotal) {
            this.order_gtotal = order_gtotal;
        }

        public String getOrder_notes() {
            return order_notes;
        }

        public void setOrder_notes(String order_notes) {
            this.order_notes = order_notes;
        }

        public String getTracking_number() {
            return tracking_number;
        }

        public void setTracking_number(String tracking_number) {
            this.tracking_number = tracking_number;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCustomer_confirmed() {
            return customer_confirmed;
        }

        public void setCustomer_confirmed(int customer_confirmed) {
            this.customer_confirmed = customer_confirmed;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public List<OrderDetailsBean> getOrder_details() {
            return order_details;
        }

        public void setOrder_details(List<OrderDetailsBean> order_details) {
            this.order_details = order_details;
        }

        public static class OrderDetailsBean {
            /**
             * uid : 1
             * order_id : 1
             * product_id : 1
             * product_price : 12
             * product_qty : 12
             * size : 1
             * color : 1
             * status : 0
             * created_at : 2019-05-07T02:09:12+0000
             * updated_at : 2019-05-07T02:09:12+0000
             * product : {"product_id":1,"currentPrice":"12","product_price":12,"name":"شسيشسيشسي","total_rating":[{"product_id":1,"stars":23,"count":3}],"productphotos":[{"product_id":1,"photo":"http://shopgate.codesroots.com/library/attachment/15567457991223780712.jpg"}]}
             */

            private int uid;
            private int order_id;
            private int product_id;
            private float product_price;
            private int product_qty;
            private int size;
            private int color;
            private int status;
            private String created_at;
            private String updated_at;
            private ProductBean product;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public float getProduct_price() {
                return product_price;
            }

            public void setProduct_price(float product_price) {
                this.product_price = product_price;
            }

            public int getProduct_qty() {
                return product_qty;
            }

            public void setProduct_qty(int product_qty) {
                this.product_qty = product_qty;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getColor() {
                return color;
            }

            public void setColor(int color) {
                this.color = color;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public ProductBean getProduct() {
                return product;
            }

            public void setProduct(ProductBean product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
                 * product_id : 1
                 * currentPrice : 12
                 * product_price : 12
                 * name : شسيشسيشسي
                 * total_rating : [{"product_id":1,"stars":23,"count":3}]
                 * productphotos : [{"product_id":1,"photo":"http://shopgate.codesroots.com/library/attachment/15567457991223780712.jpg"}]
                 */

                private int product_id;
                private String currentPrice;
                private float product_price;
                private String name;
                private List<TotalRatingBean> total_rating;
                private List<ProductphotosBean> productphotos;

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public String getCurrentPrice() {
                    return currentPrice;
                }

                public void setCurrentPrice(String currentPrice) {
                    this.currentPrice = currentPrice;
                }

                public float getProduct_price() {
                    return product_price;
                }

                public void setProduct_price(float product_price) {
                    this.product_price = product_price;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<TotalRatingBean> getTotal_rating() {
                    return total_rating;
                }

                public void setTotal_rating(List<TotalRatingBean> total_rating) {
                    this.total_rating = total_rating;
                }

                public List<ProductphotosBean> getProductphotos() {
                    return productphotos;
                }

                public void setProductphotos(List<ProductphotosBean> productphotos) {
                    this.productphotos = productphotos;
                }

                public static class TotalRatingBean {
                    /**
                     * product_id : 1
                     * stars : 23
                     * count : 3
                     */

                    private int product_id;
                    private int stars;
                    private int count;

                    public int getProduct_id() {
                        return product_id;
                    }

                    public void setProduct_id(int product_id) {
                        this.product_id = product_id;
                    }

                    public int getStars() {
                        return stars;
                    }

                    public void setStars(int stars) {
                        this.stars = stars;
                    }

                    public int getCount() {
                        return count;
                    }

                    public void setCount(int count) {
                        this.count = count;
                    }
                }

                public static class ProductphotosBean {
                    /**
                     * product_id : 1
                     * photo : http://shopgate.codesroots.com/library/attachment/15567457991223780712.jpg
                     */

                    private int product_id;
                    private String photo;

                    public int getProduct_id() {
                        return product_id;
                    }

                    public void setProduct_id(int product_id) {
                        this.product_id = product_id;
                    }

                    public String getPhoto() {
                        return photo;
                    }

                    public void setPhoto(String photo) {
                        this.photo = photo;
                    }
                }
            }
        }
    }
}
