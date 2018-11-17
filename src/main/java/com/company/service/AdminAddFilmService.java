package com.company.service;

import com.company.entities.FilmEntity;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AdminAddFilmService {
    public String addFilm(FilmEntity film) throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "addFilm");
        object.put("addFilm", film);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }
}
