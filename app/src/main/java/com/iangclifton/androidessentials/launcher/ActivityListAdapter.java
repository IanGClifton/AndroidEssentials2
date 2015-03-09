package com.iangclifton.androidessentials.launcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ian on 3/8/15.
 */
public class ActivityListAdapter extends BaseAdapter {

    private final Context mContext;
    private final ActivityItem[] mActivityItems;

    public ActivityListAdapter(Context context, ActivityItem[] activityItems) {
        mContext = context;
        mActivityItems = activityItems;
    }

    @Override
    public int getCount() {
        return mActivityItems.length;
    }

    @Override
    public ActivityItem getItem(int position) {
        return mActivityItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        final TextView textView = (TextView) convertView;
        textView.setText(getItem(position).toString());
        return convertView;
    }
}
