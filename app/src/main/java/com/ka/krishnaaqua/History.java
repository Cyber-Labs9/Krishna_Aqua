package com.ka.krishnaaqua;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.ka.krishnaaqua.databinding.ActivityHistoryBinding;

public class History extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle mToggle;
    DrawerLayout mDrawerLayout;
    private ActivityHistoryBinding mBinding;
    private androidx.appcompat.widget.Toolbar mtoolbar;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        nav = (NavigationView)findViewById(R.id.navmenu);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mtoolbar, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        nav.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home :
                    Toast.makeText(History.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent home =  new Intent(History.this,Home.class);
                    startActivity(home);
                    finish();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.order :
                    Toast.makeText(History.this, "Order", Toast.LENGTH_SHORT).show();
                    Intent order = new Intent(History.this,Order.class);
                    startActivity(order);
                    finish();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.history:
                    Toast.makeText(History.this,"History",Toast.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.logout:
                    Toast.makeText(History.this, "Logout", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);

            }

            return true;
        });



    }
}