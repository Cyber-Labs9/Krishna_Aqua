package com.ka.krishnaaqua.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.SessionManagement.SessionManagement;
import com.ka.krishnaaqua.auth.Login;
import com.ka.krishnaaqua.databinding.ActivityHistoryBinding;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private static final String LOG_TAG = History.class.getName ( );
    private final Context context = this;
    NavigationView nav;
    ActionBarDrawerToggle mToggle;
    DrawerLayout mDrawerLayout;
    private ActivityHistoryBinding mBinding;
    private androidx.appcompat.widget.Toolbar mtoolbar;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_history );
        mBinding = ActivityHistoryBinding.inflate ( getLayoutInflater ( ) );
        View view = mBinding.getRoot ( );
        setContentView ( view );

        mtoolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( mtoolbar );
        nav           = findViewById ( R.id.navmenu );
        mDrawerLayout = findViewById ( R.id.drawer );

        mToggle = new ActionBarDrawerToggle ( this , mDrawerLayout , mtoolbar , R.string.Open , R.string.Close );
        mDrawerLayout.addDrawerListener ( mToggle );
        mToggle.syncState ( );

        nav.setNavigationItemSelectedListener ( item -> {

            switch (item.getItemId ( )) {

                case R.id.home:
                    Toast.makeText ( History.this , "Home" , Toast.LENGTH_SHORT ).show ( );
                    startActivity ( new Intent ( History.this , Home.class ) );
                    mDrawerLayout.closeDrawer ( GravityCompat.START );
                    break;

                case R.id.history:
                    Toast.makeText ( History.this , "History" , Toast.LENGTH_LONG ).show ( );
                    mDrawerLayout.closeDrawer ( GravityCompat.START );
                    break;

                case R.id.logout:
                    Toast.makeText ( History.this , "Logout" , Toast.LENGTH_SHORT ).show ( );
                    LogOut ( );
                    mDrawerLayout.closeDrawer ( GravityCompat.START );

            }

            return true;
        } );
// RecycleView List
        ArrayList<String> stuff = new ArrayList<> ( );
        stuff.add ( "Data Structures" );
        stuff.add ( "Algorithms" );
        stuff.add ( "Competitive Programming" );
        stuff.add ( "Interview Questions" );
        stuff.add ( "Python" );
        stuff.add ( "Java" );
        stuff.add ( "Designing" );
        stuff.add ( "Coding" );
        stuff.add ( "Developing" );
        stuff.add ( "Project Ideas" );
        stuff.add ( "C++" );
        stuff.add ( "Basically Everything!" );
        stuff.add ( "Data Structures" );
        stuff.add ( "Algorithms" );
        stuff.add ( "Competitive Programming" );
        stuff.add ( "Interview Questions" );
        stuff.add ( "Python" );
        stuff.add ( "Java" );
        stuff.add ( "Designing" );
        stuff.add ( "Coding" );
        stuff.add ( "Developing" );
        stuff.add ( "Project Ideas" );
        stuff.add ( "C++" );
        stuff.add ( "Basically Everything!" );
        stuff.add ( "Data Structures" );
        stuff.add ( "Algorithms" );
        stuff.add ( "Competitive Programming" );
        stuff.add ( "Interview Questions" );
        stuff.add ( "Python" );
        stuff.add ( "Java" );
        stuff.add ( "Designing" );
        stuff.add ( "Coding" );
        stuff.add ( "Developing" );
        stuff.add ( "Project Ideas" );
        stuff.add ( "C++" );
        stuff.add ( "Basically Everything!" );

        ListView listView = findViewById ( R.id.list );

        ArrayAdapter<String> adapter = new ArrayAdapter<String> ( this , android.R.layout.simple_list_item_1 , stuff );

        listView.setAdapter ( adapter );


    }

    private void LogOut () {
//        This Method will remove session
        SessionManagement sessionManagement = new SessionManagement ( History.this );
        sessionManagement.removeSession ( );
        MoveToLogin ( );
    }

    private void MoveToLogin () {
        Intent log = new Intent ( History.this , Login.class );
        startActivity ( log );
    }
}