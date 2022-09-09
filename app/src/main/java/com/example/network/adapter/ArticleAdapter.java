package com.example.network.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.network.R;
import com.example.network.model.Item;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Item> listData;

    public ArticleAdapter(Activity activity, List<Item> listData) {
        this.activity = activity;
        this.listData = listData;
    }

    public void reloadData(List<Item> list){
        listData = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_article, parent, false);
        ArticleViewHolder holder = new ArticleViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArticleViewHolder vh = (ArticleViewHolder) holder;
        Item model = listData.get(position);
        vh.tvDate.setText(model.getDate());
        vh.tvTitle.setText(model.getTitle());
        vh.tvDes.setText(model.getContent().getDescription());
        Glide.with(activity).load(model.getImage()).into(vh.ivCover);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTitle, tvDes;
        ImageView ivCover;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDes = itemView.findViewById(R.id.tvDes);
            ivCover = itemView.findViewById(R.id.ivCover);
        }
    }
}
