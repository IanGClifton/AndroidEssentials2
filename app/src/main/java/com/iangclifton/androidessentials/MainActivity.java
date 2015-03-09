package com.iangclifton.androidessentials;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private MyReceiver mMyReceiver;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

//        final Intent intent = new Intent(this, SimpleService.class);
//        startService(intent);
//        MyIntentService.startActionLogMe(this, "This is my really clever log message.");

//        final Intent intent = new Intent(MyReceiver.ACTION_MY_DATA);

        // Send to the whole system
//        sendBroadcast(intent);

        // Send to our app only
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        mMyReceiver = new MyReceiver();
        mIntentFilter = new IntentFilter(MyReceiver.ACTION_MY_DATA);
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMyReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMyReceiver, mIntentFilter);


//        registerReceiver(mMyReceiver, mIntentFilter);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends ListFragment {

        public PlaceholderFragment() {
        }

//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.textView);
//            textView.setText("Arbitrary string");
//            return rootView;
//        }


        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            getListView().setAdapter(new SampleAdapter(getActivity()));
            setListShown(true);
            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            startActivity(SecondaryActivity.getIntent(getActivity(), "Clicked " + position));
                            return;

                        case 2:
                            MyIntentService.startActionLogMe(getActivity(), "Clicked the cookie.");
                            return;
                    }
                    Toast.makeText(getActivity(), "Clicked " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
