package edu.illinois.cs.cs125.lab12;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class InputApplianceStats extends Fragment {


    public InputApplianceStats() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_appliance_stats, container, false);
        Button getResults = view.findViewById(R.id.calculateResultsButton);
//        int wattsPerHour = Integer.parseInt(view.findViewById(R.id.Wattage);
//        int timePerDay = Integer.parseInt(view.findViewById(R.id.timesUsed));
//        TextView price = view.findViewById(R.id.priceResult);
//        int num = wattsPerHour * timePerDay * 30;
        EditText editTextWattage = view.findViewById(R.id.editTextWattage);
        EditText editTextTimesUsed = view.findViewById(R.id.editTextTimesUsed);

        int wattsPerHour = Integer.parseInt(editTextWattage.getText().toString());
        int timesUsedPerYear = Integer.parseInt(editTextTimesUsed.getText().toString());



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
                Toast.makeText(view.getContext(), "pushed", 100).show();
                //price.setText(num);
            }
        });
        return view;
    }

}
