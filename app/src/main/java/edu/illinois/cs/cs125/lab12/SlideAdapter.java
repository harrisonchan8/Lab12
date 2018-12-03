package edu.illinois.cs.cs125.lab12;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {
    Context ctx;
    LayoutInflater inflater;

    String[] temporaryPageTitles = {"Choosing your appliance", "Entering the stats for selected appliance"};

    public SlideAdapter(Context context) {
        this.ctx = context;
    }

    @Override
    public int getCount() {
        return temporaryPageTitles.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calculator_slide, container, false);
        LinearLayout slideLinearLayout = view.findViewById(R.id.calculatorSlideLinearLayout);
        TextView pageTitle = view.findViewById(R.id.pageTitle);
        pageTitle.setText(temporaryPageTitles[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }
}
