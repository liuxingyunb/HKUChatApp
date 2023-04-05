package com.example.chatapp.service;

import com.example.chatapp.model.po.Photo_wall;

import java.util.Map;

public interface Photo_wallService {
    Photo_wall getPhoto_wallById(int id);
    void addPhoto_wall(Photo_wall photo_wall);
    void updatePhoto_wall(Map<String, Object> map);
    void deletePhoto_wall(int id);
}
