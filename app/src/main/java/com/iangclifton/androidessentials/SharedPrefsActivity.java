package com.iangclifton.androidessentials;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SharedPrefsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String PREFS_COUNT = "prefsCount";

        private int mCount;

        public PlaceholderFragment() {
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_shared_prefs, container, false);

            // SharedPreferences
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            mCount = prefs.getInt(PREFS_COUNT, 0);

            final TextView textView = (TextView) rootView.findViewById(R.id.textView);
            textView.setText("Count: " + mCount);

            rootView.findViewById(R.id.counter_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCount++;
                    textView.setText("Count: " + mCount);
                }
            });

            return rootView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            final SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(PREFS_COUNT, mCount);
            editor.apply();
        }
    }
}
