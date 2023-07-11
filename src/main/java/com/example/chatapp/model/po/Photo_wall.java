package com.example.chatapp.model.po;

import java.util.Date;

public class Photo_wall {

    private int id;
    private int user_id;
    private String photo_url;
    private Date create_time;

    public Photo_wall(int user_id, String photo_url) {
        this.user_id = user_id;
        this.photo_url = photo_url;
        this.create_time=new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
