package com.hfad.happylibrary;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.hfad.happylibrary.Fragments.BooksFragment;
import com.hfad.happylibrary.Fragments.MyBooksFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FetchBooks extends AsyncTask<String, Void, String> {
    private static String LOG_TAG = FetchBooks.class.getSimpleName();

    private Fragment mObj;
    private int ids[];
    private String[] names, prices, images, authors, descriptions, moreInfo, isavailable;

    public FetchBooks(Fragment obj) {
        mObj = obj;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {
        try {
            return new NetworkUtils().getInfo(strings[1], "", Integer.parseInt(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("books");

            ids = new int[jsonArray.length()];
            names = new String[jsonArray.length()];
            prices = new String[jsonArray.length()];
            images = new String[jsonArray.length()];
            authors = new String[jsonArray.length()];
            descriptions = new String[jsonArray.length()];
            moreInfo = new String[jsonArray.length()];
            isavailable = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cur = jsonArray.getJSONObject(i);

                ids[i] = Integer.parseInt(cur.getString("id"));
                names[i] = cur.getString("title");
                prices[i] = cur.getString("price");
                images[i] = cur.getString("image");
                authors[i] = cur.getString("author");
                descriptions[i] = cur.getString("description");
                moreInfo[i] = cur.getString("info");
                isavailable[i] = cur.getString("isavailable");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            BooksFragment mmObj = (BooksFragment) mObj;
            mmObj.setBooks();
        } catch (Exception e) {
        }

        try {
            MyBooksFragment mmObj = (MyBooksFragment) mObj;
            mmObj.setBooks();
            Log.d(LOG_TAG, s);
        } catch (Exception e) {
        }

    }

    public int[] getIds() {
        return ids;
    }

    public String[] getNames() {
        return names;
    }

    public String[] getPrices() {
        return prices;
    }

    public String[] getImages() {
        return images;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public String[] getMoreInfo() {
        return moreInfo;
    }

    public String[] getIsavailable(){return isavailable;}
}
