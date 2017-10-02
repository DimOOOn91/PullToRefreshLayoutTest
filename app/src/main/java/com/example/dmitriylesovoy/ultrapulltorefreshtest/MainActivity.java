package com.example.dmitriylesovoy.ultrapulltorefreshtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {

    RecyclerAdapter adapter;

    PtrFrameLayout ptrFrame;
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptrFrame = (PtrFrameLayout) findViewById(R.id.ptr_frame);
        rvList = (RecyclerView) findViewById(R.id.rv_list);

        initRecyclerView();

    }

    private void initRecyclerView() {
        adapter = new RecyclerAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
    }

}
