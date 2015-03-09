package com.iangclifton.androidessentials;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";


    private static final String ACTION_LOG_MESSAGE = "com.iangclifton.androidessentials.action.LOG_MESSAGE";

    private static final String EXTRA_MESSAGE = "com.iangclifton.androidessentials.extra.MESSAGE";

    public static void startActionLogMe(Context context, String logMessage) {
        final Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_LOG_MESSAGE);
        intent.putExtra(EXTRA_MESSAGE, logMessage);
        context.startService(intent);
    }

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LOG_MESSAGE.equals(action)) {
                final String logMessage = intent.getStringExtra(EXTRA_MESSAGE);
                handleActionLogMe(logMessage);
            }
        }
    }

    private void handleActionLogMe(String logMessage) {
        Log.d(TAG, logMessage);
        final Intent intent = new Intent(MyReceiver.ACTION_MY_DATA);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
