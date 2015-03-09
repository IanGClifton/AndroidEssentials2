package com.iangclifton.androidessentials;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class InternalStorageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_internal_storage, container, false);

            rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new FileTask(getActivity()).execute();
                }
            });
            return rootView;
        }
    }

    public static class FileTask extends AsyncTask<Void, Void, String> {
        private static final String TAG = "FileTask";

        private static final String FILENAME = "ourFile";

        private final Context mAppContext;

        public FileTask(Context context) {
            mAppContext = context.getApplicationContext();
        }

        @Override
        protected String doInBackground(Void... params) {
            final File ourFile = new File(mAppContext.getFilesDir(), FILENAME);
            if (ourFile.exists() && ourFile.length() > 0) {
                FileInputStream inputStream = null;
                try {
                    // Remember, in a real app, you want to read via a buffer
                    inputStream = new FileInputStream(ourFile);
                    final byte[] bytes = new byte[(int) ourFile.length()];
                    inputStream.read(bytes);
                    final String ourString = new String(bytes);
                    Log.d(TAG, "ourString: " + ourString);
                    return ourString;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.w(TAG, "Failed to read file");
                return null;
            } else {
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(ourFile);
                    outputStream.write("This is my data".getBytes());
                    Log.d(TAG, "Successfully created file");
                    return null;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.w(TAG, "Failed to write to file");
            }

            return null;
        }
    }
}
