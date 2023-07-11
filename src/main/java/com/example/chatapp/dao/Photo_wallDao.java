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
    @Delete("DELETE FROM photo_wall WHERE user_id = #{userid} AND photo_url= #{url}")
    public void deletePhotoByUrl(int userid,String url);
    @Select("SELECT * FROM photo_wall WHERE id = #{id}")
    Photo_wall selectPhotosById(int id);
    @Select("SELECT photo_url FROM photo_wall WHERE user_id = #{userid} ")
    List<String> selectPhotosByUser(int userid);
    public void updatePhoto_wall(Map<String, Object> map);


}
