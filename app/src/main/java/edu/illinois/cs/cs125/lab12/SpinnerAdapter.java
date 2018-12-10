package edu.illinois.cs.cs125.lab12;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;

import java.util.Arrays;
import java.util.List;

public class SpinnerAdapter extends MaterialSpinnerAdapter<SpinnerItem> {

    public SpinnerAdapter(Context context, List<SpinnerItem> items) {
        super(context, items);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public SpinnerItem getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public SpinnerItem get(int position) {
        return super.get(position);
    }

    @Override
    public List<SpinnerItem> getItems() {
        return super.getItems();
    }
    @Override
    public String getItemText(int position) {
        SpinnerItem item = this.getItem(position);
        return item.getItemText();
    }

    @Override
    public int getSelectedIndex() {
         System.out.println("selected index: " + super.getSelectedIndex());
         return super.getSelectedIndex();
    }

}


