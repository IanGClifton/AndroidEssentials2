package com.iangclifton.androidessentials;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ian on 11/10/2014.
 */
public class SampleAdapter extends ArrayAdapter<String> {

    private static final String[] SAMPLE_DATA = {
            "A is for Android",
            "B is for Bacon",
            "C is for Cookie"
};

    private final LayoutInflater mLayoutInflater;

    public SampleAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1, SAMPLE_DATA);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String myString = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.bigText.setText(myString);
        viewHolder.smallText.setText(myString.replace("for", "the"));

        return convertView;
    }

    private static class ViewHolder {
        final ImageView imageView;
        final TextView bigText;
        final TextView smallText;

        public ViewHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.icon);
            bigText = (TextView) convertView.findViewById(R.id.big_text);
            smallText = (TextView) convertView.findViewById(R.id.small_text);
        }
    }
}
