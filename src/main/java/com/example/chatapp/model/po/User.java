package com.example.chatapp.model.po;


import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 用户表
 *
 * @TableName user
 */

public class User  {


    private int id;
    private String username;
    private String password;
    private String role;//admin,user
    private String gender;
    private int age;
    private String hometown;
    private String major;
    private String avatar_url;
    private String signature;
    private String telephone_number;
    private String birthday;
    private String mail;
    private String school;
    private Date create_time;
    private Date last_modified_time;
    private String mbti;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String role, String gender, int age, String hometown, String major, String avatar_url, String signature, String telephone_number, String birthday, String mail, String school, Date create_time, Date last_modified_time) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.hometown = hometown;
        this.major = major;
        this.avatar_url = avatar_url;
        this.signature = signature;
        this.telephone_number = telephone_number;
        this.birthday = birthday;
        this.mail = mail;
        this.school = school;
        this.create_time = create_time;
        this.last_modified_time = last_modified_time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() { return gender;}

    public void setGender(String gender) { this.gender = gender;}

    public int getAge() { return age;}

    public void setAge(int age){ this.age=age;}

    public String getHometown() { return this.hometown;}

    public void setHometown(String hometown) {this.hometown=hometown;}

    public String getMajor() { return major; }

    public void setMajor(String major) { this.major = major;}

    public String getAvatar_url() { return avatar_url; }

    public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url; }

    public String getSignature() { return signature; }

    public void setSignature(String signature) { this.signature = signature; }

    public String getTelephone_number() { return telephone_number; }

    public void setTelephone_number(String telephone_number) { this.telephone_number = telephone_number; }

    public String getBirthday() { return birthday; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getMail() { return mail; }

    public void setMail(String mail) { this.mail = mail; }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public Date getCreate_time() { return create_time; }

    public void setCreate_time(Date create_time) { this.create_time = create_time; }

    public Date getLast_modified_time() { return last_modified_time; }

    public void setLast_modified_time(Date last_modified_time) { this.last_modified_time = last_modified_time; }

    public String getMbti() { return mbti; }

    public void setMbti(String mbti) { this.mbti = mbti; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", hometown='" + hometown + '\'' +
                ", major='" + major + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", signature='" + signature + '\'' +
                ", telephone_number='" + telephone_number + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mail='" + mail + '\'' +
                ", school='" + school + '\'' +
                ", create_time=" + create_time +
                ", last_modified_time=" + last_modified_time +
                '}';
    }
}