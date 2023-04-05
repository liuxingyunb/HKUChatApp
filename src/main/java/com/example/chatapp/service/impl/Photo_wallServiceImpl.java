package com.example.chatapp.service.impl;

import com.example.chatapp.dao.Photo_wallDao;
import com.example.chatapp.model.po.Photo_wall;
import com.example.chatapp.service.Photo_wallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Photo_wallServiceImpl implements Photo_wallService {
    @Autowired
    private Photo_wallDao photo_wallDao;
    @Override
    public Photo_wall getPhoto_wallById(int id) {
        return photo_wallDao.selectPhotosById(id);
    }

    @Override
    public void addPhoto_wall(Photo_wall photo_wall) {
        photo_wallDao.addPhoto(photo_wall);
    }

    @Override
    public void updatePhoto_wall(Map<String, Object> map) {
        photo_wallDao.updatePhoto_wall(map);
    }

    @Override
    public void deletePhoto_wall(int id) {
        photo_wallDao.deletePhotoById(id);
    }
}
