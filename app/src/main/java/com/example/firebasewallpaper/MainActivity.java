package com.example.firebasewallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.firebasewallpaper.adapter.CarAdapter;
import com.example.firebasewallpaper.models.WallpaperModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("wallpaper");
    private ArrayList<WallpaperModel> wallpaperModel = new ArrayList<>();
    private CarAdapter carAdapter;
    private ProgressBar loadIndicator;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.carRecycler);
        loadIndicator = findViewById(R.id.loadIndicator);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carAdapter = new CarAdapter(this, wallpaperModel);

        recyclerView.setAdapter(carAdapter);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                wallpaperModel.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    WallpaperModel.Brand b = snapshot.getValue(WallpaperModel.Brand.class);
                    WallpaperModel w = new WallpaperModel();
                    w.setBrand(b);
                    wallpaperModel.add(w);
                }

                loadIndicator.setVisibility(View.GONE);
                carAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}