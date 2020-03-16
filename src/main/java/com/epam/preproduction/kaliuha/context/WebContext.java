package com.epam.preproduction.kaliuha.context;

import com.epam.preproduction.kaliuha.captcha.factory.impl.CaptchaTokenFactoryImpl;
import com.epam.preproduction.kaliuha.captcha.service.strategy.CaptchaService;
import com.epam.preproduction.kaliuha.captcha.service.strategy.impl.HiddenFieldService;
import com.epam.preproduction.kaliuha.captcha.storage.CaptchaStorage;
import com.epam.preproduction.kaliuha.captcha.storage.cleaner.StorageCleaner;
import com.epam.preproduction.kaliuha.constant.JspPage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.controller.facade.impl.CustomerFacadeImpl;
import com.epam.preproduction.kaliuha.controller.facade.impl.ProductFacadeImpl;
import com.epam.preproduction.kaliuha.dao.category.impl.CategoryDaoImpl;
import com.epam.preproduction.kaliuha.dao.fabricator.impl.FabricatorDaoImpl;
import com.epam.preproduction.kaliuha.dao.order.impl.OrderDaoImpl;
import com.epam.preproduction.kaliuha.dao.product.impl.ProductDaoDB;
import com.epam.preproduction.kaliuha.dao.user.impl.UserDaoDB;
import com.epam.preproduction.kaliuha.dataBase.connector.DBConnectionManager;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.JdbcTemplate;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.impl.JdbcTemplateImpl;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.impl.CategoryMapper;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.impl.FabricatorMapper;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.impl.ProductMapper;
import com.epam.preproduction.kaliuha.dataBase.jdbcTemplate.mapper.impl.UserMapper;
import com.epam.preproduction.kaliuha.dataBase.transaction.TransactionManager;
import com.epam.preproduction.kaliuha.dataBase.transaction.TransactionManagerSql;
import com.epam.preproduction.kaliuha.dto.mapper.CartActionDtoMapper;
import com.epam.preproduction.kaliuha.dto.mapper.FilterDtoMapper;
import com.epam.preproduction.kaliuha.entity.bean.mapper.FilterBeanMapper;
import com.epam.preproduction.kaliuha.entity.bean.mapper.OrderInfoBeanMapper;
import com.epam.preproduction.kaliuha.entity.cart.Cart;
import com.epam.preproduction.kaliuha.entity.impl.Product;
import com.epam.preproduction.kaliuha.filter.localization.strategy.container.LocaleStorageStrategyContainer;
import com.epam.preproduction.kaliuha.service.Service;
import com.epam.preproduction.kaliuha.service.category.CategoryService;
import com.epam.preproduction.kaliuha.service.category.impl.CategoryServiceImpl;
import com.epam.preproduction.kaliuha.service.customer.UserService;
import com.epam.preproduction.kaliuha.service.customer.impl.UserServiceImpl;
import com.epam.preproduction.kaliuha.service.fabricator.FabricatorService;
import com.epam.preproduction.kaliuha.service.fabricator.impl.FabricatorServiceImpl;
import com.epam.preproduction.kaliuha.service.order.impl.OrderServiceImpl;
import com.epam.preproduction.kaliuha.service.product.ProductService;
import com.epam.preproduction.kaliuha.service.product.impl.ProductServiceImpl;
import com.epam.preproduction.kaliuha.validation.UserValidation;
import com.epam.preproduction.kaliuha.validation.impl.UserValidationImpl;
import com.github.cage.GCage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@WebListener
public class WebContext implements ServletContextListener {

    private UserValidation userValidation;
    private Map<String, Service> services;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();

