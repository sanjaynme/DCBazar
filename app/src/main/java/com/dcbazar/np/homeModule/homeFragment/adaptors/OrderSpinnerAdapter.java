package com.dcbazar.np.homeModule.homeFragment.adaptors;

import android.content.Context;
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
 * Created by Amir on 21/04/2017.
 */


public class OrderSpinnerAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflate;
    private int resourceId;
    private ArrayList<String> options;
    private Context context;


    public OrderSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<String> objects) {
        super(context, resource,objects);
        this.context = context;
        this.inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceId = resource;
        this.options = objects;

    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = inflate.inflate(resourceId, null);
            Holder holder = new Holder();
            holder.textView1 = (TextView)convertView.findViewById(R.id.tv_default_first);
            holder.textView2 = (TextView) convertView.findViewById(R.id.tv_default_second);
            convertView.setTag(holder);
        }
        Holder holder = (Holder)convertView.getTag();
        holder.textView1.setText(options.get(position));
        holder.textView2.setText("");


        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = inflate.inflate(resourceId, null);
            Holder holder = new Holder();
            holder.textView1 = (TextView)convertView.findViewById(R.id.tv_default_first);
            holder.textView2 = (TextView) convertView.findViewById(R.id.tv_default_second);
            convertView.setTag(holder);
        }
        Holder holder =(Holder)convertView.getTag();
        if(position==getCount()){

        }else{
            Log.d("first",options.get(position));
            holder.textView1.setText(options.get(position));
            holder.textView2.setText("");
        }
        return convertView;
    }

    private class Holder{
        TextView textView1, textView2;
    }


}
