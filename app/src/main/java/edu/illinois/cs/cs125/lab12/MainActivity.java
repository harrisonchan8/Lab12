package edu.illinois.cs.cs125.lab12;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Main screen for our API testing app.
 */


public final class MainActivity extends AppCompatActivity {

    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our network requests. */


    final String apiKey = "ZNZ0lwTSjSn3ECYTuSw92smDiAJTgmP5CTl2Me46";
    final String uri = "https://developer.nrel.gov/api/utility_rates/v3.json?";
    final String testQuery = "&lat=35.45&lon=-82.98";
    APIBitch apiBitch;

    /**
     * Run when our activity comes into view.
     *
     * @param savedInstanceState state that was saved by the activity last time it was paused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiBitch = new APIBitch(uri, apiKey, testQuery);
        apiBitch.requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.fuck_cs125);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            CalculatorSlides calculatorSlidesFragment = new CalculatorSlides();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, calculatorSlidesFragment, "calculatorSlidesFragment").commit();
        }
        //dummy query
        apiBitch.getNewCost();
    }
}
