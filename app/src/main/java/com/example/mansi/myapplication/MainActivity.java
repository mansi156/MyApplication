package com.example.mansi.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goPayment();
    }

    private void goPayment() {
        Intent in = new Intent(getApplicationContext(), PayTabActivity.class);
// Merchant
        in.putExtra(PaymentParams.MERCHANT_EMAIL, "alrefaiy.mohammed@gmail.com");
        in.putExtra(PaymentParams.SECRET_KEY, "Xw5ny7zjNhGFkTv11TEDvc8SBB3TMEDKaJ7JQH2DZIijCOlEIZhdcp5N18GNMkxX3FGUnJxmsQG4F1In10AdqG0rPHeB2Mz5rf0O"); //Add your Secret Key Here
        in.putExtra(PaymentParams.LANGUAGE, PaymentParams.ENGLISH);

        in.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#0075c9");
        in.putExtra(PaymentParams.THEME, PaymentParams.THEME_LIGHT);
// Transaction
        in.putExtra(PaymentParams.TRANSACTION_TITLE, "My application title");
        in.putExtra(PaymentParams.PRODUCT_NAME, "Product name");
        in.putExtra(PaymentParams.AMOUNT, 3.5);
        in.putExtra(PaymentParams.CURRENCY_CODE, "BHD");
        in.putExtra(PaymentParams.ORDER_ID, "123456");
// Customer
        in.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, "009733");
        in.putExtra(PaymentParams.CUSTOMER_EMAIL, "j.deo@example.com");
// Billing Address
        in.putExtra(PaymentParams.ADDRESS_BILLING, "Flat 11 Building 222 Block 333 Road 444 Manama Bahrain");
        in.putExtra(PaymentParams.CITY_BILLING, "Manama");
        in.putExtra(PaymentParams.STATE_BILLING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_BILLING, "BHR");
        in.putExtra(PaymentParams.POSTAL_CODE_BILLING, "12345"); //Put Country Phone code if Postal code not available '00973'
// Shipping Address
        in.putExtra(PaymentParams.ADDRESS_SHIPPING, "Flat 11 Building 222 Block 333 Road 444 Manama Bahrain");
        in.putExtra(PaymentParams.CITY_SHIPPING, "Manama");
        in.putExtra(PaymentParams.STATE_SHIPPING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_SHIPPING, "BHR");
        in.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, "12345"); //Put Country Phone code if Postal code not available '00973'
//Tokenization
        in.putExtra(PaymentParams.IS_TOKENIZATION, true);
        startActivityForResult(in, PaymentParams.PAYMENT_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {
            Log.e("Tag", data.getStringExtra(PaymentParams.RESPONSE_CODE));
            Log.e("Tag", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            Toast.makeText(MainActivity.this, data.getStringExtra(PaymentParams.RESPONSE_CODE), Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this, data.getStringExtra(PaymentParams.TRANSACTION_ID), Toast.LENGTH_LONG).show();
            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {
                Log.e("Tag", data.getStringExtra(PaymentParams.TOKEN));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD));
            }
        }
    }

}
