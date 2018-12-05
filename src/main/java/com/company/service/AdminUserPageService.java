package com.company.service;

import com.company.entities.UserEntity;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AdminUserPageService {
    public JSONObject getUsers() throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getUsers");
        SendObjectService.sendObject(object);
        return SendObjectService.getObject();
    }

    public String updateUser(UserEntity user) throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "updateUser");
        object.put("user", user);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }

    public String deleteUser(UserEntity user) throws IOException, ClassNotFoundException{
        JSONObject object = new JSONObject();
        object.put("action", "deleteUser");
        object.put("user", user);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }

    public String addUser(UserEntity user) throws IOException, ClassNotFoundException{
        JSONObject object = new JSONObject();
        object.put("action", "addUser");
        object.put("user", user);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }
}
