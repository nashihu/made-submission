package com.example.content_receiver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TontonanItem> tontonanItems;

    public MainAdapter() {}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = tontonanItems.get(i).title;
        String url   = tontonanItems.get(i).url;
        viewHolder.textView.setText(title);
        Picasso.get().load(url).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return tontonanItems.size();
    }

    public void setItems(Context context, ArrayList<TontonanItem> tontonanItems) {
        this.context = context;
        this.tontonanItems = tontonanItems;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_title);
            imageView = itemView.findViewById(R.id.iv_item);
        }
    }
}
