package com.epam.preproduction.kaliuha.controller.facade;

import com.epam.preproduction.kaliuha.entity.bean.RegistrationBean;
import com.epam.preproduction.kaliuha.entity.impl.User;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface CustomerFacade {

    List<String> registration(RegistrationBean bean, Part filePart);

    Optional<User> login(String email, String password);
}
