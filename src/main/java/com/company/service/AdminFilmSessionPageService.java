package com.company.service;

import com.company.entities.SessionEntity;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AdminFilmSessionPageService {
    public String addSession(SessionEntity session) throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "addSession");
        object.put("session", session);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }
}
