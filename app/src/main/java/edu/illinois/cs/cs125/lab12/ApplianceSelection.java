package edu.illinois.cs.cs125.lab12;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class ApplianceSelection extends Fragment {

    private String[] applianceStringArray = {"Air Conditioner", "Ceiling Fan", "Clothes Washer", "Computer", "Hair Dryer",
            "Microwave", "Oven", "Refrigerator", "Radio", "Speakers", "Telephone", "Television", "Water Heater", "Vacuum"};

    private int[] applianceIconArray = {
            R.drawable.ic_airconditioner,
            R.drawable.ic_fan,
            R.drawable.ic_washingmachine,
            R.drawable.ic_computer,
            R.drawable.ic_hairdryer,
            R.drawable.ic_microwave,
            R.drawable.ic_oven,
            R.drawable.ic_fridge,
            R.drawable.ic_radio,
            R.drawable.ic_speakers,
            R.drawable.ic_telephone,
            R.drawable.ic_television,
            R.drawable.ic_kettle,
            R.drawable.ic_vacuum,
    };

    public SpinnerItem selectedItem;

    private ArrayList<SpinnerItem> applianceList;

    private ImageView selectedApplianceImageView;

    public ApplianceSelection() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("Hey!");
        applianceList = createApplianceList(this.applianceStringArray, this.applianceIconArray);
        View view = inflater.inflate(R.layout.appliance_selection, container, false);
        MaterialSpinner applianceSelectionSpinner = (MaterialSpinner) view.findViewById(R.id.materialSpinner);
        SpinnerAdapter applianceSelectionSpinnerAdapter = new SpinnerAdapter(this.getContext(), this.applianceList);
        applianceSelectionSpinner.setAdapter(applianceSelectionSpinnerAdapter);
        selectedItem = applianceSelectionSpinnerAdapter.getSelectedItem();
        System.out.println("Selected: " + selectedItem.getItemText());
        selectedApplianceImageView = (ImageView) view.findViewById(R.id.selectedApplianceImageView);
        selectedApplianceImageView.setImageResource(selectedItem.getItemId());
        applianceSelectionSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selectedItem = (SpinnerItem) item;
                selectedApplianceImageView.setImageResource(selectedItem.getItemId());
                Snackbar.make(view, "Selected " + item + ", swipe to go to next page", Snackbar.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private ArrayList<SpinnerItem> createApplianceList(String[] namesArray, int[] iconArray) {
        ArrayList<SpinnerItem> applianceList = new ArrayList<SpinnerItem>();
        for (int i = 0; i < namesArray.length; i++) {
            applianceList.add(new SpinnerItem(namesArray[i], iconArray[i]));
        }
        return applianceList;
    }

}
