package com.iangclifton.androidessentials.launcher;

/**
 * Created by ian on 3/8/15.
 */
public class ActivityItem {
    private final String mName;
    private final Class mClass;

    public ActivityItem(String name, Class activityClass) {
        mName = name;
        mClass = activityClass;
    }

    /**
     * Returns the Activity class associated with this item
     *
     * @return Class for this item
     */
    public Class getActivityClass() {
        return mClass;
    }

    @Override
    public String toString() {
        return mName;
    }
}
