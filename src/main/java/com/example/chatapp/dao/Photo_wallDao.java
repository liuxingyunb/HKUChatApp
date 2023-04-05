package com.example.chatapp.dao;

import com.example.chatapp.model.po.Photo_wall;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
@Mapper
public interface Photo_wallDao {

    @Insert("insert into photo_wall(user_id, photo_url,create_time) value(#{user_id}, #{photo_url},#{create_time})" )
    public void addPhoto(Photo_wall photoWall);
    @Delete("DELETE FROM photo_wall WHERE id = #{id}")
    public void deletePhotoById(int id);
    @Select("SELECT * FROM photo_wall WHERE id = #{id}")
    Photo_wall selectPhotosById(int id);
    public void updatePhoto_wall(Map<String, Object> map);


}
