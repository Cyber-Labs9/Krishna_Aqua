package com.ka.krishnaaqua.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ka.krishnaaqua.R;
import com.ka.krishnaaqua.data.OrderData;
import com.ka.krishnaaqua.databinding.ActivityReviewOrderBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ReviewOrder extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = ReviewOrder.class.getSimpleName ( );

    //    Variable Declaration

    private ActivityReviewOrderBinding binding;
    private OrderData orderData;
    private int Total;
    private int Days;
    private String id, name, email, mobile;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_review_order );
        binding = ActivityReviewOrderBinding.inflate ( getLayoutInflater ( ) );
        View view = binding.getRoot ( );
        setContentView ( view );

        Intent intent = getIntent ( );
        orderData = intent.getExtras ( ).getParcelable ( "order_data" );

        String Qty = String.valueOf ( orderData.getQuantity ( ) );
        int Price = Integer.parseInt ( String.valueOf ( orderData.getPrice ( ) ) );

        id     = orderData.getId ( );
        name   = orderData.getName ( );
        email  = orderData.getEmail ( );
        mobile = orderData.getMobile ( );
        String StartDate = orderData.getStartDate ( );
        String EndDate = orderData.getEndDate ( );

        Calendar start = getCalendarInstance ( StartDate );
        Calendar end = getCalendarInstance ( EndDate );

        String diff = getDaysCount ( end , start );
        Days = Integer.parseInt ( diff );

        Total = 100 * (Price * Days);
        Log.v ( "" , String.valueOf ( Total ) );
        binding.QuantityValue.setText ( Qty + " " + "Litres" );
        binding.PriceValue.setText ( "₹" + Price + " " + "per Day" );
        binding.StartValue.setText ( StartDate );
        binding.EndDateValue.setText ( EndDate );
        binding.NoOfDaysValue.setText ( diff );
        binding.TotalValue.setText ( "₹" + Price * Days );


        binding.bookbtn.setOnClickListener ( v -> {
            startPayment ( );
        } );

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
            preFill.put ( "email" , "" + email );
            preFill.put ( "contact" , "" + mobile );


            co.open ( activity , options );
        } catch ( Exception e ) {
            Toast.makeText ( activity , "Error in payment: " + e.getMessage ( ) , Toast.LENGTH_SHORT )
                    .show ( );
            e.printStackTrace ( );
        }


    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText ( this , "Payment Successful: " + razorpayPaymentID , Toast.LENGTH_SHORT ).show ( );
            startActivity ( new Intent ( ReviewOrder.this , History.class ) );
        } catch ( Exception e ) {
            Log.e ( TAG , "Exception in onPaymentSuccess" , e );
        }

    }

    @Override
    public void onPaymentError(int i , String s) {
        try {
            Toast.makeText ( this , "Payment failed: " + i + " " + s , Toast.LENGTH_SHORT ).show ( );
        } catch ( Exception e ) {
            Log.e ( TAG , "Exception in onPaymentError" , e );
        }
    }



    /*------------------------------ Convert String in Calendar ---------------------------------*/

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




    /*------------------------------ Count Days Between Dates ---------------------------------*/

    private String getDaysCount(Calendar start , Calendar end) {

        long msDiff = start.getTimeInMillis ( ) - end.getTimeInMillis ( );
        long daysDiff = TimeUnit.MILLISECONDS.toDays ( msDiff );


        Log.d ( TAG , "getDaysCount: " + daysDiff );
        return String.valueOf ( daysDiff );
    }


}