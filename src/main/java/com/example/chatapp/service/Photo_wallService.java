package com.example.chatapp.service;

import com.example.chatapp.model.po.Photo_wall;

import java.util.List;
import java.util.Map;

public interface Photo_wallService {
    Photo_wall getPhoto_wallById(int id);
    void addPhoto_wall(Photo_wall photo_wall);
    void updatePhoto_wall(Map<String, Object> map);
    void deletePhotoByUrl(int userid,String url);
    void deletePhoto_wall(int id);
    List<String> selectPhotosByUser(int userid);
}
