package com.example.carson_ho.toptabbar;

/**
 * Created by newlife on 2018/1/28.
 */

public class Fruit {
    private String name;
    private int imageId;
    private int imageArrowId;

    public Fruit(String name,int imageId,int imageArrowId)
    {
        this.name = name;
        this.imageId = imageId;
        this.imageArrowId = imageArrowId;
    }

    public String getName()
    {
        return name;
    }

    public int getImageId()
    {
        return imageId;
    }
    public int getImageArrowId()
    {
         return imageArrowId;
    }
}
