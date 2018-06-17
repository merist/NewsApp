package com.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/13/2018.
 */

public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getName();

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, MainActivity.getAppContext().getResources().getString(R.string.problem_building_url), e);
        }
        return url;
    }

    private static List<News> extractResponseFromJson (String newsJSON){
        if(TextUtils.isEmpty(newsJSON)){
            return null;
        }
        List<News> newsList = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONArray NewsArray = baseJsonResponse.getJSONArray("response");

            for (int i = 0; i < NewsArray.length(); i++) {

                JSONObject currentNews = NewsArray.getJSONObject(i);

                JSONObject results = currentNews.getJSONObject("results");

                String webTitle = results.getString("webTitle");

                String sectionName = results.getString("sectionName");

                long webPublicationDate = results.getLong("webPublicationDate");

                String url = results.getString("url");

                News news = new News(webTitle, sectionName, webPublicationDate, url);

                newsList.add(news);
            }
        }catch (JSONException e) {
            Log.e(LOG_TAG, MainActivity.getAppContext().getResources().getString(R.string.problem_parsing_json), e);
        }
        return newsList;
    }
    public static List<News> getNewsData (String requestUrl){

        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);

        } catch (IOException e) {
            Log.e(LOG_TAG, MainActivity.getAppContext().getResources().getString(R.string.problem_http_request), e);
        }

        return extractResponseFromJson(jsonResponse);
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            Log.e(LOG_TAG, MainActivity.getAppContext().getResources().getString(R.string.error_connection), e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();

    }
}
