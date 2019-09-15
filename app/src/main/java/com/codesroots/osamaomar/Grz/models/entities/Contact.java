package com.codesroots.osamaomar.grz.models.entities;

import java.util.List;

public class Contact {

    /**
     * success : true
     * data : [{"uid":1,"contact_email":"GRZexpress@gmail.com","contact_phone":"GRZexpress@gmail.com","contact_address":"GRZexpress@gmail.com","status":0,"log_user":null,"created_at":"2018-09-18T09:20:28+0000","updated_at":"2018-09-18T09:20:28+0000"}]
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 1
         * contact_email : GRZexpress@gmail.com
         * contact_phone : GRZexpress@gmail.com
         * contact_address : GRZexpress@gmail.com
         * status : 0
         * log_user : null
         * created_at : 2018-09-18T09:20:28+0000
         * updated_at : 2018-09-18T09:20:28+0000
         */

        private int uid;
        private String contact_email;
        private String contact_phone;
        private String contact_address;
        private int status;
        private Object log_user;
        private String created_at;
        private String updated_at;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getContact_email() {
            return contact_email;
        }

        public void setContact_email(String contact_email) {
            this.contact_email = contact_email;
        }

        public String getContact_phone() {
            return contact_phone;
        }

        public void setContact_phone(String contact_phone) {
            this.contact_phone = contact_phone;
        }

        public String getContact_address() {
            return contact_address;
        }

        public void setContact_address(String contact_address) {
            this.contact_address = contact_address;
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
