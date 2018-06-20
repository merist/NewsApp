package com.android.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 6/13/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * This is the custom constructor.
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context  The current context. Used to inflate the layout file.
     * @param newsList A List of News objects to display in a list
     */
    public NewsAdapter(Activity context, ArrayList<News> newsList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        super(context, 0, newsList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the News object located at this position in the list
        News currentNews = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID section_name
        TextView categoryTextView = (TextView) listItemView.findViewById(R.id.section_name);
        // Get the news category from the current News object and
        // set this text on the news section TextView
        categoryTextView.setText(currentNews.getSectionName());

        // Find the TextView in the list_item.xml layout with the ID news_title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.news_title);
        // Get the news title from the current News object and
        // set this text on the title TextView
        titleTextView.setText(currentNews.getTitle());

        // Find the TextView in the list_item.xml layout with the ID news_date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.news_date);
        String date = currentNews.getPublicationDate().substring(0, 10);
        // Get the news date from the current News object and
        // set this text on the date TextView
        dateTextView.setText(date);

        // Find the TextView in the list_item.xml layout with the ID news_time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.news_time);
        String time = currentNews.getPublicationDate().substring(11, 19);
        // Get the news time from the current News object and
        // set this text on the time TextView
        timeTextView.setText(time);

        // Find the TextView in the list_item.xml layout with the ID news_author
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.news_author);
        // Get the news author from the current News object and
        // set this text on the author TextView
        authorTextView.setText(currentNews.getAuthor());

        // Return the whole list item layout so that it can be shown in the ListView
        return listItemView;
    }
}
