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
import com.ka.krishnaaqua.SessionManagement.User;
import com.ka.krishnaaqua.dashboard.Home;
import com.ka.krishnaaqua.databinding.ActivityLoginBinding;
import com.ka.krishnaaqua.network.Api;
import com.ka.krishnaaqua.network.AppConfig;
import com.ka.krishnaaqua.network.ServerResponse;
import com.ka.krishnaaqua.utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.loginbtn.setOnClickListener(v -> {
            String Email = binding.email.getText().toString();
            String Password = binding.password.getText().toString();


            if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
                binding.email.setError("All fields are required !!");
                binding.password.setError("All fields are required!!");
                return;
            }
            Log.v("Login ","It Works");

            doLogin(Email,Password);

        });

        binding.registerbtn.setOnClickListener ( v -> {
            Intent obj = new Intent ( Login.this , Register.class );
            Login.this.startActivity ( obj );

        } );

    }

    @Override
    protected void onStart() {
        super.onStart ( );
        CheckSession ( );
    }

    private void CheckSession() {
        //   Check if user logged in
        //   If yes then move to dashboard
        SessionManagement sessionManagement = new SessionManagement ( Login.this );
        int UsedId = sessionManagement.getSession ( );

        if (UsedId != -1) {
            MoveToActivity ( );
        } else {
            //   Do Nothing
        }
    }

    private void doLogin(String email , String password) {
        Retrofit retrofit = AppConfig.getRetrofit ( );
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.login(email, password);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                if(response.body()!=null){

                    ServerResponse serverResponse = response.body();

                    if(!serverResponse.getError()){
                        Config.showToast ( context , serverResponse.getMessage ( ) );
//                        Login Data
                        Call<ServerResponse> datacall = service.login_data ( email , password );
                        datacall.enqueue ( new Callback<ServerResponse> ( ) {
                            @Override
                            public void onResponse(Call<ServerResponse> call , Response<ServerResponse> response) {

                                if (response.body ( ) != null) {
                                    ServerResponse serverResponse = response.body ( );
                                    Log.e ( "Data" , String.valueOf ( serverResponse ) );
                                }
                            }

                            @Override
                            public void onFailure(Call<ServerResponse> call , Throwable t) {

                            }
                        } );
//                        SessionGeneration
                        User user = new User ( 1 , email );
                        SessionManagement sessionManagement = new SessionManagement ( Login.this );
                        sessionManagement.saveSession ( user );
                        MoveToActivity ( );

                    }
                    else {
                        Config.showToast(context,serverResponse.getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Config.showToast(context, t.getMessage());
            }
        });

    }

    private void MoveToActivity() {
        Intent obj = new Intent ( Login.this , Home.class );
        obj.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity ( obj );
    }
}