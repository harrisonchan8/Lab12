package edu.illinois.cs.cs125.lab12;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ResultsFragment extends Fragment {

    public InputApplianceStats inputApplianceStats = new InputApplianceStats();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results, container, false);
        Button heyBitch = view.findViewById(R.id.heyBitch);
        heyBitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getChildFragmentManager().beginTransaction().replace(R.id.resultsLayout, inputApplianceStats, "inputApplianceStatsFragment").commit();
                List<Fragment> fragmentList = getFragmentManager().getFragments();
                for (Fragment fragment : fragmentList) {
                    System.out.println(fragment.getTag());
                    if (fragment.getTag().equals("inputApplianceStatsFragment")) {
                        getFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                }
            }
        });

        return view;
    }
}
