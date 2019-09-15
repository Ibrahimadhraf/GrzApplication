package com.codesroots.osamaomar.grz.models.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetails {


    private List<product> productdetails;
    private List<product> related;

    public List<product> getRelated() {
        return related;
    }

    public void setRelated(List<product> related) {
        this.related = related;
    }

    public List<product> getProductdetails() {
        return productdetails;
    }

    public void setProductdetails(List<product> productdetails) {
        this.productdetails = productdetails;
    }

    public static class product {
        /**
         * id : 74
         * name : الكولاجين البحري
         * name_en : Marine Collagen
         * description : الكولاجين البحري
         * description_en : Marine Collagen
         * productsizes : [{"id":111,"id":74,"start_price":"12","amount":10,"size":"60 س"}]
         * total_rating : [{"id":74,"stars":27,"count":7}]
         * productphotos : [{"id":74,"id":44,"photo":"http://shopgate.codesroots.com/library/attachment/1555320503900203421.jpg"}]
         * offers : [{"id":84,"percentage":"18","id":74}]
         * favourites : []
         */

        private int product_id;
        private String name;
        private String name_en;
        private String description;
        private String product_notes;
        private String description_en;
        private int amount;
        @SerializedName("currentPrice")
        private float currentPrice;

        public String getProduct_notes() {
            return product_notes;
        }

        public void setProduct_notes(String product_notes) {
            this.product_notes = product_notes;
        }

        @SerializedName("product_price")
        private float product_price;

        private List<ProductsizesBean> product_sizes;
        private List<ColorBean> product_colors;
        private List<TotalRatingBean> total_rating;
        private List<ProductphotosBean> productphotos;
        @SerializedName("product_discounts")
        private List<OffersBean> offers;
        private List<FavouritesBean> favourites;
        private List<product> related;

        public List<product> getRelated() {
            return related;
        }

        public void setRelated(List<product> related) {
            this.related = related;
        }

        public float getProduct_price() {
            return product_price;
        }

        public void setProduct_price(float product_price) {
            this.product_price = product_price;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public List<ProductsizesBean> getProduct_sizes() {
            return product_sizes;
        }

        public void setProduct_sizes(List<ProductsizesBean> product_sizes) {
            this.product_sizes = product_sizes;
        }

        public List<ColorBean> getProduct_colors() {
            return product_colors;
        }

        public void setProduct_colors(List<ColorBean> product_colors) {
            this.product_colors = product_colors;
        }

        public float getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(float currentPrice) {
            this.currentPrice = currentPrice;
        }

        public int getId() {
            return product_id;
        }

        public void setId(int id) {
            this.product_id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription_en() {
            return description_en;
        }

        public void setDescription_en(String description_en) {
            this.description_en = description_en;
        }

        public List<ProductsizesBean> getProductsizes() {
            return product_sizes;
        }

        public void setProductsizes(List<ProductsizesBean> productsizes) {
            this.product_sizes = productsizes;
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

        public List<OffersBean> getOffers() {
            return offers;
        }

        public void setOffers(List<OffersBean> offers) {
            this.offers = offers;
        }

        public List<FavouritesBean> getFavourites() {
            return favourites;
        }

        public void setFavourites(List<FavouritesBean> favourites) {
            this.favourites = favourites;
        }

        public static class ProductsizesBean {


            /**
             * uid : 30729
             * product_id : 103
             * size_id : 107
             * status : 0
             * log_user : null
             * created_at : 2019-03-11T06:48:20+0000
             * updated_at : 2019-03-11T06:48:20+0000
             * size : {"size_id":107,"size_title":"xs","status":0,"log_user":"","created_at":"2019-02-19T08:42:13+0000","updated_at":"2019-02-19T08:42:13+0000"}
             */

            private int uid;
            private int product_id;
            private int size_id;
            private int status;
            private Object log_user;
            private String created_at;
            private String updated_at;
            private SizeBean size;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getSize_id() {
                return size_id;
            }

            public void setSize_id(int size_id) {
                this.size_id = size_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getLog_user() {
                return log_user;
            }

            public void setLog_user(Object log_user) {
                this.log_user = log_user;
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

            public SizeBean getSize() {
                return size;
            }

            public void setSize(SizeBean size) {
                this.size = size;
            }

            public static class SizeBean {
                /**
                 * size_id : 107
                 * size_title : xs
                 * status : 0
                 * log_user :
                 * created_at : 2019-02-19T08:42:13+0000
                 * updated_at : 2019-02-19T08:42:13+0000
                 */

                private int size_id;
                private String size_title;
                private int status;
                private String log_user;
                private String created_at;
                private String updated_at;

                public int getSize_id() {
                    return size_id;
                }

                public void setSize_id(int size_id) {
                    this.size_id = size_id;
                }

                public String getSize_title() {
                    return size_title;
                }

                public void setSize_title(String size_title) {
                    this.size_title = size_title;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getLog_user() {
                    return log_user;
                }

                public void setLog_user(String log_user) {
                    this.log_user = log_user;
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
            }
        }

        public static class TotalRatingBean {
            /**
             * id : 74
             * stars : 27
             * count : 7
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
             * id : 74
             * id : 44
             * photo : http://shopgate.codesroots.com/library/attachment/1555320503900203421.jpg
             */

            private int product_id;
            private int id;
            private String photo;

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class ColorBean {


            /**
             * uid : 1
             * product_id : 1
             * color_id : 1
             * status : 0
             * log_user : 0
             * created_at : 2019-05-07T13:25:05+0000
             * updated_at : 2019-05-07T13:25:05+0000
             * color : {"name":"احمر"}
             */

            private int uid;
            private int product_id;
            private int color_id;
            private int status;
            private String log_user;
            private String created_at;
            private String updated_at;
            private ColorData color;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getColor_id() {
                return color_id;
            }

            public void setColor_id(int color_id) {
                this.color_id = color_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getLog_user() {
                return log_user;
            }

            public void setLog_user(String log_user) {
                this.log_user = log_user;
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

            public ColorData getColor() {
                return color;
            }

            public void setColor(ColorData color) {
                this.color = color;
            }

            public static class ColorData {
                /**
                 * name : احمر
                 */
                private int color_id;
                private String name;

                public int getColor_id() {
                    return color_id;
                }

                public void setColor_id(int color_id) {
                    this.color_id = color_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }



        public static class FavouritesBean {
            /**
             * id : 31
             * id : 1
             * user_id : 2
             */

            private int product_id;
            private int id;
            private int user_id;

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }
        }


        public static class OffersBean {
            /**
             * id : 84
             * percentage : 18
             * id : 74
             */
            @SerializedName("uid")
            private int id;
            @SerializedName("discounted_price")
            private float percentage;
            private int product_id;
            private String to_discount;

            public String getTo_discount() {
                return to_discount;
            }

            public void setTo_discount(String to_discount) {
                this.to_discount = to_discount;
            }

            public int getId() {
                return id;
            }
            public void setId(int id) {
                this.id = id;
            }

            public float getPercentage() {
                return percentage;
            }
            public void setPercentage(float percentage) {
                this.percentage = percentage;
            }
            public int getProduct_id() {
                return product_id;
            }
            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }
        }
    }
}
