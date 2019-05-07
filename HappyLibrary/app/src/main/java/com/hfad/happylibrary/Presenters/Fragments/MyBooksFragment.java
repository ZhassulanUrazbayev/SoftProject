package com.hfad.happylibrary.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.hfad.happylibrary.Models.Book;
import com.hfad.happylibrary.Adapters.BooksAdapter;
import com.hfad.happylibrary.Fetchers.FetchBooks;
import com.hfad.happylibrary.Utils.NetworkUtils;
import com.hfad.happylibrary.R;

import java.util.ArrayList;


public class MyBooksFragment extends Fragment {
    private static final String LOG_TAG = MyBooksFragment.class.getSimpleName();

    private ArrayList<Book> data;
    private FetchBooks mFetchBooks;
    private RecyclerView RecyclerViewMyBook;
    private BooksAdapter mAdapter;

    public MyBooksFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_books, container, false);
        setData();

        mAdapter = new BooksAdapter(data,getContext());

        RecyclerViewMyBook = view.findViewById(R.id.RecyclerView_my_books);
        RecyclerViewMyBook.setLayoutManager(new GridLayoutManager(getContext(), 2));
        RecyclerViewMyBook.setAdapter(mAdapter);

        return view;

    }

    private void setData() {
        data = new ArrayList<>();
        mFetchBooks = new FetchBooks(this);

        mFetchBooks.execute(NetworkUtils.MY_BOOKS+"", FirebaseAuth.getInstance().getCurrentUser().getUid());
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
            Log.d(LOG_TAG, names.length+"");
            for (int i = 0; i < names.length; i++) {
                Book cur = new Book(ids[i], names[i], descriptions[i], prices[i], images[i], authors[i], moreInfo[i], isavailable[i]);
                data.add(cur);
            }
        } else {
            Log.d(LOG_TAG, "NULL");
        }
        mAdapter.notifyDataSetChanged();
    }
}
