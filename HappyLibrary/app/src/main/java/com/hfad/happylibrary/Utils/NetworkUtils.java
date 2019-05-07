package com.hfad.happylibrary;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public static final int BOOKS = 1;
    public static final int SUBSCRIBE = 2;
    public static final int MY_BOOKS = 3;
    // Base URL for API.
    private static final String BASE_URL_BOOKS = "http://134.209.252.202:5000/lib/api/v1.0/books?";
    private static final String BASE_URL_SUBSCRIBE = "http://134.209.252.202:5000/lib/api/v1.0/subscribe?";
    private static final String BASE_URL_MY_BOOKS = "http://134.209.252.202:5000/lib/api/v1.0/subscriptions/";
    // Parameters for the search string.
    private static final String QUERY_PARAM_USER = "user_id";
    private static final String QUERY_PARAM_BOOK = "book_id";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getInfo(String user_id, String book_id, int URL) throws IOException {
        String url = "", JSONString = "";

        switch (URL) {
            case BOOKS:
                url = BASE_URL_BOOKS;
                JSONString = baseUrlBooks(url);
                break;
            case SUBSCRIBE:
                url = BASE_URL_SUBSCRIBE;
                baseUrlSubscribe(url, "user_id=" + user_id + "&book_id=" + book_id);
                break;
            case MY_BOOKS:
                url = BASE_URL_MY_BOOKS;
                JSONString = baseUrlBooks(url+user_id);
                break;
        }

        return JSONString;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String baseUrlSubscribe(String url, String query) throws MalformedURLException,
            ProtocolException, IOException {
        HttpURLConnection con = null;
        String JSONString = "";
        BufferedReader reader = null;

        URL myurl = new URL(url+query);
        try {
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            InputStream inputStream = con.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            JSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return JSONString;
    }

    private String baseUrlBooks(String url) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONString = "$";
        try {
            Uri builtURI = Uri.parse(url).buildUpon().build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            JSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return JSONString;
    }
}