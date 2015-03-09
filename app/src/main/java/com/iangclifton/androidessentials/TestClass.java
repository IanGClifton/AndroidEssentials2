package com.iangclifton.androidessentials;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 11/12/2014.
 */
public class TestClass {
    private static final String TAG = "TestClass";

    private String mMyString = "Hello";

    public void dummyMethod() {
        final List<String> myStrings = new ArrayList<String>();
        myStrings.add("Hi");
        myStrings.add("Bye");

        Log.d(TAG, "My message");
        for (String myString : myStrings) {

        }
    }

    public String getMyString() {
        return mMyString;
    }

    public void setMyString(String myString) {
        mMyString = myString;
    }
}
