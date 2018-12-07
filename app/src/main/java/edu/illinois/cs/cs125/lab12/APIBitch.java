package edu.illinois.cs.cs125.lab12;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class APIBitch {

    private String uri;
    private String apiKey;
    private String query;
    public static RequestQueue requestQueue;

    APIBitch() { }

    APIBitch(String setUri, String setApiKey) {
        this.uri = setUri;
        this.apiKey = setApiKey;
    }

    APIBitch(String setUri, String setApiKey, String setQuery) {
        this.uri = setUri;
        this.apiKey = setApiKey;
        this.query = setQuery;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Double[] getNewCost() {
        return getNewCost(this.query);
    }

    public Double[] getNewCost(String query) {
        final Double costArr[] = new Double[2];
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
                                costArr[0] = commercial;
                                costArr[1] = residential;
                                //Prints costArr
                                System.out.println("Commercial cost: " + costArr[0]);
                                System.out.println("Residential cost: " + costArr[1]);
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
        return null;
    }
}
