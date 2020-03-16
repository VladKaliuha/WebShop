package com.epam.preproduction.kaliuha.validation.impl;

import com.epam.preproduction.kaliuha.constant.ServletErrorMessage;
import com.epam.preproduction.kaliuha.constant.ValidationRegEx;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.entity.bean.RegistrationBean;
import com.epam.preproduction.kaliuha.validation.UserValidation;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationImpl implements UserValidation {

    private static final Logger LOG = Logger.getLogger(UserValidation.class);

    private Map<String, String> regexps;
    private Map<String, String> errorMessages;
    private List<String> messages;

    public UserValidationImpl() {
        createRegex();
        createErrorMessage();
    }

    @Override
    public List<String> signUpValidation(RegistrationBean bean) {
        messages = new ArrayList<>();

        Optional<String> timeout = checkTimeout(bean.getRegistrationToken());
        if (!timeout.isPresent()) {
            addErrorMessage(validateField(WebShopEntityField.NAME, bean.getFirstName()));
            addErrorMessage(validateField(WebShopEntityField.NAME, bean.getLastName()));
            addErrorMessage(validateField(WebShopEntityField.EMAIL, bean.getEmail()));
            addErrorMessage(validateField(WebShopEntityField.CUSTOMER_PASSWORD, bean.getPassword()));
            addErrorMessage(compareFields(bean.getPassword(), bean.getCpassword(), WebContextKey.DIFFERENT_PASSWORDS));
            addErrorMessage(compareFields(bean.getRegistrationToken(), bean.getCaptcha(), WebShopEntityField.CAPTCHA));
        } else {
            addErrorMessage(timeout);
        }
        return messages;
    }

    private void addErrorMessage(Optional<String> error) {
        error.ifPresent(messages::add);
    }

    private Optional<String> checkTimeout(String token) {
        Optional<String> message = Optional.empty();
        if (Objects.isNull(token)) {
            message = Optional.of(ServletErrorMessage.TIMEOUT);
            LOG.info(message);
        }
        return message;
    }

    private Optional<String> compareFields(String field, String confirmField, String error) {
        Optional<String> message = Optional.empty();
        if (!field.equals(confirmField)) {
            message = Optional.of(errorMessages.get(error));
            LOG.info(message);
        }
        return message;
    }

    private Optional<String> validateField(String fieldName, String value) {
        Optional<String> message = Optional.empty();

        String regex = regexps.get(fieldName);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            message = Optional.of(errorMessages.get(fieldName));
            LOG.info(message);
        }
        return message;
    }

    private void createErrorMessage() {
        errorMessages = new HashMap<>();

        errorMessages.put(WebShopEntityField.EMAIL, ServletErrorMessage.INVALID_EMAIL);
        errorMessages.put(WebShopEntityField.NAME, ServletErrorMessage.INVALID_NAME);
        errorMessages.put(WebShopEntityField.CUSTOMER_PASSWORD, ServletErrorMessage.INVALID_PASSWORD);
        errorMessages.put(WebShopEntityField.CAPTCHA, ServletErrorMessage.INVALID_CAPTCHA);
        errorMessages.put(WebContextKey.DIFFERENT_PASSWORDS, ServletErrorMessage.DIFFERENT_PASSWORDS);
    }

    private void createRegex() {
        regexps = new HashMap<>();

        regexps.put(WebShopEntityField.EMAIL, ValidationRegEx.EMAIL);
        regexps.put(WebShopEntityField.NAME, ValidationRegEx.NAME);
        regexps.put(WebShopEntityField.CUSTOMER_PASSWORD, ValidationRegEx.PASSWORD);
    }
}
