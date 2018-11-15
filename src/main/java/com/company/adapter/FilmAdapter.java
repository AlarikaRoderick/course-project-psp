package com.company.adapter;

import com.company.entities.FilmEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class FilmAdapter implements Adapter<FilmEntity> {
    @Override
    public ObservableList<FilmEntity> convertFromListToObservableList(List<FilmEntity> entities) {
        return FXCollections.observableList(entities);
    }
}
