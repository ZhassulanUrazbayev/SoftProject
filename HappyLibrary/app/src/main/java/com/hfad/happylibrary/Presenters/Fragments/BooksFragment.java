package com.hfad.happylibrary.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.happylibrary.Models.Book;
import com.hfad.happylibrary.Adapters.BooksAdapter;
import com.hfad.happylibrary.Fetchers.FetchBooks;
import com.hfad.happylibrary.Utils.NetworkUtils;
import com.hfad.happylibrary.R;

import java.util.ArrayList;

public class BooksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BooksAdapter mAdapter;
    private ArrayList<Book> data;
    private FetchBooks mFetchBooks;

    public BooksFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        setData();

        mAdapter = new BooksAdapter(data, getContext());

        mRecyclerView = view.findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private void setData() {
        data = new ArrayList<>();
        mFetchBooks = new FetchBooks(this);

        mFetchBooks.execute(NetworkUtils.BOOKS+"", "");
    }

    public void setBooks() {
        int ids[] = mFetchBooks.getIds();
        String[] names = mFetchBooks.getNames();
        String[] prices = mFetchBooks.getPrices();
        String[] images = mFetchBooks.getImages();
        String[] authors = mFetchBooks.getAuthors();
        String[] descriptions = mFetchBooks.getDescriptions();
        String[] moreInfo = mFetchBooks.getMoreInfo();
        String[] isavailable = mFetchBooks.getIsavailable();
        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                Book cur = new Book(ids[i], names[i], descriptions[i], prices[i], images[i], authors[i], moreInfo[i], isavailable[i]);
                data.add(cur);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
