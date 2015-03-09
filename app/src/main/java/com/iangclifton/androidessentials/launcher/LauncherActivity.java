package com.iangclifton.androidessentials.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iangclifton.androidessentials.AnimationActivity;
import com.iangclifton.androidessentials.AsyncActivity;
import com.iangclifton.androidessentials.InternalStorageActivity;
import com.iangclifton.androidessentials.MainActivity;
import com.iangclifton.androidessentials.PersonListActivity;
import com.iangclifton.androidessentials.R;
import com.iangclifton.androidessentials.SharedPrefsActivity;
import com.iangclifton.androidessentials.StyledActivity;
import com.iangclifton.androidessentials.TabbedActivity;

/**
 * Activity that displays a list of other Activity classes to start
 */
public class LauncherActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final ActivityItem[] sActivityItemsArray = {
            new ActivityItem("Main Activity", MainActivity.class),
            new ActivityItem("Person List Activity", PersonListActivity.class),
            new ActivityItem("Tabbed Activity", TabbedActivity.class),
            new ActivityItem("Async Activity", AsyncActivity.class),
            new ActivityItem("Shared Prefs Activity", SharedPrefsActivity.class),
            new ActivityItem("Internal Storage Activity", InternalStorageActivity.class),
            new ActivityItem("Styled Activity", StyledActivity.class),
            new ActivityItem("Animation Activity", AnimationActivity.class)
    };

    private ActivityListAdapter mActivityListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mActivityListAdapter = new ActivityListAdapter(this, sActivityItemsArray);
        final ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(mActivityListAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, mActivityListAdapter.getItem(position).getActivityClass()));
    }
}
