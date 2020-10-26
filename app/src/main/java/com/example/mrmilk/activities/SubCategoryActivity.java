package com.example.mrmilk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mrmilk.R;
import com.example.mrmilk.adapters.ParentAdapter;
import com.example.mrmilk.models.BrandList;
import com.example.mrmilk.models.Products;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class SubCategoryActivity extends AppCompatActivity {

    TextView sub_category_title;
    ImageView sub_category_img;
    public static LinearLayout linearLayout;




    private final String JSON_URL = "http://192.168.29.8:8000/mr_milk/brands/" ;       //"http://10.0.2.2:8000/mr_milk/brands/";
    private List<BrandList> lstBrands;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private String subcategory_title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Log.e("category name2",getIntent().getExtras().getString("catg_name") );
        subcategory_title = getIntent().getExtras().getString("catg_name");
        String subcategory_image_url = getIntent().getExtras().getString("catg_thumbnail");
        Toolbar toolbar = findViewById(R.id.toolbar_subcategory);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });
        sub_category_title = findViewById(R.id.subcategory_title_id);
        sub_category_img = findViewById(R.id.subcategory_img_id);
        recyclerView = findViewById(R.id.recycler_subcategory);
        linearLayout = (LinearLayout) findViewById(R.id.linear_cart_layout);


        sub_category_title.setText(subcategory_title);


        Log.e("++category thumbnail++",subcategory_image_url);
        Glide.with(this).load(subcategory_image_url).into(sub_category_img);

        lstBrands = new ArrayList<>();
        jsonrequest();


    }


    public void showcart(){
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void hidecart(){
        linearLayout.setVisibility(View.GONE);
    }


    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++){

                    try {
                        jsonObject = response.getJSONObject(i);
                        BrandList brandList = new BrandList();
                        ArrayList<Products> productsList = new ArrayList<>();



                        JSONArray jsonArray = jsonObject.getJSONArray("products");

                        if(jsonArray.length()!=0){

                            for(int j=0;j<jsonArray.length();j++) {
                                Products products = new Products();


                                try {

                                    if(!jsonArray.getJSONObject(j).getString("category").equals(subcategory_title))
                                    {  continue;
                                    }
                                    else
                                    {
                                        try {
                                            products.setProduct_name(jsonArray.getJSONObject(j).getString("product_name"));
                                            products.setId(jsonArray.getJSONObject(j).getString("id"));
                                            products.setImage(jsonArray.getJSONObject(j).getString("image"));
                                            products.setPrice(jsonArray.getJSONObject(j).getString("price"));
                                            products.setQuantity(jsonArray.getJSONObject(j).getString("quantity"));
                                            products.setCategory(jsonArray.getJSONObject(j).getString("category"));
                                            products.setSub_category(jsonArray.getJSONObject(j).getString("sub_category"));
                                            products.setBrand(jsonArray.getJSONObject(j).getString("brand"));

                                            Log.e("list of Products",products.toString());
                                            productsList.add(products);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            if(productsList.size()!=0){
                            brandList.setBrand_name(jsonObject.getString("brand_name"));
                            brandList.setProducts(productsList);
                            Log.e("Products before adding",brandList.getProducts().toString());

                            lstBrands.add(brandList);
                            }
                        }


                    }
                catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.e("List from brand", lstBrands.get(0).getProducts().toString());
                setuprecyclerview(lstBrands);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




    private void setuprecyclerview(List<BrandList> lstBrands) {

        ParentAdapter myadapter = new ParentAdapter(lstBrands, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(myadapter);
    }


}
