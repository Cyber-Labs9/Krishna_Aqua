package com.ka.krishnaaqua.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.databinding.ActivityOrderBinding;

public class Order extends AppCompatActivity {

    private final Context context = this;
    NavigationView nav;
    ActionBarDrawerToggle mToggle;
    DrawerLayout mDrawerLayout;
    private ActivityOrderBinding mBinding;
    private androidx.appcompat.widget.Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        nav = findViewById(R.id.navmenu);
        mDrawerLayout = findViewById(R.id.drawer);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mtoolbar, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        nav.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.home:
                    Toast.makeText(Order.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(Order.this, Home.class);
                    startActivity(home);
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.order:
                    Toast.makeText(Order.this, "Order", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.history:
                    Intent history = new Intent(Order.this, History.class);
                    startActivity(history);
                    finish();
                    Toast.makeText(Order.this, "History", Toast.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.logout:
                    Toast.makeText(Order.this, "Logout", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);

            }

            return true;
        });


    }
}