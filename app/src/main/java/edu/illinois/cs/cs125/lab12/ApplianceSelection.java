package edu.illinois.cs.cs125.lab12;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

public class ApplianceSelection extends Fragment {


    public ApplianceSelection() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appliance_selection, container, false);
        MaterialSpinner spinner = (MaterialSpinner) view.findViewById(R.id.materialSpinner);
        String[] applianceList = {"Ceiling Fan", "Clothes Dryer", "Clothes Washer", "Computer","Dish Washer", "Hair Dryer"
                , "Microwave", "Humidifier", "Air Conditioner", "Oven", "Refrigerator", "Television", "Water Heater", "Vacuum"};
        spinner.setItems(applianceList);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Selected " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        return view;
    }

}
