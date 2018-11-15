package com.company.service;

import com.company.entities.FilmEntity;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AdminFilmPageService {
    public ObservableList<FilmEntity> getFilms() throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "getFilms");
        SendObjectService.sendObject(object);
        return (ObservableList<FilmEntity>) SendObjectService.getObject();
    }
}
