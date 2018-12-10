package edu.illinois.cs.cs125.lab12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;

import java.util.Arrays;
import java.util.List;

public class SpinnerAdapter extends MaterialSpinnerAdapter<SpinnerItem> {
    LayoutInflater inflater;

    public SpinnerAdapter(Context context, List<SpinnerItem> items) {
        super(context, items);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public long getItemId(int position) {
        return super.getItem(position).getItemId();
    }

    @Override
    public int getSelectedIndex() {
         return super.getSelectedIndex();
    }

    public SpinnerItem getSelectedItem() {
        return this.getItem(this.getSelectedIndex());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView itemName = (TextView) itemView.findViewById(R.id.itemName);
        itemName.setText(this.getItemText(position));
        ImageView itemIcon = (ImageView) itemView.findViewById(R.id.itemIcon);
        try {
            itemIcon.setImageResource((int) this.getItemId(position));
        } catch (Exception e) {
            System.out.println("Some fucking error: " + e);
        }
        return itemView;
    }


}