        validationInitialized(context);
        serviceInitialized(context);
        tokenFactoryInitialized(context);
        captchaServiceInitialized(context);
        servletFacadeInitialized(context);
        mappersInitialized(context);
        initCartActions(context);
    }

    private void initCartActions(ServletContext context) {
        Map<String, BiConsumer<Cart, Product>> cartAction = new HashMap<>();
        cartAction.put(JspPage.CART_ACTION_PLUS, (cart, product) -> cart.addProduct(product, 1));
        cartAction.put(JspPage.CART_ACTION_MINUS, (cart, product) -> cart.addProduct(product, -1));
        cartAction.put(JspPage.CART_ACTION_DELETE, Cart::removeProduct);
        context.setAttribute(WebContextKey.CART_ACTIONS, cartAction);
    }

    private void mappersInitialized(ServletContext context) {
        context.setAttribute(WebContextKey.FILTER_DTO_MAPPER, new FilterDtoMapper());
        context.setAttribute(WebContextKey.FILTER_BEAN_MAPPER, new FilterBeanMapper());
        context.setAttribute(WebContextKey.CART_ACTION_DTO_MAPPER, new CartActionDtoMapper());
        context.setAttribute(WebContextKey.ORDER_INFO_BEAN_MAPPER, new OrderInfoBeanMapper());
        localeStorageStrategyContainer(context);
    }

    private void localeStorageStrategyContainer(ServletContext context) {
        context.setAttribute(WebContextKey.LOCALE_STRATEGY_CONTAINER, new LocaleStorageStrategyContainer());
    }

    private void servletFacadeInitialized(ServletContext context) {
        context.setAttribute(WebContextKey.CUSTOMER_FACADE, new CustomerFacadeImpl((UserService) services.get(WebContextKey.USER_SERVICE), userValidation));
        context.setAttribute(WebContextKey.PRODUCT_FACADE, new ProductFacadeImpl(
                (ProductService) services.get(WebContextKey.PRODUCT_SERVICE),
                (FabricatorService) services.get(WebContextKey.FABRICATOR_SERVICE),
                (CategoryService) services.get(WebContextKey.CATEGORY_SERVICE)
        ));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void validationInitialized(ServletContext context) {
        userValidation = new UserValidationImpl();
        context.setAttribute(WebContextKey.USER_VALIDATION, userValidation);
    }

    private void captchaServiceInitialized(ServletContext context) {
        CaptchaStorage captchaStorage = new CaptchaStorage();

        CaptchaService captchaService = new HiddenFieldService(captchaStorage);
        context.setAttribute(WebContextKey.CAPTCHA_SERVICE, captchaService);
        context.setAttribute(WebContextKey.CAPTCHA_STORAGE, captchaStorage);
        context.setAttribute(WebContextKey.CAPTCHA_CREATOR, new GCage());
        StorageCleaner cleaner = new StorageCleaner(context);
        cleaner.setDaemon(true);
        cleaner.start();
    }

    private void serviceInitialized(ServletContext context) {
        DBConnectionManager connectionManager = new DBConnectionManager(new ThreadLocal<>());
        JdbcTemplate jdbcTemplate = new JdbcTemplateImpl(connectionManager);
        TransactionManager transactionManager = new TransactionManagerSql(connectionManager);
        services = new HashMap<>();
        services.put(WebContextKey.USER_SERVICE, new UserServiceImpl(
                new UserDaoDB(jdbcTemplate, new UserMapper()), transactionManager));
        services.put(WebContextKey.PRODUCT_SERVICE, new ProductServiceImpl(
                new ProductDaoDB(jdbcTemplate, new ProductMapper()), transactionManager
        ));
        services.put(WebContextKey.CATEGORY_SERVICE, new CategoryServiceImpl(
                new CategoryDaoImpl(jdbcTemplate, new CategoryMapper())
        ));
        services.put(WebContextKey.FABRICATOR_SERVICE, new FabricatorServiceImpl(
                new FabricatorDaoImpl(jdbcTemplate, new FabricatorMapper())
        ));
        services.put(WebContextKey.ORDER_SERVICE, new OrderServiceImpl(
                new OrderDaoImpl(jdbcTemplate),
                transactionManager));
        context.setAttribute(WebContextKey.USER_SERVICE, services.get(WebContextKey.USER_SERVICE));
        context.setAttribute(WebContextKey.PRODUCT_SERVICE, services.get(WebContextKey.PRODUCT_SERVICE));
        context.setAttribute(WebContextKey.CATEGORY_SERVICE, services.get(WebContextKey.CATEGORY_SERVICE));
        context.setAttribute(WebContextKey.FABRICATOR_SERVICE, services.get(WebContextKey.FABRICATOR_SERVICE));
        context.setAttribute(WebContextKey.ORDER_SERVICE, services.get(WebContextKey.ORDER_SERVICE));
    }

    private void tokenFactoryInitialized(ServletContext context) {
        context.setAttribute(WebContextKey.TOKEN_FACTORY, new CaptchaTokenFactoryImpl());
    }
}
