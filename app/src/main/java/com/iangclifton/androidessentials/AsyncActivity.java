package com.iangclifton.androidessentials;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class AsyncActivity extends ActionBarActivity {

    public static final int PROGRESS_PARTS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AsyncListener {

        private SampleTask mSampleTask;
        private String mResult;

        public PlaceholderFragment() {
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            setRetainInstance(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_async, container, false);

            final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
            final Button button = (Button) rootView.findViewById(R.id.button);
            final View cancelButton = rootView.findViewById(R.id.cancel_button);
            final TextView textView = (TextView) rootView.findViewById(R.id.textView);

            if (!TextUtils.isEmpty(mResult)) {
                textView.setText(mResult);
                progressBar.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                return rootView;
            }

            textView.setText("Starting...");
            progressBar.setMax(PROGRESS_PARTS);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLoading();
                    v.setEnabled(false);
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSampleTask == null) {
                        return;
                    }
                    mSampleTask.cancel(true);
                }
            });
            return rootView;
        }

        @Override
        public void onComplete(String myString) {
            final View rootView = getView();
            if (rootView != null) {
                final TextView textView = (TextView) rootView.findViewById(R.id.textView);
                textView.setText(myString);
                final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
                progressBar.setVisibility(View.GONE);
            }
            mResult = myString;
        }

        @Override
        public void onProgress(Integer progress) {
            final View rootView = getView();
            if (rootView != null) {
                final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
                progressBar.setProgress(progress);
            }
        }

        public void startLoading() {
            if (mSampleTask == null) {
                mSampleTask = new SampleTask(this);
                mSampleTask.execute();
            }
        }
    }

    public static class SampleTask extends AsyncTask<Void, Integer, String> {

        private final AsyncListener mAsyncListener;

        public SampleTask(AsyncListener asyncListener) {
            mAsyncListener = asyncListener;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                for (int i = 0; i < PROGRESS_PARTS; i++) {
                    onProgressUpdate(i);
                    if (isCancelled()) {
                        return null;
                    }
                    Thread.sleep(DateUtils.SECOND_IN_MILLIS);
                }
                return "Success";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Failure";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            mAsyncListener.onComplete(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mAsyncListener.onProgress(values[0]);
        }

        @Override
        protected void onCancelled(String s) {
            mAsyncListener.onComplete("Canceled");
        }

        @Override
        protected void onCancelled() {
            mAsyncListener.onComplete("Canceled");
        }
    }

    public static interface AsyncListener {
        public void onComplete(String myString);
        public void onProgress(Integer progress);
    }
}
