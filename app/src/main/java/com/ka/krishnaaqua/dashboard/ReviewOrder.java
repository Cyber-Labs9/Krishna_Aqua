package com.ka.krishnaaqua.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.SessionManagement.SessionManagement;
import com.ka.krishnaaqua.data.OrderData;
import com.ka.krishnaaqua.databinding.ActivityReviewOrderBinding;
import com.ka.krishnaaqua.network.Api;
import com.ka.krishnaaqua.network.AppConfig;
import com.ka.krishnaaqua.network.ServerResponse;
import com.ka.krishnaaqua.utils.Config;
import com.ka.krishnaaqua.utils.SharedPrefManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReviewOrder extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = ReviewOrder.class.getSimpleName ( );

    //    Variable Declaration
    private ActivityReviewOrderBinding binding;
    private OrderData orderData;
    private int Total;
    private int Qty;
    private int Days;
    private final Context context = this;
    private String StartDate, EndDate;
    /*-------------------Shared Prefrence Variable-------------------*/
    private SessionManagement sessionManagement;
    private SharedPrefManager sharedPrefManager;
    private String name, Email, mobile, address;
    private int id;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_review_order );
        binding = ActivityReviewOrderBinding.inflate ( getLayoutInflater ( ) );
        View view = binding.getRoot ( );
        setContentView ( view );

        Intent intent = getIntent ( );
        orderData = intent.getExtras ( ).getParcelable ( "order_data" );

        Qty = orderData.getQuantity ( );
        int Price = Integer.parseInt ( String.valueOf ( orderData.getPrice ( ) ) );

        StartDate = orderData.getStartDate ( );
        EndDate   = orderData.getEndDate ( );

        Calendar start = getCalendarInstance ( StartDate );
        Calendar end = getCalendarInstance ( EndDate );

        String diff = getDaysCount ( end , start );
        Days = Integer.parseInt ( diff );

        /*------------------Sharedprefrence Manager Constructor-----------------------------------*/
        sharedPrefManager = new SharedPrefManager ( context );
        sessionManagement = new SessionManagement ( context );

        /*--------------------------------Sharedprefrence Variable Assignment--------------------------*/
        id     = sharedPrefManager.getInt ( "id" );
        name   = sharedPrefManager.getString ( "name" );
        Email  = sharedPrefManager.getString ( "Email" );
        mobile = sharedPrefManager.getString ( "mobile" );
        /*------------------------------Data View For Users---------------------------------------*/
        Total = 100 * (Price * Days);
        Log.v ( "" , String.valueOf ( Total ) );
        binding.QuantityValue.setText ( Qty + " " + "Litres" );
        binding.PriceValue.setText ( "₹" + Price + " " + "per Day" );
        binding.StartValue.setText ( StartDate );
        binding.EndDateValue.setText ( EndDate );
        if (Days <= 365) {
            binding.NoOfDaysValue.setText ( diff );
            binding.TotalValue.setText ( "₹" + Price * Days );
            binding.bookbtn.setOnClickListener ( v -> {
                startPayment ( );
            } );

        } else {
            binding.NoOfDaysValue.setText ( "Select Less Number Of Days" );
            binding.TotalValue.setText ( "₹ 0 " );
            binding.bookbtn.setEnabled ( false );
        }


    }

    /*--------------------------------------------Start Payment------------------------------------------------*/
    private void startPayment() {


        final Activity activity = this;

        final Checkout co = new Checkout ( );
        co.setKeyID ( "rzp_test_po1WCvaXKmHZmm" );

        try {
            JSONObject options = new JSONObject ( );
            options.put ( "name" , "Krishna Aqua" );
            options.put ( "description" , "A Water Company" );
            //You can omit the image option to fetch the image from dashboard
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put ( "currency" , "INR" );
            options.put ( "amount" , "" + Total );

            JSONObject preFill = new JSONObject ( );
            preFill.put ( "email" , "" + Email );
            preFill.put ( "contact" , "" + mobile );


            co.open ( activity , options );
        } catch ( Exception e ) {
            Toast.makeText ( activity , "Error in payment: " + e.getMessage ( ) , Toast.LENGTH_SHORT )
                    .show ( );
            e.printStackTrace ( );
        }


    }

    @Override
    public void onPaymentSuccess ( String razorpayPaymentID ) {
        try {
            Toast.makeText ( this , "Payment Successful: " + razorpayPaymentID , Toast.LENGTH_SHORT ).show ( );
            orderComplete ( id , StartDate , EndDate , Total , Qty , razorpayPaymentID );

        } catch ( Exception e ) {
            Log.e ( TAG , "Exception in onPaymentSuccess" , e );
        }

    }

    private void orderComplete ( int id , String startDate , String endDate , int total , int qty , String razorpayPaymentID ) {
        Retrofit retrofit = AppConfig.getRetrofit ( );
        Api service = retrofit.create ( Api.class );

        Call<ServerResponse> call = service.order ( id , startDate , endDate , total , qty , razorpayPaymentID );
        call.enqueue ( new Callback<ServerResponse> ( ) {
            @Override
            public void onResponse ( Call<ServerResponse> call , Response<ServerResponse> response ) {
                if (response.body ( ) != null) {
                    ServerResponse serverResponse = response.body ( );
                    if (!serverResponse.getError ( )) {
                        Config.showToast ( context , serverResponse.getMessage ( ) );
                        startActivity ( new Intent ( ReviewOrder.this , Home.class ) );
                        finish ( );
                    } else {
                        Config.showToast ( context , serverResponse.getMessage ( ) );

                    }
                }
            }

            @Override
            public void onFailure ( Call<ServerResponse> call , Throwable t ) {

            }
        } );
    }


    @Override
    public void onPaymentError ( int i , String s ) {
        try {
            Toast.makeText ( this , "Payment failed: " + i + " " + s , Toast.LENGTH_SHORT ).show ( );
            startActivity ( new Intent ( ReviewOrder.this , Home.class ) );
        } catch ( Exception e ) {
            Log.e ( TAG , "Exception in onPaymentError" , e );
        }
    }
    /*------------------------------ Convert String in Calendar ----------------------------------*/

    private Calendar getCalendarInstance(String myDate) {
        Calendar cal = Calendar.getInstance ( );
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy" , Locale.getDefault ( ) );

        try {
            cal.setTime ( sdf.parse ( myDate ) );// all done
        } catch ( ParseException e ) {
            e.printStackTrace ( );
        }

        return cal;
    }
    /*------------------------------ Count Days Between Dates ------------------------------------*/

    private String getDaysCount(Calendar start , Calendar end) {

        long msDiff = start.getTimeInMillis ( ) - end.getTimeInMillis ( );
        long daysDiff = TimeUnit.MILLISECONDS.toDays ( msDiff );


        Log.d ( TAG , "getDaysCount: " + daysDiff );
        return String.valueOf ( daysDiff );
    }


}