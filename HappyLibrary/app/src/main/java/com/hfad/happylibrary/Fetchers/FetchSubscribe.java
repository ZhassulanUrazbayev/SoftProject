package com.hfad.happylibrary;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.hfad.happylibrary.NetworkUtils;

import java.io.IOException;

public class FetchSubscribe extends AsyncTask<String, Void, String> {
    private static final String LOG_TAG = FetchSubscribe.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {
        try {
            Log.d(LOG_TAG, FirebaseAuth.getInstance().getCurrentUser().getUid());
            Log.d(LOG_TAG, strings[0]);
            return new NetworkUtils().getInfo(FirebaseAuth.getInstance().getCurrentUser().getUid(), strings[0] + "", NetworkUtils.SUBSCRIBE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            Log.d(LOG_TAG, s);
        }
    }
}
