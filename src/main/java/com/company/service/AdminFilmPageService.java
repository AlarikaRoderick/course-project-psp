package com.company.service;

import com.company.entities.FilmEntity;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AdminFilmPageService {
    public JSONObject getFilms() throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getFilms");
        SendObjectService.sendObject(object);
        return SendObjectService.getObject();
    }

    public String updateFilm(FilmEntity film) throws IOException, ClassNotFoundException{
        JSONObject object = new JSONObject();
        object.put("action", "updateFilm");
        object.put("film", film);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }

    public String deleteFilm(FilmEntity film) throws IOException, ClassNotFoundException{
        JSONObject object = new JSONObject();
        object.put("action", "deleteFilm");
        object.put("delFilm", film);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }
}
