package com.epam.preproduction.kaliuha.validation;

import com.epam.preproduction.kaliuha.entity.bean.RegistrationBean;

import java.util.List;

public interface UserValidation {

    List<String> signUpValidation(RegistrationBean bean);
}
