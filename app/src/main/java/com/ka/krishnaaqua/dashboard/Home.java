package com.ka.krishnaaqua.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.navigation.NavigationView;
import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.SessionManagement.SessionManagement;
import com.ka.krishnaaqua.auth.Login;
import com.ka.krishnaaqua.data.OrderData;
import com.ka.krishnaaqua.databinding.ActivityHomeBinding;
import com.ka.krishnaaqua.utils.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Home extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    /*--------------------------------Variable Declaration-------------------------------------------------------------------------------------*/
    private static final String TAG = "Home";
    private final Context context = this;
    public String startDate = "";
    public String endDate = "";
    String[] Jug = {"20 Litre       ₹20" , "35 Litre      ₹30" , "50 Litre      ₹40"};
    NavigationView nav;
    ActionBarDrawerToggle mToggle;
    DrawerLayout mDrawerLayout;
    private ActivityHomeBinding mBinding;
    private androidx.appcompat.widget.Toolbar mtoolbar;
    private int qty = 0;
    private int price = 0;
    private String StartDate;
    private String EndDate;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );
        mBinding = ActivityHomeBinding.inflate ( getLayoutInflater ( ) );
        View view = mBinding.getRoot ( );
        setContentView ( view );

        mtoolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( mtoolbar );

        nav           = findViewById ( R.id.navmenu );
        mDrawerLayout = findViewById ( R.id.drawer );
        mBinding.spinner.setOnItemSelectedListener ( this );
        ArrayAdapter mArr = new ArrayAdapter ( this , android.R.layout.simple_spinner_dropdown_item , Jug );
        mArr.setDropDownViewResource ( android.R.layout.simple_spinner_dropdown_item );
        mBinding.spinner.setAdapter ( mArr );

        Button StartFrom = mBinding.StartFrom;
        Button EndAt = mBinding.EndAt;
        /*-----------------------------------------------Navigation Drawer-------------------------------------------------------------------------------------------*/
        mToggle = new ActionBarDrawerToggle ( this , mDrawerLayout , mtoolbar , R.string.Open , R.string.Close );
        mDrawerLayout.addDrawerListener ( mToggle );
        mToggle.syncState ( );

        nav.setNavigationItemSelectedListener ( item -> {

            switch (item.getItemId ( )) {

                case R.id.home:
                    Toast.makeText ( Home.this , "Home" , Toast.LENGTH_SHORT ).show ( );
                    mDrawerLayout.closeDrawer ( GravityCompat.START );
                    break;

                case R.id.history:
//                    Toast.makeText ( Home.this , "History" , Toast.LENGTH_LONG ).show ( );
                    startActivity ( new Intent ( Home.this , History.class ) );
                    mDrawerLayout.closeDrawer ( GravityCompat.START );
                    break;

                case R.id.logout:
                    Toast.makeText ( Home.this , "Logout" , Toast.LENGTH_SHORT ).show ( );
                    LogOut ( );
                    mDrawerLayout.closeDrawer ( GravityCompat.START );

            }

            return true;
        } );

        /*------------------------------------------------------Date Picker----------------------------------------------------------------------------------*/
        StartFrom.setOnClickListener ( v -> {
            MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker ( );
            materialDateBuilder.setTitleText ( "Start Date" );
            final MaterialDatePicker materialDatePicker = materialDateBuilder.build ( );
            materialDatePicker.show ( getSupportFragmentManager ( ) , "MATERIAL_DATE_PICKER" );
            materialDatePicker.addOnPositiveButtonClickListener (
                    selection -> {
                        SimpleDateFormat spf = new SimpleDateFormat ( "MMM dd, yyyy" );
                        Date newDate = null;
                        try {
                            newDate = spf.parse ( materialDatePicker.getHeaderText ( ) );
                        } catch ( ParseException e ) {
                            e.printStackTrace ( );
                        }
                        spf       = new SimpleDateFormat ( "dd/MM/yyyy" );
                        StartDate = spf.format ( newDate );
                        mBinding.StartFrom.setText ( StartDate );
                    } );
        } );

        EndAt.setOnClickListener ( v -> {
            MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker ( );
            materialDateBuilder.setTitleText ( "End Date" );
            final MaterialDatePicker materialDatePicker = materialDateBuilder.build ( );
            materialDatePicker.show ( getSupportFragmentManager ( ) , "MATERIAL_DATE_PICKER" );
            materialDatePicker.addOnPositiveButtonClickListener (
                    selection -> {
                        SimpleDateFormat spf = new SimpleDateFormat ( "MMM dd, yyyy" );
                        Date newDate = null;
                        try {
                            newDate = spf.parse ( materialDatePicker.getHeaderText ( ) );
                        } catch ( ParseException e ) {
                            e.printStackTrace ( );
                        }
                        spf     = new SimpleDateFormat ( "dd/MM/yyyy" );
                        EndDate = spf.format ( newDate );
                        mBinding.EndAt.setText ( EndDate );
                    } );
        } );

        mBinding.orderbtn.setOnClickListener ( v -> {
            sendData ( );
        } );


    }

    /*------------------------------------------------------Method Call-------------------------------------------------------------------------------------*/
    private void LogOut () {
//        This Method will remove session
        SessionManagement sessionManagement = new SessionManagement ( Home.this );
        sessionManagement.removeSession ( );
        MoveToLogin ( );
    }

    private void MoveToLogin () {
        Intent log = new Intent ( Home.this , Login.class );
        startActivity ( log );
    }

    @Override
    public void onItemSelected ( AdapterView<?> parent , View view , int position , long id ) {
        Toast.makeText ( getApplicationContext ( ) , Jug[position] , Toast.LENGTH_LONG ).show ( );
        jugValue ( Jug[position] );
    }

    private void jugValue ( String s ) {
//        Variables
        String Jug = s;
//        Data
        String TwentyLitre = "20 Litre       ₹20";
        String ThirtyFiveLitre = "35 Litre      ₹30";
        String FiftyLitre = "50 Litre      ₹40";
//        Log.v("Jug", s);
//        Blank Variables


        if (Jug.equals ( TwentyLitre )) {
//            Log.v("Jug", "Twenty Litres");
            qty   = 20;
            price = 20;

        } else if (Jug.equals ( ThirtyFiveLitre )) {
//            Log.v("Jug", "ThirtyFiveLitre Litres");
            qty   = 35;
            price = 30;

        } else if (Jug.equals ( FiftyLitre )) {
//            Log.v("Jug", "FiftyLitre Litres");
            qty   = 50;
            price = 40;

        } else {

        }


    }

    private void sendData () {
        Log.v ( TAG , String.valueOf ( price ) );
        Log.v ( TAG , String.valueOf ( qty ) );
        Log.v ( TAG , StartDate );
        Log.v ( TAG , EndDate );

        OrderData orderData = new OrderData ( StartDate , EndDate , qty , price );

        Intent intent = new Intent ( Home.this , ReviewOrder.class );
        intent.putExtra ( "order_data" , orderData );
        startActivity ( intent );


    }

    @Override
    public void onNothingSelected ( AdapterView<?> arg0 ) {
        Config.showToast ( context , "Select Date" );
    }

}