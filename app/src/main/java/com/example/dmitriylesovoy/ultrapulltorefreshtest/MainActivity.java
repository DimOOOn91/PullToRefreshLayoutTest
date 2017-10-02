package com.example.dmitriylesovoy.ultrapulltorefreshtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<String> titles = new ArrayList<>();

    RecyclerAdapter adapter;

    PtrClassicFrameLayout ptrFrame;
    RecyclerView rvList;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.ptr_frame);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(this);

        initRecyclerView();
        initPullToRefresh();

    }

    private void initRecyclerView() {
        adapter = new RecyclerAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
    }

    private void initPullToRefresh() {
        ptrFrame.setLastUpdateTimeRelateObject(rvList);
        Progress progress = new Progress(this);
        ptrFrame.setHeaderView(progress);
        ptrFrame.setPullToRefresh(true);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mockList(3);
                ptrFrame.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, rvList, header);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mockList(3);
    }

    private void mockList(int size) {
        int startSize = titles.size();
        for (int i = startSize; i < startSize + size; i++) {
            titles.add("Title " + i);
        }
        adapter.setItems(titles);
    }

    @Override
    public void onClick(View v) {
        adapter.setItems(new ArrayList<String>());
    }
}
