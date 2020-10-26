package com.example.mrmilk.models;

import java.util.ArrayList;

public class BrandList
{
    private String brand_name;
    private ArrayList<Products> products;


    public String getBrand_name ()
    {
        return brand_name;
    }

    public void setBrand_name (String brand_name)
    {
        this.brand_name = brand_name;
    }

    public ArrayList<Products> getProducts ()
    {
        return products;
    }

    public void setProducts (ArrayList<Products> products)
    {
        this.products = products;
    }


    @Override
    public String toString()
    {
        return "[brand_name = "+brand_name+", products = "+products+"]";
    }
}

