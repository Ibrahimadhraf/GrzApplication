package com.codesroots.osamaomar.grz.models.entities;

import java.util.List;

public class Currency {


    /**
     * success : true
     * data : [{"cr_id":1,"omr":1,"usd":2.6,"aed":9.54,"sar":9.74,"status":0,"log_user":null,"created_at":"2018-10-18T04:43:32+0000","updated_at":"2019-01-06T13:26:36+0000"}]
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
         * cr_id : 1
         * omr : 1
         * usd : 2.6
         * aed : 9.54
         * sar : 9.74
         * status : 0
         * log_user : null
         * created_at : 2018-10-18T04:43:32+0000
         * updated_at : 2019-01-06T13:26:36+0000
         */

        private int cr_id;
        private int omr;
        private double usd;
        private double aed;
        private double sar;
        private int status;
        private Object log_user;
        private String created_at;
        private String updated_at;

        public int getCr_id() {
            return cr_id;
        }

        public void setCr_id(int cr_id) {
            this.cr_id = cr_id;
        }

        public int getOmr() {
            return omr;
        }

        public void setOmr(int omr) {
            this.omr = omr;
        }

        public double getUsd() {
            return usd;
        }

        public void setUsd(double usd) {
            this.usd = usd;
        }

        public double getAed() {
            return aed;
        }

        public void setAed(double aed) {
            this.aed = aed;
        }

        public double getSar() {
            return sar;
        }

        public void setSar(double sar) {
            this.sar = sar;
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
