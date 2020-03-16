package com.epam.preproduction.kaliuha.entity.impl;

import com.epam.preproduction.kaliuha.entity.Entity;
import com.epam.preproduction.kaliuha.entity.bean.RegistrationBean;

import java.util.Objects;

public class User extends Entity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean mailing;
    private String icon;

    public User(long id, String firstName, String lastName, String email, String password, boolean mailing, String icon) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mailing = mailing;
        this.icon = icon;
    }

    public User(RegistrationBean bean) {
        this.firstName = bean.getFirstName();
        this.lastName = bean.getLastName();
        this.email = bean.getEmail();
        this.password = bean.getPassword();
        this.mailing = bean.isMailing();
        this.icon = bean.getIcon();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMailing() {
        return mailing;
    }

    public void setMailing(boolean mailing) {
        this.mailing = mailing;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return mailing == user.mailing &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                Objects.equals(icon, user.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, password, mailing, icon);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mailing=" + mailing +
                '}';
    }
}
