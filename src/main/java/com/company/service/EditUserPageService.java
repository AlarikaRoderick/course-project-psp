package com.company.service;

import com.company.entities.UserEntity;
import org.json.simple.JSONObject;

import java.io.IOException;

public class EditUserPageService {
    public String updateUser(UserEntity user) throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "editUser");
        object.put("user", user);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }
}
