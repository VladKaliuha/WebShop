package com.epam.preproduction.kaliuha.entity.bean;

import java.util.Objects;

public class RegistrationBean {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cpassword;
    private String captcha;
    private boolean mailing;
    private String registrationToken;
    private String icon;

    public RegistrationBean(String firstName, String lastName, String email, String password, String cpassword, String captcha, boolean mailing, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
        this.mailing = mailing;
        this.captcha = captcha;
        this.registrationToken = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public String getCaptcha() {
        return captcha;
    }

    public boolean isMailing() {
        return mailing;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "RegistrationBean{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cpassword='" + cpassword + '\'' +
                ", captcha='" + captcha + '\'' +
                ", mailing=" + mailing +
                ", registrationToken='" + registrationToken + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationBean bean = (RegistrationBean) o;
        return mailing == bean.mailing &&
                Objects.equals(firstName, bean.firstName) &&
                Objects.equals(lastName, bean.lastName) &&
                Objects.equals(email, bean.email) &&
                Objects.equals(password, bean.password) &&
                Objects.equals(cpassword, bean.cpassword) &&
                Objects.equals(captcha, bean.captcha) &&
                Objects.equals(registrationToken, bean.registrationToken) &&
                Objects.equals(icon, bean.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, cpassword, captcha, mailing, registrationToken, icon);
    }
}
