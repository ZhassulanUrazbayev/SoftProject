package com.hfad.happylibrary;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class BookInfo extends AppCompatActivity {

    private ImageView mBookImage;
    private TextView mTitleDescription, mAuthorDescription, mDescription, mPrice;
    private WebView mWebView;
    private Button Download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        mBookImage = findViewById(R.id.image_description);
        mTitleDescription = findViewById(R.id.Title_description);
        mAuthorDescription = findViewById(R.id.Author_description);
        mPrice = findViewById(R.id.price_value);
        Download = findViewById(R.id.add_book);
        mDescription = findViewById(R.id.Description);
        mWebView = findViewById(R.id.WebView);

        Intent intent = getIntent();

        String title = intent.getStringExtra("name");
        String author = intent.getStringExtra("author");
        String description = intent.getStringExtra("description");
        String price = intent.getStringExtra("price");
        String image = intent.getStringExtra("image");
        String info = intent.getStringExtra("info");
        final String status = intent.getStringExtra("isavailable");

        mPrice.setText(price);

        if (status.equals("available")) {
            mPrice.setText(price);
        } else {
            Download.setText(status);
        }

        Glide.with(this).load(image).into(mBookImage);
        mTitleDescription.setText(title);
        mDescription.setText(description);
        mAuthorDescription.setText(author);
        mWebView.loadData(info, "text/html", "UTF-8");

        Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!status.equals("available")) {
                    Toast.makeText(BookInfo.this, "Cказали же " + status, Toast.LENGTH_LONG).show();
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://knigolub.net/uploads/book/Absoliutnaia_al'tiernativa_-_Il'ia_Tio.pdf")));
                }
            }
        });


    }
}
