package com.luck.pictureselector.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.captlu.oos.VosBean;
import com.luck.pictureselector.GlideEngine;
import com.luck.pictureselector.R;

public class GalleryAdapter extends ListAdapter<VosBean.DataDTO, GalleryAdapter.MyVH> {

    public GalleryAdapter(@NonNull DiffUtil.ItemCallback<VosBean.DataDTO> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyVH vh = new MyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_cell, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        Glide.with(holder.itemView)
                .load(getItem(position).getImguri())
                .into(holder.img);
        holder.time.setText(getItem(position).getCreatetime());
    }

    protected static class MyVH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView time;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            time = itemView.findViewById(R.id.create);
        }
    }
}
