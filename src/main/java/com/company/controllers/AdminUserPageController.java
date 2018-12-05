package com.company.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.company.adapter.UserAdapter;
import com.company.dao.user.UserService;
import com.company.entities.UserEntity;
import com.company.entities.current.CurrentUserEntity;
import com.company.service.AdminUserPageService;
import com.company.service.ChangeWindow;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONObject;

public class AdminUserPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView userTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn userNameColumn;

    @FXML
    private TableColumn userSurnameColumn;

    @FXML
    private TableColumn userAgeColumn;

    @FXML
    private TableColumn userLoginColumn;

    @FXML
    private TableColumn userPasswordColumn;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField userSurnameField;

    @FXML
    private TextField userAgeField;

    @FXML
    private TextField userLoginField;

    @FXML
    private Button updateUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button addNewUserButton;

    @FXML
    private Button findUserOrderButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField userPasswordField;

    private UserAdapter userAdapter = new UserAdapter();
    private AdminUserPageService adminUserPageService = new AdminUserPageService();
    private ChangeWindow changeWindow = new ChangeWindow();

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        idColumn.setCellValueFactory(new PropertyValueFactory<UserEntity, Integer>("id_user"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<UserEntity, String>("userName"));
        userSurnameColumn.setCellValueFactory(new PropertyValueFactory<UserEntity, String>("userSurname"));
        userAgeColumn.setCellValueFactory(new PropertyValueFactory<UserEntity, Integer>("userAge"));
        userLoginColumn.setCellValueFactory(new PropertyValueFactory<UserEntity, String>("userLogin"));
        userPasswordColumn.setCellValueFactory(new PropertyValueFactory<UserEntity, String>("userPassword"));
        JSONObject object = adminUserPageService.getUsers();
        List<UserEntity> users = (List<UserEntity>) object.get("userList");
        ObservableList<UserEntity> userEntities = userAdapter.convertFromListToObservableList(users);
        userTable.setItems(userEntities);
        initClick();
    }

    private void initClick() {
        userTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 1){
                    String userName = ((UserEntity)userTable.getSelectionModel().getSelectedItem()).getUserName();
                    String userSurname = ((UserEntity)userTable.getSelectionModel().getSelectedItem()).getUserSurname();
                    int userAge = ((UserEntity)userTable.getSelectionModel().getSelectedItem()).getUserAge();
                    String userLogin = ((UserEntity)userTable.getSelectionModel().getSelectedItem()).getUserLogin();
                    String userPassword = ((UserEntity)userTable.getSelectionModel().getSelectedItem()).getUserPassword();
                    setTextFields(userName, userSurname, userAge, userLogin, userPassword);
                }
            }
        });
    }

    public void updateUser(){
        UserEntity user = createUser();
        try{
            String request = adminUserPageService.updateUser(user);
            if (request.equals("successfulUpdate")){
                System.out.println("Пользователь обновлен");
                clearFields();
                initialize();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(){
        UserEntity user = createUser();
        try{
            String request = adminUserPageService.deleteUser(user);
            if(request.equals("successfulDelete")){
                System.out.println("Пользователь удален");
                clearFields();
                initialize();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void addUser(){
        String userName = userNameField.getText();
        String userSurname = userSurnameField.getText();
        int userAge = Integer.parseInt(userAgeField.getText());
        String userLogin = userLoginField.getText();
        String userPassword = userPasswordField.getText();

        UserEntity user = new UserEntity(userName, userSurname, userAge, userLogin, userPassword, false);
        try{
            String request = adminUserPageService.addUser(user);
            if(request.equals("successfulAdd")){
                System.out.println("Пользователь добавлен");
                clearFields();
                initialize();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void back() throws IOException{
        changeWindow.changeWindow(backButton, "/fxml/adminFirstPage.fxml");
    }

    public void goToUserOrder() throws IOException{
        UserEntity user = createUser();
        CurrentUserEntity.setUser(user);
        changeWindow.changeWindow(findUserOrderButton, "/fxml/adminUserOrderPage.fxml");
    }

    private void clearFields() {
        userNameField.clear();
        userSurnameField.clear();
        userAgeField.clear();
        userLoginField.clear();
        userPasswordField.clear();
    }

    private UserEntity createUser() {
        int id = ((UserEntity)userTable.getSelectionModel().getSelectedItem()).getId_user();
        String userName = userNameField.getText();
        String userSurname = userSurnameField.getText();
        int userAge = Integer.parseInt(userAgeField.getText());
        String userLogin = userLoginField.getText();
        String userPassword = userPasswordField.getText();
        UserEntity user = new UserEntity(userName, userSurname, userAge, userLogin, userPassword, false);
        user.setId_user(id);
        return user;
    }

    private void setTextFields(String userName, String userSurname, int userAge, String userLogin, String userPassword) {
        userNameField.setText(userName);
        userSurnameField.setText(userSurname);
        userAgeField.setText(String.valueOf(userAge));
        userLoginField.setText(userLogin);
        userPasswordField.setText(userPassword);
    }
}
