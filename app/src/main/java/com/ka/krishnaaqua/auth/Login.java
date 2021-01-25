package com.ka.krishnaaqua.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.SessionManagement.SessionManagement;
import com.ka.krishnaaqua.dashboard.Home;
import com.ka.krishnaaqua.data.UserData;
import com.ka.krishnaaqua.data.Users;
import com.ka.krishnaaqua.databinding.ActivityLoginBinding;
import com.ka.krishnaaqua.network.Api;
import com.ka.krishnaaqua.network.AppConfig;
import com.ka.krishnaaqua.network.ServerResponse;
import com.ka.krishnaaqua.utils.Config;
import com.ka.krishnaaqua.utils.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private final Context context = this;
    public String id, name, Email, Password, address, mobile;
    private ActivityLoginBinding binding;

    private static final String TAG = "Login";

    private SessionManagement sessionManagement;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        binding = ActivityLoginBinding.inflate ( getLayoutInflater ( ) );
        View view = binding.getRoot ( );
        setContentView ( view );


        binding.loginbtn.setOnClickListener ( v -> {
            String Email = binding.email.getText ( ).toString ( );
            String Password = binding.password.getText ( ).toString ( );


            if (TextUtils.isEmpty ( Email ) || TextUtils.isEmpty ( Password )) {
                binding.email.setError ( "All fields are required !!" );
                binding.password.setError ( "All fields are required!!" );
                return;
            }
            Log.v ( "Login " , "It Works" );

            doLogin ( Email , Password );

        } );

        binding.registerbtn.setOnClickListener ( v -> {
            Intent obj = new Intent ( Login.this , Register.class );
            Login.this.startActivity ( obj );

        } );

    }

    @Override
    protected void onStart () {
        super.onStart ( );
        CheckSession ( );
    }

    /*---------------------------------------------CheckSession---------------------------------------------------*/
    private void CheckSession () {
        //   Check if user logged in
        //   If yes then move to dashboard
        sessionManagement = new SessionManagement ( context );
        sharedPrefManager = new SharedPrefManager ( context );


       /* int UsedId = sessionManagement.getSession ( );

        if (UsedId != -1) {
            MoveToActivity ( id , name , Email , Password , address , mobile );
        } else {
            //   Do Nothing
        }*/


        int UsedId = sharedPrefManager.getInt ( "id" );

        if (UsedId != -1) {

            name    = sharedPrefManager.getString ( "name" );
            Email   = sharedPrefManager.getString ( "email" );
            address = sharedPrefManager.getString ( "address" );
            mobile  = sharedPrefManager.getString ( "mobile" );

            MoveToActivity ( String.valueOf ( UsedId ) , name , Email , address , mobile );
        } else {

            Config.showToast ( context , "User ID is Null" );
        }

    }

    private void doLogin ( String email , String password ) {
        Retrofit retrofit = AppConfig.getRetrofit ( );
        Api service = retrofit.create ( Api.class );

        Call<ServerResponse> call = service.login ( email , password );
        call.enqueue ( new Callback<ServerResponse> ( ) {
            @Override
            public void onResponse ( Call<ServerResponse> call , Response<ServerResponse> response ) {

                if (response.body ( ) != null) {

                    ServerResponse serverResponse = response.body ( );

                    Config.showToast ( context , serverResponse.getMessage ( ) );
                    if (!serverResponse.getError ( )) {
                        //                        SessionGeneration

                        fetchLoginData ( email , password );
                    }
                }
            }

            @Override
            public void onFailure ( Call<ServerResponse> call , Throwable t ) {
                Config.showToast ( context , t.getMessage ( ) );
            }
        } );

    }

    private void fetchLoginData ( String email , String password ) {

        Retrofit retrofit = AppConfig.getRetrofit ( );
        Api service = retrofit.create ( Api.class );

        Call<ServerResponse> datacall = service.login_data ( email , password );
        datacall.enqueue ( new Callback<ServerResponse> ( ) {
            @Override
            public void onResponse ( Call<ServerResponse> call , Response<ServerResponse> response ) {

                if (response.body ( ) != null) {
                    ServerResponse serverResponse = response.body ( );
                    Users loginData = serverResponse.getUsers ( );
                    id      = loginData.getId ( );
                    name    = loginData.getUserName ( );
                    Email   = loginData.getEmail ( );
                    address = loginData.getAddress ( );
                    mobile  = loginData.getMobile ( );
                    Log.e ( "" , id );


                    sharedPrefManager.setInt ( "id" , Integer.parseInt ( id ) );
                    sharedPrefManager.setString ( "name" , name );
                    sharedPrefManager.setString ( "email" , email );
                    sharedPrefManager.setString ( "address" , address );
                    sharedPrefManager.setString ( "mobile" , mobile );

                    CheckSession ( );

                }
            }

            @Override
            public void onFailure ( Call<ServerResponse> call , Throwable t ) {
                Config.showToast ( context , "Data Not Found" );
            }
        } );

    }

    private void MoveToActivity ( String id , String name , String email , String address , String mobile ) {
        UserData userData = new UserData ( id , name , email , address , mobile );

        Log.d ( TAG , "MoveToActivity: " );
        Intent obj = new Intent ( Login.this , Home.class );
        obj.putExtra ( "userData" , userData );

        obj.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity ( obj );
    }
}