package edu.illinois.cs.cs125.lab12;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

    public InputApplianceStats() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_appliance_stats, container, false);
        Button getResults = view.findViewById(R.id.calculateResultsButton);
        MaterialSpinner spinnerStatesSelection = (MaterialSpinner) view.findViewById(R.id.spinnerStateSelection);
        String[] statesArr = {"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"};
        spinnerStatesSelection.setItems(statesArr);

        EditText editTextWattage = view.findViewById(R.id.editTextWattage);
        EditText editTextTimesUsed = view.findViewById(R.id.editTextTimesUsed);
        String wattageString = editTextWattage.getText().toString();
        String timesUsedString = editTextTimesUsed.getText().toString();
        int wattsPerHour = wattageString.equals("") ? 0 : Integer.parseInt(wattageString);
        int timesUsedPerYear = timesUsedString.equals("") ? 0 : Integer.parseInt(timesUsedString);
        int num = wattsPerHour * timesUsedPerYear;

        //Get Cost (Double) from api
        String costQuery = "=" + statesArr[spinnerStatesSelection.getSelectedIndex()];
        Double[] costArr = myApi.getNewCost(costQuery);
        

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
                Toast.makeText(view.getContext(), "Fuck CS125", Toast.LENGTH_LONG).show();
                //price.setText(num);
            }
        });
        return view;
    }

}
