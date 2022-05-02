package com.haryanvis.booknarrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.haryanvis.booknarrator.controller.HomeMainController;
import com.haryanvis.booknarrator.controller.SectionMainController;
import com.haryanvis.booknarrator.model.Book;
import com.haryanvis.booknarrator.model.HomeSection;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private RecyclerView sectionsRV;
    private List<HomeSection> sections;
    private HomeMainController rvAdapter;
    private FirebaseDatabase database;
    private List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        books =  new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        database.getReference("/books").get().addOnSuccessListener(dataSnapshot -> {
            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                books.add(snapshot.getValue(Book.class));
            }
            addSections("New Titles",books);
            refreshLayout();
        }).addOnFailureListener(e -> {
            Log.i("PARAMJEET",e.getMessage());
        });
        initVars();




        sectionsRV.setAdapter(rvAdapter);
        sectionsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
//        Book ramNaamSatya = new Book(1,"Ram Naam Satya Hai","Devotional","Hindi","https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
//                348.67,6, "https://cdn.pixabay.com/photo/2021/03/29/10/33/rama-6133619_1280.png");
//        Book mahakaal = new Book(2,"Mahakal","Devotional","Hindi","fgsgegfs",86.98,8,"https://cdn.pixabay.com/photo/2020/09/09/21/09/shiva-5558695_1280.png");
//
//        books = new ArrayList<>();
//
//        books.add(ramNaamSatya);
//        books.add(mahakaal);


    }

    //for Refresh the Layout
    private void refreshLayout() {
        rvAdapter.notifyDataSetChanged();
    }

    //For Adding the New Sections in Home Screen of App
    private void addSections(String sectionName, List<Book> bookList) {
        HomeSection section = new HomeSection(sectionName,bookList);
        sections.add(section);
    }

    //used for initialize the Variables
    private void initVars()
    {
        sectionsRV= findViewById(R.id.rvHomeMain);
        sections = new ArrayList<>();
        rvAdapter = new HomeMainController(sections);
    }



}


