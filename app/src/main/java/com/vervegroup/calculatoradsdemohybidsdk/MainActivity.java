package com.vervegroup.calculatoradsdemohybidsdk;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.vervegroup.calculatoradsdemohybidsdk.databinding.ActivityMainBinding;
import net.pubnative.lite.sdk.HyBid;
import net.pubnative.lite.sdk.HyBid.InitialisationListener;
import net.pubnative.lite.sdk.interstitial.HyBidInterstitialAd;
import net.pubnative.lite.sdk.views.HyBidBannerAdView;
import net.pubnative.lite.sdk.views.PNAdView;

public class MainActivity extends AppCompatActivity  {

    private Application app;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private HyBidBannerAdView mBanner;
    private HyBidInterstitialAd mInterstitial;
    private String hybidAppToken = "dde3c298b47648459f8ada4a982fa92d";
    private String ZONE_ID_BANNER = "2";
    private String ZONE_ID_INTERSTITIAL = "3";
    public EditText e1, e2;
    TextView t1;
    int num1, num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.i(TAG, "onCreate");
        e1 = (EditText) findViewById(R.id.num1);
        // defining the edit text 2 to e2
        e2 = (EditText) findViewById(R.id.num2);
        app = this.getApplication();

        DisplayMetrics display = this.getResources().getDisplayMetrics();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.width = display.widthPixels;
        HyBid.setTestMode(true);

        HyBid.initialize(hybidAppToken, app, new InitialisationListener() {
            @Override
            public void onInitialisationFinished(boolean success) {
                Log.i(TAG, "HybidSDK.initialize");
                View.inflate(app, R.layout.banner_view, layout);
                HyBid.setAppToken(hybidAppToken);
                mBanner = findViewById(R.id.hybid_banner);
                getBannerAd();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public boolean getNumbers() {

        //checkAndClear();
        // defining the edit text 1 to e1
        e1 = (EditText) findViewById(R.id.num1);

        // defining the edit text 2 to e2
        e2 = (EditText) findViewById(R.id.num2);

        // defining the text view to t1
        t1 = (TextView) findViewById(R.id.result);

        // taking input from text box 1
        String s1 = e1.getText().toString();

        // taking input from text box 2
        String s2 = e2.getText().toString();



        if(s1.equals("Please enter value 1") && s2.equals(null))
        {
            String result = "Please enter value 2";
            e2.setText(result);
            return false;
        }
        if(s1.equals(null) && s2.equals("Please enter value 2"))
        {
            String result = "Please enter value 1";
            e1.setText(result);
            return false;
        }
        if(s1.equals("Please enter value 1") || s2.equals("Please enter value 2"))
        {
            return false;
        }

        if((!s1.equals(null) && s2.equals(null))|| (!s1.equals("") && s2.equals("")) ){

            String result = "Please enter value 2";

            e2.setText(result);
            return false;
        }
        if((s1.equals(null) && !s2.equals(null))|| (s1.equals("") && !s2.equals("")) ){
            //checkAndClear();
            String result = "Please enter value 1";
            e1.setText(result);
            return false;
        }
        if((s1.equals(null) && s2.equals(null))|| (s1.equals("") && s2.equals("")) ){
            //checkAndClear();
            String result1 = "Please enter value 1";
            e1.setText(result1);
            String result2 = "Please enter value 2";
            e2.setText(result2);
            return false;
        }

        else {
            // converting string to int.
            num1 = Integer.parseInt(s1);

            // converting string to int.
            num2 = Integer.parseInt(s2);


        }

        return true;
    }

    public void doSum(View v) {

        // get the input numbers
        if (getNumbers()) {
            int sum = num1 + num2;
            t1.setText(Integer.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }

        getInterstitialAd();

    }
    public void clearTextNum1(View v) {

        // get the input numbers
        e1.getText().clear();
    }
    public void clearTextNum2(View v) {

        // get the input numbers
        e2.getText().clear();
    }
    public void doPow(View v) {

        //checkAndClear();
        // get the input numbers
        if (getNumbers()) {
            double sum = Math.pow(num1, num2);
            t1.setText(Double.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }
    }

    // a public method to perform subtraction
    public void doSub(View v) {
        //checkAndClear();
        // get the input numbers
        if (getNumbers()) {
            int sum = num1 - num2;
            t1.setText(Integer.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }
    }

    // a public method to perform multiplication
    public void doMul(View v) {
        //checkAndClear();
        // get the input numbers
        if (getNumbers()) {
            int sum = num1 * num2;
            t1.setText(Integer.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }
    }

    // a public method to perform Division
    public void doDiv(View v) {
        //checkAndClear();
        // get the input numbers
        if (getNumbers()) {

            // displaying the text in text view assigned as t1
            double sum = num1 / (num2 * 1.0);
            t1.setText(Double.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }
    }

    // a public method to perform modulus function
    public void doMod(View v) {
        //checkAndClear();
        // get the input numbers
        if (getNumbers()) {
            double sum = num1 % num2;
            t1.setText(Double.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }
    }

    private void getBannerAd() {
        mBanner.load(ZONE_ID_BANNER, new PNAdView.Listener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "onAdLoaded");
            }

            @Override
            public void onAdLoadFailed(Throwable error) {
                Log.d(TAG, "onAdLoadFailed");
            }

            @Override
            public void onAdImpression() {
                Log.d(TAG, "onAdImpression");
            }

            @Override
            public void onAdClick() {
                Log.d(TAG, "onAdClick");
            }
        });
    }

    private void getInterstitialAd() {
        mInterstitial = new HyBidInterstitialAd(this,ZONE_ID_INTERSTITIAL, new HyBidInterstitialAd.Listener() {
            @Override
            public void onInterstitialLoaded() {
                Log.d(TAG, "onInterstitialLoaded");
                mInterstitial.show();

            }

            @Override
            public void onInterstitialLoadFailed(Throwable error) {
                Log.d(TAG, "onInterstitialLoadFailed");
            }

            @Override
            public void onInterstitialImpression() {

            }

            @Override
            public void onInterstitialDismissed() {

            }

            @Override
            public void onInterstitialClick() {

            }
        });
        mInterstitial.load();
    }


}

