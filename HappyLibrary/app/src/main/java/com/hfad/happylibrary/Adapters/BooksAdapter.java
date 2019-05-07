package com.hfad.happylibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.hfad.happylibrary.Fragments.MyBooksFragment;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private static final String LOG_TAG = BooksAdapter.class.getSimpleName();

    private ArrayList<Book> data;
    private Context context;

    public BooksAdapter(ArrayList<Book> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void addToMyBooks(Book book) {
        FetchSubscribe fetchSubscribe = new FetchSubscribe();
        fetchSubscribe.execute(book.getId()+"");
        Intent intent = new Intent(context, MyBooksFragment.class);

        intent.putExtra("flag",true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Book currentBook = data.get(position);

        viewHolder.bindTo(currentBook);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BookInfo.class);

                intent.putExtra("name", currentBook.getName());
                intent.putExtra("description", currentBook.getDescription());
                intent.putExtra("author", currentBook.getAuthor());
                intent.putExtra("image", currentBook.getImage());
                intent.putExtra("price", currentBook.getPrice());
                intent.putExtra("info", currentBook.getMoreInfo());
                intent.putExtra("isavailable", currentBook.getIsavailable());

                context.startActivity(intent);
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(context);

                myAlertBuilder.setTitle("Message");
                myAlertBuilder.setMessage("You are going to buy.");

                myAlertBuilder.setPositiveButton("Accept", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Нажатие на ДА
                                addToMyBooks(currentBook);
                                notifyDataSetChanged();
                            }
                        });
                myAlertBuilder.setNegativeButton("No No No", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Нажатие на Нет
                                dialog.cancel();
                            }
                        });
                myAlertBuilder.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mPoster;
        private TextView mPrice;
        private TextView mTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mPoster = itemView.findViewById(R.id.poster);
            mPrice = itemView.findViewById(R.id.price);
            mTitle = itemView.findViewById(R.id.title);
        }

        public void bindTo(Book currentBook) {
            mPrice.setText(currentBook.getPrice());
            mTitle.setText(currentBook.getName());
            Glide.with(context).load(currentBook.getImage()).into(mPoster);
        }
    }
}
