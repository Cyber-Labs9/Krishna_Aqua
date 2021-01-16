package com.ka.krishnaaqua.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.databinding.ActivityRegisterBinding;
import com.ka.krishnaaqua.network.Api;
import com.ka.krishnaaqua.network.AppConfig;
import com.ka.krishnaaqua.network.ServerResponse;
import com.ka.krishnaaqua.utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = binding.name.getText().toString();
                String Email = binding.email.getText().toString();
                String Password = binding.password.getText().toString();
                String Phone = binding.phone.getText().toString();
                String Address = binding.address.getText().toString();


                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(Name) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Address)) {
                    binding.password.setError("All fields are required!!");
                    return;
                }


                doRegister(Email, Password, Name, Phone, Address);

            }
        });


    }

    private void doRegister(String email, String password, String name, String phone, String address) {

        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.register ( name , email , password , phone , address );
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                if (response.body() != null) {
                    ServerResponse serverResponse = response.body();
                    if (!serverResponse.getError()) {
                        Config.showToast(context, serverResponse.getMessage());
                        Intent home = new Intent(Register.this,Login.class);
                        startActivity(home);
                        finish();
                    } else {
                        Config.showToast(context, serverResponse.getMessage());

                    }

                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Config.showToast(context, t.getMessage());
            }
        });


    }
}