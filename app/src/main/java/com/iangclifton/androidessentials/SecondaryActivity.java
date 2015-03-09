package com.iangclifton.androidessentials;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SecondaryActivity extends ActionBarActivity implements DoThingInterface {

    public static final String INTENT_KEY_MY_STRING = SecondaryActivity.class.getPackage() + ".myString";

    public static Intent getIntent(Context context, String myString) {
        if (TextUtils.isEmpty(myString)) {
            throw new IllegalArgumentException("myString cannot be null or empty");
        }
        final Intent intent = new Intent(context, SecondaryActivity.class);
        intent.putExtra(SecondaryActivity.INTENT_KEY_MY_STRING, myString);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String enteredString = getIntent().getStringExtra(INTENT_KEY_MY_STRING);
        if (TextUtils.isEmpty(enteredString)) {
            throw new IllegalStateException("Required value INTENT_KEY_MY_STRING not found");
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, SecondaryFragment.newInstance(enteredString))
                    .commit();
        }

    }

    @Override
    public void doThing(int position) {
        // Do things
    }

    public static class SecondaryFragment extends Fragment {

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            if (activity instanceof DoThingInterface) {
                // We're happy
            } else {
                // We're sad
                throw new IllegalStateException("SecondaryFragment can only be used in a DoThingInterface instance");
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_secondary, container, false);
            final TextView textView = (TextView) rootView.findViewById(R.id.textView);
            textView.setText(getArguments().getString(INTENT_KEY_MY_STRING));

            final View button = rootView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(android.R.id.content, new ThirdFragment());
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
            return rootView;
        }

        public static SecondaryFragment newInstance(String myString) {
            final SecondaryFragment fragment = new SecondaryFragment();
            final Bundle args = new Bundle();
            args.putString(INTENT_KEY_MY_STRING, myString);
            fragment.setArguments(args);
            return fragment;
        }
    }

    public static class ThirdFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final TextView textView = new TextView(getActivity());
            textView.setText("Hi!");
            return textView;
        }
    }
}
