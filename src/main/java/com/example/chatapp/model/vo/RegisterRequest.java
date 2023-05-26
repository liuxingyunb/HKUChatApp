package com.example.chatapp.model.vo;

import com.example.chatapp.model.po.User;

/*json
{
  "user": {
    "username": "user123",
    "password": "password123",
    "email": "user@example.com"
  },
  //verification code
  "code": "123456"
}
 */

public class RegisterRequest {
    private User user;
    private String code;

    public RegisterRequest(User user, String code) {
        this.user = user;
        this.code = code;
    }

    public RegisterRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getCode() {
        return code;
    }
}
