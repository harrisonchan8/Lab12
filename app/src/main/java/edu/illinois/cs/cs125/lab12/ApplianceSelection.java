package edu.illinois.cs.cs125.lab12;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class ApplianceSelection extends Fragment {

    private String[] applianceStringArray = {"Ceiling Fan", "Clothes Dryer", "Clothes Washer", "Computer","Dish Washer", "Hair Dryer",
            "Microwave", "Humidifier", "Air Conditioner", "Oven", "Refrigerator", "Television", "Water Heater", "Vacuum"};

    private ArrayList<SpinnerItem> applianceList;

    public ApplianceSelection() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        applianceList = createApplianceList(this.applianceStringArray);
        View view = inflater.inflate(R.layout.appliance_selection, container, false);
        MaterialSpinner applianceSelectionSpinner = (MaterialSpinner) view.findViewById(R.id.materialSpinner);
        SpinnerAdapter applianceSelectionSpinnerAdapter = new SpinnerAdapter(this.getContext(), this.applianceList);
        applianceSelectionSpinner.setAdapter(applianceSelectionSpinnerAdapter);
//        applianceSelectionSpinner.setItems(applianceList);
//        applianceSelectionSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Selected " + item + ", swipe to go to next page", Snackbar.LENGTH_LONG).show();
//            }
//        });
        return view;
    }

    private ArrayList<SpinnerItem> createApplianceList(String[] namesArray) {
        ArrayList<SpinnerItem> applianceList = new ArrayList<SpinnerItem>();
        for (int i = 0; i < namesArray.length; i++) {
            System.out.println(namesArray[i]);
            applianceList.add(new SpinnerItem(namesArray[i]));
            System.out.println("List: " + applianceList.get(i).getItemText());
        }
        return applianceList;
    }

}
