package com.example.mrmilk.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mrmilk.Common.Common;
import com.example.mrmilk.R;
import com.example.mrmilk.activities.SubCategoryActivity;
import com.example.mrmilk.databases.CartDataSource;
import com.example.mrmilk.databases.CartDatabase;
import com.example.mrmilk.databases.CartItem;
import com.example.mrmilk.databases.LocalCartDataSource;
import com.example.mrmilk.models.BrandList;
import com.example.mrmilk.models.Products;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyViewHolder> {
    private Context mContext ;
    ArrayList<Products> productsArrayList = new ArrayList<>() ;
    ViewGroup container;
    CompositeDisposable compositeDisposable;
    CartDataSource cartDataSource;

    public void onStop(){
        compositeDisposable.clear();
    }

    public ChildAdapter(ArrayList<Products> productsArrayList, Context mContext){

        this.productsArrayList = productsArrayList;
        this.mContext = mContext;
        compositeDisposable = new CompositeDisposable();
        cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(mContext).cartDAO());


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.subcategory_row_item,parent,false);


        ChildAdapter.MyViewHolder myViewHolder = new ChildAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildAdapter.MyViewHolder holder, final int position) {


                holder.product_name.setText(productsArrayList.get(position).getProduct_name());
                holder.product_price.setText(productsArrayList.get(position).getPrice());
                Glide.with(mContext).load(productsArrayList.get(position).getImage()).into(holder.product_img);


        holder.add_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.add_Btn.setVisibility(View.GONE);
                        holder.elegant_number_button.setVisibility(View.VISIBLE);
                        holder.elegant_number_button.setNumber("1");
                                   // Subcategory.relativeLayout.setVisibility(View.VISIBLE);
                        Log.e("just ","before button pressed Noooooooooooooooooooooooooo");

                        CartItem cartItem = new CartItem();
                        cartItem.setFoodId(Integer.parseInt(productsArrayList.get(position).getId()));
                        cartItem.setFoodName(productsArrayList.get(position).getProduct_name());
                        cartItem.setFoodPrice(Double.parseDouble(productsArrayList.get(position).getPrice()));
                        cartItem.setFoodImage(productsArrayList.get(position).getImage());
                        cartItem.setFoodQuantity(1);
                        cartItem.setUserPhone(Common.currentUser.getPhone());

                        compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {


                                },
                                throwable -> {
                            Toast.makeText(mContext,"[ADD CART]"+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                                })





                        );




                    }
                });
//            holder.elegant_number_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    System.out.println("ELEGANT NUMBER"+Integer.valueOf(holder.elegant_number_button.getNumber()));
//                    if (Integer.valueOf(holder.elegant_number_button.getNumber())==0){
//                        holder.add_Btn.setVisibility(View.VISIBLE);
//                        holder.elegant_number_button.setVisibility(View.GONE);
//                    }
//                }
//            });


            holder.elegant_number_button.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                    if (Integer.valueOf(holder.elegant_number_button.getNumber())==0){
                        holder.add_Btn.setVisibility(View.VISIBLE);
                        holder.elegant_number_button.setVisibility(View.GONE);


                    }
                }
            });

    }

//    public void setBottomMargin(){
//
//        RelativeLayout.LayoutParams layoutParams  = (RelativeLayout.LayoutParams) .getLayoutParams();
//        layoutParams.setMargins(0,259,0,52);
//        // layoutParams.setMargins(left, top, right, bottom);
//    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public void addView(View view){
        LayoutInflater vi = (LayoutInflater) mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.cart_popup, null);


//        textView = (TextView) v.findViewById(R.id.txtsampleinsertion);
//        textView.setText("Sample Text Added");


        container.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView product_name;
        TextView product_price;
        ImageView product_img;
        ElegantNumberButton elegant_number_button;
        Button add_Btn,subscribe_Btn;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_name_id);
            product_price = itemView.findViewById(R.id.product_price);
            product_img = itemView.findViewById(R.id.product_row_img);
            add_Btn = itemView.findViewById(R.id.add_Btn);
            subscribe_Btn = itemView.findViewById(R.id.subscribe_Btn);
            elegant_number_button = itemView.findViewById(R.id.number_button);
            container = (ViewGroup) itemView.findViewById(R.id.subcategory_id);

        }
    }


}
