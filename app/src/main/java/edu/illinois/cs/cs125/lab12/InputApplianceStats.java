package edu.illinois.cs.cs125.lab12;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class InputApplianceStats extends Fragment {


    public InputApplianceStats() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_appliance_stats, container, false);
        Button getResults = view.findViewById(R.id.calculateResultsButton);
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
            }
        });
        return view;
    }

}
