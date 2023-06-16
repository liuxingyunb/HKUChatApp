package com.example.chatapp.model.po;

import java.util.Date;

public class Chat_group {

    private int id;
    private String name;
    private int owner_id;
    private String members;
    private Date create_time;
    private Date last_active_time;

    private String role;


    public Chat_group(){}

    public Chat_group(String name, int owner_id, String members) {
        this.name = name;
        this.owner_id = owner_id;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_active_time() {
        return last_active_time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLast_active_time(Date last_active_time) {
        this.last_active_time = last_active_time;
    }
}


