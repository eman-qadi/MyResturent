package com.example.myresturent;

public class category {

    String cate ;
    String imageId ;

    public category(String cate, String imageId) {
        this.cate = cate;
        this.imageId = imageId;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }



}
