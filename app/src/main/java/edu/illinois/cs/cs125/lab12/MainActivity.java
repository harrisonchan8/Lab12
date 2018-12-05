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
    private static RequestQueue requestQueue;

    final String apiKey = "ZNZ0lwTSjSn3ECYTuSw92smDiAJTgmP5CTl2Me46";
    final String uri = "https://developer.nrel.gov/api/utility_rates/v3.json?";

    /**
     * Run when our activity comes into view.
     *
     * @param savedInstanceState state that was saved by the activity last time it was paused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.fuck_cs125);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            CalculatorSlides calculatorSlidesFragment = new CalculatorSlides();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, calculatorSlidesFragment, "calculatorSlidesFragment").commit();
        }
        //dummy query
        String query = "&lat=35.45&lon=-82.98";
        apiCall(query);
    }

    void apiCall(String query) {
        try {
            String url = uri + "api_key=" + apiKey + query;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
//                            System.out.println(response);
                            try {
                                JSONObject outputs = response.getJSONObject("outputs");
                                Double commercial = outputs.getDouble("commercial");
                                Double residential = outputs.getDouble("residential");
                                Double[] costArr = {commercial, residential};
                                for (int i = 0; i < costArr.length; i++) {
                                    System.out.println(costArr[i]);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    System.out.println(error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
