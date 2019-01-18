package com.example.android.newfoodorder;

/**
 * Created by fiona on 3/30/18.
 */

public class Order {
    String user, itemname;

    public Order(){

    }

    public Order(String username, String itemname )
    {
        this.itemname = itemname;
        this.user = username;
    }

    public String getUsername(){
        return user;
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
        this.user = username;

    }

}
