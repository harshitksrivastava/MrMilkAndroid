package com.example.mrmilk.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mrmilk.activities.SubCategoryActivity;
import com.example.mrmilk.models.Categories;
import com.example.mrmilk.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Categories> mData;
    RequestOptions options;

    public RecyclerViewAdapter(Context mContext, List<Categories> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request options for Glide
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cardview_item_categories,parent,false);

        final MyViewHolder viewHolder = new MyViewHolder(view);



        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("category name1", mData.get(viewHolder.getAdapterPosition()).getName());
                Intent subcategory_page = new Intent(mContext, SubCategoryActivity.class);
                subcategory_page.putExtra("catg_name", mData.get(viewHolder.getAdapterPosition()).getName());
                Log.e("category name12", mData.get(viewHolder.getAdapterPosition()).getName());
                subcategory_page.putExtra("catg_thumbnail",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(subcategory_page);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.catg_name.setText(mData.get(position).getName());

        //Load Image from internet and set it into Imageview using Glide
        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(options).into(holder.catg_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView catg_name;
        ImageView catg_thumbnail;
        CardView cardView;



        public MyViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.categories_id);
            catg_name = itemView.findViewById(R.id.category_title_id);
            catg_thumbnail = itemView.findViewById(R.id.category_img_id);


        }
    }

}
