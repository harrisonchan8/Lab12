package edu.illinois.cs.cs125.lab12;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalculatorSlides extends Fragment {

    private ViewPager viewPager;
    private FragmentViewPagerAdapter mainFragmentPagerAdapter;

    public CalculatorSlides() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculator_slides, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentViewPagerAdapter(getChildFragmentManager()));
        return view;
    }
}
