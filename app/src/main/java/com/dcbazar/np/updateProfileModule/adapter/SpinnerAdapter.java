package com.dcbazar.np.updateProfileModule.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dcbazar.np.R;

import java.util.ArrayList;

/**
 * Created by Amir on 03/04/2017.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflate;
    private int resourceId;
    private ArrayList<String> options;
    private Context context;
    private int spinnerid;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<String> objects, int spinnerid) {
        super(context, resource, objects);
        this.context = context;
        this.inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceId = resource;
        this.options = objects;
        this.spinnerid = spinnerid;
    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Typeface externalFont = Typeface.createFromAsset(context.getAssets(), "fonts/sofiapro-light.otf");
        if (convertView == null) {
            convertView = inflate.inflate(resourceId, null);
            Holder holder = new Holder();

            holder.textView1 = (TextView) convertView.findViewById(R.id.tv_default_first);

            holder.textView2 = (TextView) convertView.findViewById(R.id.tv_default_second);
            holder.textView1.setTextSize(18);
            holder.textView2.setTextSize(18);
            holder.textView1.setTypeface(externalFont);
            holder.textView2.setTypeface(externalFont);
            holder.textView1.setHintTextColor(Color.WHITE);
            holder.textView2.setHintTextColor(Color.WHITE);
            holder.textView1.setTextColor(Color.WHITE);
            holder.textView2.setTextColor(Color.WHITE);


            convertView.setTag(holder);
        }
        Holder holder = (Holder) convertView.getTag();
        holder.textView1.setText(options.get(position));
        holder.textView2.setText("");
        holder.textView1.setTypeface(externalFont);
        holder.textView2.setTypeface(externalFont);
        holder.textView1.setHintTextColor(Color.WHITE);
        holder.textView2.setHintTextColor(Color.WHITE);
        holder.textView1.setTextColor(Color.WHITE);
        holder.textView2.setTextColor(Color.WHITE);

        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Typeface externalFont = Typeface.createFromAsset(context.getAssets(), "fonts/sofiapro-light.otf");
        if (convertView == null) {
            convertView = inflate.inflate(resourceId, null);
            Holder holder = new Holder();

            holder.textView1 = (TextView) convertView.findViewById(R.id.tv_default_first);
            holder.textView2 = (TextView) convertView.findViewById(R.id.tv_default_second);
            holder.textView1.setTypeface(externalFont);
            holder.textView2.setTypeface(externalFont);
            holder.textView1.setHintTextColor(Color.WHITE);
            holder.textView2.setHintTextColor(Color.WHITE);
            holder.textView1.setTextColor(Color.WHITE);
            holder.textView2.setTextColor(Color.WHITE);

            holder.textView1.setTextSize(18);
            holder.textView2.setTextSize(18);

            convertView.setTag(holder);
        }
        Holder holder = (Holder) convertView.getTag();
        if (position == getCount()) {
            if (spinnerid == 1) {
                holder.textView1.setText("Gender");
                holder.textView2.setText("");
            } else if (spinnerid == 2) {
                holder.textView1.setText("Income");
                holder.textView2.setText("(Optional)");
            } else if (spinnerid == 3) {
                holder.textView1.setText("Occupation");
                holder.textView2.setText("");
            } else if (spinnerid == 4) {
                holder.textView1.setText("Age");
                holder.textView2.setText("");
            }

        } else {
            Log.d("first", options.get(position));
            holder.textView1.setText(options.get(position));
            holder.textView2.setText("");
            holder.textView1.setTypeface(externalFont);
            holder.textView2.setTypeface(externalFont);
            holder.textView1.setHintTextColor(Color.WHITE);
            holder.textView2.setHintTextColor(Color.WHITE);
            holder.textView1.setTextColor(Color.WHITE);
            holder.textView2.setTextColor(Color.WHITE);

        }
        return convertView;
    }

    private class Holder {
        TextView textView1, textView2;
    }


}
