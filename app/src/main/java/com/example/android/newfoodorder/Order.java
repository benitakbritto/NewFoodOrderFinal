package com.example.android.newfoodorder;

/**
 * Created by fiona on 3/30/18.
 */

public class Order {
    String username, itemname;

    public Order(){

    }

    public Order(String username, String itemname )
    {
        this.itemname = itemname;
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public String getItemname(){
        return itemname;
    }


    public void setItemname(String itemname)
    {
        this.itemname = itemname;

    }

    public void setUsername(String username)
    {
        this.username = username;

    }

}
