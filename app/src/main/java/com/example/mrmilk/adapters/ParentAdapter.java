package com.example.mrmilk.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrmilk.R;
import com.example.mrmilk.models.BrandList;
import com.example.mrmilk.models.Products;

import java.util.ArrayList;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {

    private List<BrandList> mbrands;
    private List<Products> products;
    private Context mContext ;
    ArrayList<Integer> counter = new ArrayList<Integer>();

    public ParentAdapter(List<BrandList> mbrands, Context mContext) {
        this.mbrands = mbrands;
        this.mContext = mContext;

        for (int i = 0; i < mbrands.size(); i++) {
            counter.add(0);
        }
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.listparent_subcategory,parent,false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.catg_name.setText(mbrands.get(position).getBrand_name());
        int pr_size = mbrands.get(position).getProducts().size();
        holder.no_of_products.setText(String.valueOf(mbrands.get(position).getProducts().size()));



        Log.e("parent Adapter",mbrands.get(position).getProducts().toString());
        ChildAdapter childAdapter = new ChildAdapter(mbrands.get(position).getProducts(),mContext);
        holder.cardRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter.get(position) % 2 == 0) {
                    holder.cardRecyclerView.setVisibility(View.VISIBLE);
                   // holder.category_expand_btn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up);

                } else {
                    holder.cardRecyclerView.setVisibility(View.GONE);
                   // holder.category_expand_btn.setImageResource(R.drawable.ic_down_arrow);

                }

                counter.set(position, counter.get(position) + 1);

            }
        });
        holder.category_expand_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter.get(position) % 2 == 0) {
                    holder.cardRecyclerView.setVisibility(View.VISIBLE);
                    //holder.category_expand_btn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up);

                } else {
                    holder.cardRecyclerView.setVisibility(View.GONE);
                    //holder.category_expand_btn.setImageResource(R.drawable.ic_down_arrow);
                }

                counter.set(position, counter.get(position) + 1);

            }

        });
                holder.cardRecyclerView.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return mbrands.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


    TextView catg_name;
    TextView no_of_products;
    ImageButton category_expand_btn;
    CardView cardView;
    RecyclerView cardRecyclerView;

    public MyViewHolder(View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.parent_card_view);
        catg_name = itemView.findViewById(R.id.brand_id);
        no_of_products = itemView.findViewById(R.id.total_no_of_products_id);
        category_expand_btn = (ImageButton)itemView.findViewById(R.id.category_expand_btn);
        cardRecyclerView = itemView.findViewById(R.id.innerRecyclerView);


    }
}

}
