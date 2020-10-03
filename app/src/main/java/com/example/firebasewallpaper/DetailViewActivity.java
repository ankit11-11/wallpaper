package com.example.firebasewallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        TextView t = findViewById(R.id.brand);
        TextView info = findViewById(R.id.info);
        ImageView i = findViewById(R.id.fullView);

        String[] urls = getIntent().getStringArrayExtra("imageUrl");
        t.setText(getIntent().getStringExtra("brandName"));
        info.setText(getIntent().getStringExtra("info"));
        Glide.with(this).load(urls[0]).into(i);
    }
}