package com.ka.krishnaaqua.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ka.krishnaaqua.data.Order;
import com.ka.krishnaaqua.data.OrderData;
import com.ka.krishnaaqua.data.Users;

import java.util.List;

public class ServerResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("users")
    @Expose
    private Users users;

    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public Boolean getError () {
        return error;
    }

    public void setError ( Boolean error ) {
        this.error = error;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    public Users getUsers () {
        return users;
    }

    public void setUsers ( Users users ) {
        this.users = users;
    }
}