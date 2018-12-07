package edu.illinois.cs.cs125.lab12;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;


public class InputApplianceStats extends Fragment {

    final String apiKey = "ZNZ0lwTSjSn3ECYTuSw92smDiAJTgmP5CTl2Me46";
    final String uri = "https://developer.nrel.gov/api/utility_rates/v3.json?";
    private APIBitch myApi = new APIBitch(uri, apiKey);
    private int wattsPerHour;
    private int timesUsedPerYear;
    private int num;
    final String[] statesArr = {"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"};
    private String stateSelected = "Alabama";
    private Double[] costArr;
    private Double residentialCost;
    private Double commercialCost;

    public InputApplianceStats() { }
    /**
     * ADD CALLBACK PLEASE TO GET IT TO WOEK!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public void setCosts() {
        System.out.println("state selected: " + stateSelected);
        String costQuery = "&address=" + stateSelected;
        costArr = myApi.getNewCost(costQuery);
        if (costArr != null) {
            residentialCost = 0.0;
            commercialCost = 0.0;
        }
        System.out.println("residential cost for " + stateSelected + " is: " + residentialCost);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setCosts();
        View view = inflater.inflate(R.layout.input_appliance_stats, container, false);
        Button getResults = view.findViewById(R.id.calculateResultsButton);
        MaterialSpinner spinnerStatesSelection = (MaterialSpinner) view.findViewById(R.id.spinnerStateSelection);
        spinnerStatesSelection.setItems(statesArr);

        EditText editTextWattage = view.findViewById(R.id.editTextWattage);
        EditText editTextTimesUsed = view.findViewById(R.id.editTextTimesUsed);
        String wattageString = editTextWattage.getText().toString();
        String timesUsedString = editTextTimesUsed.getText().toString();
        wattsPerHour = wattageString.equals("") ? 0 : Integer.parseInt(wattageString);
        timesUsedPerYear = timesUsedString.equals("") ? 0 : Integer.parseInt(timesUsedString);
        num = wattsPerHour * timesUsedPerYear;

        //Update Selected State and get cost on state selected
        spinnerStatesSelection.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                stateSelected = statesArr[position];
                setCosts();
            }
        });

        getResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragmentList = getFragmentManager().getFragments();
                for (Fragment fragment : fragmentList) {
                    System.out.println(fragment);
                    System.out.println(fragment.getTag());
                    if (fragment.getTag().equals("calculatorSlidesFragment")) {
                        getFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                }
                Toast.makeText(view.getContext(), "Test Message", Toast.LENGTH_LONG).show();
                //price.setText(num);
            }
        });
        return view;
    }

}
