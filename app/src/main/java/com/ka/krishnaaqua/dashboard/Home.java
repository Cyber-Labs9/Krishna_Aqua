package com.ka.krishnaaqua.dashboard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.databinding.ActivityHomeBinding;

import java.util.Calendar;

public class Home extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {


    private final Context context = this;
    String[] Jug = {"20 Litre       ₹20", "35 Litre      ₹30", "50 Litre      ₹40"};
    NavigationView nav;
    ActionBarDrawerToggle mToggle;
    DrawerLayout mDrawerLayout;
    private ActivityHomeBinding mBinding;
    private androidx.appcompat.widget.Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        nav = findViewById(R.id.navmenu);
        mDrawerLayout = findViewById(R.id.drawer);

        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter mArr = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Jug);
        mArr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(mArr);

        Button StartFrom = findViewById(R.id.StartFrom);
        Button EndAt = findViewById(R.id.EndAt);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mtoolbar, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        nav.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.home:
                    Toast.makeText(Home.this, "Home", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.order:
                    Toast.makeText(Home.this, "Order", Toast.LENGTH_SHORT).show();
                    Intent order = new Intent(Home.this, Order.class);
                    view.getContext().startActivity(order);
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.history:
                    Toast.makeText(Home.this, "History", Toast.LENGTH_LONG).show();
                    Intent history = new Intent(Home.this, History.class);
                    view.getContext().startActivity(history);
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.logout:
                    Toast.makeText(Home.this, "Logout", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);

            }

            return true;
        });

        StartFrom.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(Home.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view1, int year, int month, int dayOfMonth) {

                    StartFrom.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                }
            }, 0, 0, 0);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        EndAt.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(Home.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view12, int year, int month, int dayOfMonth) {

                    EndAt.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                }
            }, 0, 0, 0);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), Jug[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

}