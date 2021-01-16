package com.ka.krishnaaqua;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.ka.krishnaaqua.auth.Login;

public class MainActivity extends AppCompatActivity {

    public static int Splash_Screen_Timeout = 4000;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ( ).setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_main );

        new Handler ( ).postDelayed ( () -> {
            Intent log = new Intent ( MainActivity.this , Login.class );
            startActivity ( log );
            finish ( );
        } , Splash_Screen_Timeout );


    }
}