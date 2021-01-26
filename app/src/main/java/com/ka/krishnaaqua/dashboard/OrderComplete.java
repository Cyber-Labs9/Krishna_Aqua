package com.ka.krishnaaqua.dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.data.OrderCompleted;

public class OrderComplete extends AppCompatActivity {

    private OrderCompleted orderCompleted;
    private int id, total;
    private String startDate, endDate;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_order_complete );

        Intent intent = getIntent ( );
        orderCompleted = intent.getExtras ( ).getParcelable ( "Order Completed" );


    }
}