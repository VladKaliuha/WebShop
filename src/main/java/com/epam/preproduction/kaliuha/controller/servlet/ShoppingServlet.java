package com.epam.preproduction.kaliuha.controller.servlet;

import com.epam.preproduction.kaliuha.constant.JspPage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.constant.WebShopEntityField;
import com.epam.preproduction.kaliuha.controller.facade.ProductFacade;
import com.epam.preproduction.kaliuha.dto.PaginationDto;
import com.epam.preproduction.kaliuha.dto.ProductFilterDto;
import com.epam.preproduction.kaliuha.dto.ShoppingPageItems;
import com.epam.preproduction.kaliuha.dto.mapper.FilterDtoMapper;
import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;
import com.epam.preproduction.kaliuha.entity.bean.mapper.FilterBeanMapper;
import com.epam.preproduction.kaliuha.entity.cart.Cart;
import com.epam.preproduction.kaliuha.service.product.ProductService;
import com.epam.preproduction.kaliuha.util.ContextAttributeExtractorUtil;
import com.epam.preproduction.kaliuha.util.SessionAttributeExtractorUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopping")
public class ShoppingServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ShoppingServlet.class);

    private ProductFacade productFacade;
    private FilterDtoMapper dtoMapper;
    private FilterBeanMapper beanMapper;
    private ProductService productService;

    @Override
    public void init(ServletConfig servletConfig) {
        LOG.info("Start init");
        ServletContext context = servletConfig.getServletContext();
        productFacade = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.PRODUCT_FACADE);
        productService = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.PRODUCT_SERVICE);
        dtoMapper = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.FILTER_DTO_MAPPER);
        beanMapper = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.FILTER_BEAN_MAPPER);
        LOG.info("Finish init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductFilterBean filtersFromRequest = getFiltersFromRequest(request);
        PaginationDto paginationParams = getPaginationData(request);
        long pageCount = productFacade.getPageCount(filtersFromRequest, paginationParams);
        ShoppingPageItems pageItems = productFacade.getProducts(filtersFromRequest, paginationParams, getSortingRule(request));
        request.setAttribute(WebContextKey.PAGE_ITEMS, pageItems);
        request.setAttribute(WebContextKey.PAGE_COUNT, pageCount);
        request.getRequestDispatcher(JspPage.SHOPPING_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        long productId = Long.parseLong(request.getParameter("product_id"));
        Cart cart = SessionAttributeExtractorUtil.getCart(request);
        productService.getProduct(productId)
                .ifPresent(product -> cart.addProduct(product, 1));
        request.getSession().setAttribute("cart", cart);

    }

    private ProductFilterBean getFiltersFromRequest(HttpServletRequest request) {
        ProductFilterDto dto = dtoMapper.map(request);
        return beanMapper.map(dto);
    }

    private PaginationDto getPaginationData(HttpServletRequest request) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setCount(request.getParameter(WebShopEntityField.ITEMS_COUNT_JSP_PARAM));
        paginationDto.setPage((request.getParameter(WebShopEntityField.PAGE_JSP_PARAM)));
        return paginationDto;
    }

    private String getSortingRule(HttpServletRequest request) {
        return request.getParameter(WebShopEntityField.SORTING_JSP_PARAM);
    }
}
