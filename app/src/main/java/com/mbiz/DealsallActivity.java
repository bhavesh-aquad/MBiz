package com.mbiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mbiz.adapter.DealsallAdapter;
import com.mbiz.model.Dealsall;

import java.util.ArrayList;
import java.util.List;

public class DealsallActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Dealsall> dealsallList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealsall);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        dealsallList = new ArrayList<>();


        //adding some items to our list
        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "10%",
                        R.drawable.img2,
                R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "14%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "13%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "14%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "14%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "14%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "13%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "14%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "14%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        dealsallList.add(
                new Dealsall(
                        1,
                        " Restaurant",
                        "14%",
                        R.drawable.img2,
                        R.drawable.map_icon));

        //creating recyclerview adapter
        DealsallAdapter adapter = new DealsallAdapter(this, dealsallList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}

