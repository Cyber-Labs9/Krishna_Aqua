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

public class ReviewOrder extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = ReviewOrder.class.getSimpleName ( );

    //    Variable Declaration

    private ActivityReviewOrderBinding binding;
    private OrderData orderData;
    private int Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_review_order );
        binding = ActivityReviewOrderBinding.inflate ( getLayoutInflater ( ) );
        View view = binding.getRoot ( );
        setContentView ( view );

        Intent intent = getIntent ( );
        orderData = intent.getExtras ( ).getParcelable ( "order_data" );

        String Qty = String.valueOf ( orderData.getQuantity ( ) );
        int Price = Integer.parseInt ( String.valueOf ( orderData.getPrice ( ) ) );
        String StartDate = orderData.getStartDate ( );
        String EndDate = orderData.getEndDate ( );


        Total = 100 * Price;
        binding.QuantityValue.setText ( Qty + " " + "Litres" );
        binding.PriceValue.setText ( "₹" + Price + " " + "per Day" );
        binding.StartValue.setText ( StartDate );
        binding.EndDateValue.setText ( EndDate );


        binding.bookbtn.setOnClickListener ( v -> {
            startPayment ( );
        } );

    }

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
            preFill.put ( "email" , "alokrathava@gmail.com" );
            preFill.put ( "contact" , "9512334819" );


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
            startActivity ( new Intent ( ReviewOrder.this , Home.class ) );
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


}