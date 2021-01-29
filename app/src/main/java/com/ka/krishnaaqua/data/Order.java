package com.ka.krishnaaqua.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("u_id")
    @Expose
    private String uId;

    @SerializedName("endDate")
    @Expose
    private String endDate;

    @SerializedName("startDate")
    @Expose
    private String startDate;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("paymentId")
    @Expose
    private String paymentId;


    private boolean isExpanded = false;

    public boolean isExpanded () {
        return isExpanded;
    }

    public void setExpanded ( boolean expanded ) {
        isExpanded = expanded;
    }

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getUId () {
        return uId;
    }

    public void setUId ( String uId ) {
        this.uId = uId;
    }

    public String getEndDate () {
        return endDate;
    }

    public String getStartDate () {
        return startDate;
    }

    public void setStartDate ( String startDate ) {
        this.startDate = startDate;
    }

    public void setEndDate ( String endDate ) {
        this.endDate = endDate;
    }

    public String getTotal () {
        return total;
    }

    public void setTotal ( String total ) {
        this.total = total;
    }

    public String getQty () {
        return qty;
    }

    public void setQty ( String qty ) {
        this.qty = qty;
    }

    public String getPaymentId () {
        return paymentId;
    }

    public void setPaymentId ( String paymentId ) {
        this.paymentId = paymentId;
    }

}