package com.example.mrmilk.models;


public class Products
{
    private String image_url;

    private String quantity;

    private String sub_category;

    private String price;

    private String id;

    private String category;

    private String product_name;

    private String brand;



    public String getImage ()
    {
        return image_url;
    }

    public void setImage (String image_url)
    {
        this.image_url = image_url;
    }

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }

    public String getSub_category ()
    {
        return sub_category;
    }

    public void setSub_category (String sub_category)
    {
        this.sub_category = sub_category;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getProduct_name ()
    {
        return product_name;
    }

    public void setProduct_name (String product_name)
    {
        this.product_name = product_name;
    }

    public String getBrand ()
    {
        return brand;
    }

    public void setBrand (String brand)
    {
        this.brand = brand;
    }


    @Override
    public String toString()
    {
        return "[image = "+ image_url +", quantity = "+quantity+", sub_category = "+sub_category+", price = "+price+", id = "+id+", category = "+category+", product_name = "+product_name+", brand = "+brand+"]";
    }
}



