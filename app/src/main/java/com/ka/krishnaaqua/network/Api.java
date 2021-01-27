package com.ka.krishnaaqua.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    String my_url = "Api.php?apicall=";

    @FormUrlEncoded
    @POST(my_url + "login")
    Call<ServerResponse> login(
            @Field("email") String email ,
            @Field("pwd") String password
    );

    @FormUrlEncoded
    @POST(my_url + "login_data")
    Call<ServerResponse> login_data(
            @Field("email") String email ,
            @Field("pwd") String password
    );


    @FormUrlEncoded
    @POST(my_url + "register")
    Call<ServerResponse> register (
            @Field("name") String mName ,
            @Field("email") String mEmail ,
            @Field("pwd") String mPwd ,
            @Field("mobile") String mphone ,
            @Field("address") String maddress
    );


    @FormUrlEncoded
    @POST(my_url + "insertOrder")
    Call<ServerResponse> insertOrder (
            @Field("id") int id ,
            @Field("startDate") String startDate ,
            @Field("endDate") String endDate ,
            @Field("total") int total ,
            @Field("qty") int qty ,
            @Field("paymentId") String razorpayPaymentID
    );

    @FormUrlEncoded
    @POST(my_url + "fetchOrder")
    Call<ServerResponse> fetchOrder (
            @Field("u_id") int id
    );

}

