package com.company.adapter;

import javafx.collections.ObservableList;

import java.util.List;

public interface Adapter<T> {
    ObservableList<T> convertFromListToObservableList(List<T> entities);
}
