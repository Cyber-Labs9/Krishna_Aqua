package com.ka.krishnaaqua.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ka.krishnaaqua.data.Users;

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