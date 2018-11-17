package com.company.adapter;

import com.company.entities.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserAdapter implements Adapter<UserEntity> {
    @Override
    public ObservableList<UserEntity> convertFromListToObservableList(List<UserEntity> entities) {
        return FXCollections.observableList(entities);
    }
}
