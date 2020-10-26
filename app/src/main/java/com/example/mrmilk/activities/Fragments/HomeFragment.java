package com.example.mrmilk.activities.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mrmilk.R;
import com.example.mrmilk.adapters.RecyclerViewAdapter;
import com.example.mrmilk.models.Categories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private final String JSON_URL = "http://192.168.29.8:8000/mr_milk/categories/" ;         //      "http://10.0.2.2:8000/mr_milk/categories/";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Categories> lstCategories;
    private RecyclerView recyclerView;

    View view;
    TextView textView;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        lstCategories = new ArrayList<>();
        view = inflater.inflate(R.layout.fragment_home, container, false);
        textView = view.findViewById(R.id.homeTxt);
        textView.setText(("Greetings from Mr. Milk! Select your order"));
        recyclerView = view.findViewById(R.id.recycler_id);
        jsonrequest();

        return view;
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for(int i=0;i<response.length();i++){

                    try {
                        jsonObject = response.getJSONObject(i);
                        Categories category = new Categories();
                        category.setName(jsonObject.getString("category_name"));
                        //category.setImage_url("http://10.0.2.2:8000"+jsonObject.getString("image"));
                        category.setImage_url("http://192.168.29.8:8000"+jsonObject.getString("image"));
                        lstCategories.add(category);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                    setuprecyclerview(lstCategories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<Categories> lstCategories) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getContext(),lstCategories);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        recyclerView.setAdapter(myadapter);
    }
}
