package com.codesroots.osamaomar.grz.models.entities;

import java.util.List;

public class ProductRate {


    /**
     * data : [{"rating_id":3,"product_id":2,"customer_id":1,"rated":4,"feedback":"","status":0,"log_user":null,"created_at":"2018-09-18T20:14:36+0000","updated_at":"2018-09-18T20:14:36+0000","user":{"customer_id":1,"customer_name":"iftikhar Ali","customer_email":"iftikhar.romtech@gmail.com","customer_contact":"94113503","customer_address":"SOME ADDRESS","status":0,"log_user":null,"created_at":"2018-09-09T21:57:16+0000","updated_at":"2018-12-18T10:28:12+0000"}},{"rating_id":5,"product_id":2,"customer_id":2,"rated":4,"feedback":"good","status":0,"log_user":null,"created_at":"2018-10-10T05:26:01+0000","updated_at":"2018-10-10T05:26:01+0000","user":{"customer_id":2,"customer_name":"Tt","customer_email":"admin@mazoon-whats.com ","customer_contact":"9999","customer_address":"aaa","status":0,"log_user":null,"created_at":"2018-09-10T14:47:29+0000","updated_at":"2019-04-15T05:21:49+0000"}}]
     * rate5 : 0
     * rate4 : 2
     * rate3 : 0
     * rate2 : 0
     * rate1 : 0
     */

    private int rate5;
    private int rate4;
    private int rate3;
    private int rate2;
    private int rate1;
    private List<DataBean> data;

    public int getRate5() {
        return rate5;
    }

    public void setRate5(int rate5) {
        this.rate5 = rate5;
    }

    public int getRate4() {
        return rate4;
    }

    public void setRate4(int rate4) {
        this.rate4 = rate4;
    }

    public int getRate3() {
        return rate3;
    }

    public void setRate3(int rate3) {
        this.rate3 = rate3;
    }

    public int getRate2() {
        return rate2;
    }

    public void setRate2(int rate2) {
        this.rate2 = rate2;
    }

    public int getRate1() {
        return rate1;
    }

    public void setRate1(int rate1) {
        this.rate1 = rate1;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rating_id : 3
         * product_id : 2
         * customer_id : 1
         * rated : 4
         * feedback :
         * status : 0
         * log_user : null
         * created_at : 2018-09-18T20:14:36+0000
         * updated_at : 2018-09-18T20:14:36+0000
         * user : {"customer_id":1,"customer_name":"iftikhar Ali","customer_email":"iftikhar.romtech@gmail.com","customer_contact":"94113503","customer_address":"SOME ADDRESS","status":0,"log_user":null,"created_at":"2018-09-09T21:57:16+0000","updated_at":"2018-12-18T10:28:12+0000"}
         */

        private int rating_id;
        private int product_id;
        private int customer_id;
        private int rated;
        private String feedback;
        private int status;
        private Object log_user;
        private String created_at;
        private String updated_at;
        private UserBean user;

        public int getRating_id() {
            return rating_id;
        }

        public void setRating_id(int rating_id) {
            this.rating_id = rating_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public int getRated() {
            return rated;
        }

        public void setRated(int rated) {
            this.rated = rated;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * customer_id : 1
             * customer_name : iftikhar Ali
             * customer_email : iftikhar.romtech@gmail.com
             * customer_contact : 94113503
             * customer_address : SOME ADDRESS
             * status : 0
             * log_user : null
             * created_at : 2018-09-09T21:57:16+0000
             * updated_at : 2018-12-18T10:28:12+0000
             */

            private int customer_id;
            private String customer_name;
            private String customer_email;
            private String customer_contact;
            private String customer_address;
            private int status;
            private Object log_user;
            private String created_at;
            private String updated_at;

            public int getCustomer_id() {
                return customer_id;
            }

            public void setCustomer_id(int customer_id) {
                this.customer_id = customer_id;
            }

            public String getCustomer_name() {
                return customer_name;
            }

            public void setCustomer_name(String customer_name) {
                this.customer_name = customer_name;
            }

            public String getCustomer_email() {
                return customer_email;
            }

            public void setCustomer_email(String customer_email) {
                this.customer_email = customer_email;
            }

            public String getCustomer_contact() {
                return customer_contact;
            }

            public void setCustomer_contact(String customer_contact) {
                this.customer_contact = customer_contact;
            }

            public String getCustomer_address() {
                return customer_address;
            }

            public void setCustomer_address(String customer_address) {
                this.customer_address = customer_address;
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
        }
    }
}
