package com.luck.pictureselector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.captlu.oos.Collection;
import com.captlu.oos.VosBean;
import com.luck.pictureselector.adapter.GalleryAdapter;

import io.reactivex.functions.Consumer;

public class SimpleActivity extends AppCompatActivity {
    private Button btn_activity, btn_fragment;
    private RecyclerView recyclerView;
    private GalleryAdapter adapter = new GalleryAdapter(new DiffUtil.ItemCallback<VosBean.DataDTO>() {
        @Override
        public boolean areItemsTheSame(@NonNull VosBean.DataDTO oldItem, @NonNull VosBean.DataDTO newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull VosBean.DataDTO oldItem, @NonNull VosBean.DataDTO newItem) {
            return oldItem.getImguri().equals(newItem.getImguri());
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        btn_activity = findViewById(R.id.btn_activity);
        btn_activity.setOnClickListener(v -> startActivity(new Intent(SimpleActivity.this, MainActivity.class)));

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        Collection.get(vosBean -> {
           adapter.submitList(vosBean.getData());
        });

    }
}
