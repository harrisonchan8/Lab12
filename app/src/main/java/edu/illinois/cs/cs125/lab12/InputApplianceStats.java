package edu.illinois.cs.cs125.lab12;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;


public class InputApplianceStats extends Fragment {

    final String apiKey = "ZNZ0lwTSjSn3ECYTuSw92smDiAJTgmP5CTl2Me46";
    final String uri = "https://developer.nrel.gov/api/utility_rates/v3.json?";
    private APIBitch myApi = new APIBitch(uri, apiKey);
    private int wattsPerHour;
    private int timesUsedPerDay;
    final String[] statesArr = {"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"};
    private String stateSelected = "Alabama";
    private Double[] costArr;
    private Double residentialCost;
    private Double commercialCost;
    private Double cost;
    final String[] priceType = {"commercialCost", "residentialCost"};
    private String priceTypeSelected = "commercialCost";
    ArrayAdapter<CharSequence> adapter;
    EditText editWattage;
    EditText editTimesUsed;
    private double num;
    TextView resultText;


    public InputApplianceStats() { }

    public void setCosts() {
        String costQuery = "&address=" + stateSelected;
        costArr = myApi.getNewCost(costQuery);
        if (costArr != null) {
            residentialCost = costArr[0];
            commercialCost = costArr[1];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setCosts();
        View view = inflater.inflate(R.layout.input_appliance_stats, container, false);
        Button getResults = view.findViewById(R.id.calculateResultsButton);
        MaterialSpinner spinnerStatesSelection = (MaterialSpinner) view.findViewById(R.id.spinnerStateSelection);
        spinnerStatesSelection.setItems(statesArr);

        //Spinner spinnerPriceTypeSelection = (Spinner) view.findViewById(R.id.priceTypeSelection);
        //adapter = ArrayAdapter.createFromResource(getContext(), R. )
        //spinnerPriceTypeSelection.setOnItemClickListener(priceType);


        editWattage = (EditText) view.findViewById(R.id.editTextWattage);
        editTimesUsed = (EditText) view.findViewById(R.id.editTextTimesUsed);
        final TextView resultText = view.findViewById(R.id.resultTextView);

        //Update Selected State and get cost on state selected
        spinnerStatesSelection.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                stateSelected = statesArr[position];
                setCosts();
            }
        });

        MaterialSpinner  spinnerPriceTypeSelection = (MaterialSpinner) view.findViewById(R.id.spinnerPriceSelection);
        spinnerPriceTypeSelection.setItems(priceType);
        spinnerPriceTypeSelection.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                priceTypeSelected = priceType[position];
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
                //DUMB CALL BACK PLEASE REPLACE
                if (costArr == null) {
                    residentialCost = myApi.getNewCost()[0];
                    commercialCost = myApi.getNewCost()[1];
                }
                makeCalculation();
                resultText.setText(String.valueOf(num));
                Snackbar.make(view, "Price calculated. Swipe back for new selection", Snackbar.LENGTH_LONG).show();
                //Toast.makeText(getContext(), String.valueOf(num), 2000 ).show();
            }
        });
        return view;
    }
    private void makeCalculation() {
        if (editWattage.getText().toString().equals("")) {
            num = 0.0;
        } else {
            wattsPerHour = Integer.valueOf(editWattage.getText().toString());
            timesUsedPerDay = Integer.valueOf(editTimesUsed.getText().toString());
            if (wattsPerHour < 0 || timesUsedPerDay < 0 || timesUsedPerDay > 24) {
                wattsPerHour = 0;
                timesUsedPerDay = 0;
            }
            if (priceTypeSelected.equals("commercialCost")) {
                cost = commercialCost;
            }
            if (priceTypeSelected.equals("residentialCost")) {
                cost = residentialCost;
            }
            num = wattsPerHour * timesUsedPerDay * 30 * cost / 1000;
        }
        /*wattsPerHour = Integer.valueOf(editWattage.getText().toString());
        timesUsedPerDay = Integer.valueOf(editTimesUsed.getText().toString());
        if (priceTypeSelected.equals("commercialCost")) {
            cost = commercialCost;
        }
        if (priceTypeSelected.equals("residentialCost")) {
            cost = residentialCost;
        }*/
        System.out.print(num);
    }
}
