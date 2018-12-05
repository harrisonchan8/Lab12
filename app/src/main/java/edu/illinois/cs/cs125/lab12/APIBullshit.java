package edu.illinois.cs.cs125.lab12;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class APIBullshit extends AsyncTask<Void, Void, Void>{
    String textResults;

    APIBullshit() { }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL apiUrl = new URL("https://developer.nrel.gov/api/utility_rates/v3.json?api_key=DEMO_KEY&lat=35.45&lon=-82.98");
            URLConnection connection = apiUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            } br.close();
            System.out.println(response.toString());
            System.out.println("Hello?");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
