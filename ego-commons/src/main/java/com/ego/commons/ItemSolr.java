package com.ego.commons;

import org.apache.solr.client.solrj.beans.Field;

public class ItemSolr {
    @Field("id")
    private String id;
    @Field("item_title")
    private String title;
    @Field("item_sell_point")
    private String sell_point;
    @Field("item_price")
    private Long price;
    @Field("item_image")
    private String image;

    private String[] images;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSell_point() {
        return this.sell_point;
    }

    public void setSell_point(final String sell_point) {
        this.sell_point = sell_point;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(final Long price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public void setImages(final String[] images) {
        this.images = images;
    }

    public String[] getImages(){
        String[] images=image.split(",");
        return images;
    }
}
