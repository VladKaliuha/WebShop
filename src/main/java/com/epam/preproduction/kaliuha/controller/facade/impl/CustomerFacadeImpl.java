package com.epam.preproduction.kaliuha.controller.facade.impl;

import com.epam.preproduction.kaliuha.constant.ServletErrorMessage;
import com.epam.preproduction.kaliuha.controller.facade.CustomerFacade;
import com.epam.preproduction.kaliuha.entity.bean.RegistrationBean;
import com.epam.preproduction.kaliuha.entity.impl.User;
import com.epam.preproduction.kaliuha.file.FileHelper;
import com.epam.preproduction.kaliuha.service.customer.UserService;
import com.epam.preproduction.kaliuha.validation.UserValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerFacadeImpl implements CustomerFacade {

    private static final Logger LOG = Logger.getLogger(CustomerFacadeImpl.class);

    private static final String CONTENT_TYPE_REGEX = "/";
    private static final String DOT = ".";
    private static final String FILE_SAVE_EXCEPTION = "File cannot be saved";
    private static final String SAVED_DIRECTORY = "/customersIcon/";
    private static final String DEFAULT_USER_ICON = "/customersIcon/defaultUserIcon.png";
    private static final String IMAGE = "image";
    private UserService userService;
    private UserValidation userValidation;

    public CustomerFacadeImpl(UserService userService, UserValidation userValidation) {
        this.userService = userService;
        this.userValidation = userValidation;
    }

    @Override
    public List<String> registration(RegistrationBean bean, final Part filePart) {
        List<String> errorMessages = userValidation.signUpValidation(bean);
        emailAlreadyExist(bean.getEmail(), errorMessages);
        if (errorMessages.isEmpty()) {
            if (iconPresent(filePart)) {
                try {
                    String icon = SAVED_DIRECTORY + bean.getEmail() + DOT + filePart.getContentType().split(CONTENT_TYPE_REGEX)[1];
                    FileHelper.readFileFromServlet(filePart, icon);
                    bean.setIcon(icon);
                } catch (IOException e) {
                    LOG.warn(FILE_SAVE_EXCEPTION);
                }
            } else {
                bean.setIcon(DEFAULT_USER_ICON);
            }
            userService.add(new User(bean));
        }
        return errorMessages;
    }

    private boolean iconPresent(Part filePart) {
        return filePart.getContentType().split(CONTENT_TYPE_REGEX)[0].equals(IMAGE);
    }

    @Override
    public Optional<User> login(String email, String password) {
        return userService.getUser(email)
                .filter(user -> user.getPassword().equals(password));
    }

    private void emailAlreadyExist(String email, List<String> errorMessages) {
        userService.getUser(email).ifPresent(user -> errorMessages.add(ServletErrorMessage.MAIL_ALREADY_EXIST));
    }
}
