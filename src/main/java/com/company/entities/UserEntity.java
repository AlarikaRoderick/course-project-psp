package com.company.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_surname")
    private String userSurname;
    @Column(name = "user_age")
    private int userAge;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "user_password")
    private String userPassword;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orderEntities;

    public void addOrder(OrderEntity order){
        order.setUser(this);
        orderEntities.add(order);
    }

    public void removeOrder(OrderEntity order){
        orderEntities.remove(order);
    }

    public List<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(List<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id_user != that.id_user) return false;
        if (userAge != that.userAge) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userSurname != null ? !userSurname.equals(that.userSurname) : that.userSurname != null) return false;
        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_user;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userSurname != null ? userSurname.hashCode() : 0);
        result = 31 * result + userAge;
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        return result;
    }
}
