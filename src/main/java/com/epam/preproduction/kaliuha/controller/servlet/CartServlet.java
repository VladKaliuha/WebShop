package com.epam.preproduction.kaliuha.controller.servlet;

import com.epam.preproduction.kaliuha.constant.JspPage;
import com.epam.preproduction.kaliuha.constant.WebContextKey;
import com.epam.preproduction.kaliuha.dto.CartActionDto;
import com.epam.preproduction.kaliuha.dto.mapper.CartActionDtoMapper;
import com.epam.preproduction.kaliuha.entity.bean.OrderInfoBean;
import com.epam.preproduction.kaliuha.entity.bean.mapper.OrderInfoBeanMapper;
import com.epam.preproduction.kaliuha.entity.cart.Cart;
import com.epam.preproduction.kaliuha.entity.impl.Product;
import com.epam.preproduction.kaliuha.service.order.OrderService;
import com.epam.preproduction.kaliuha.util.ContextAttributeExtractorUtil;
import com.epam.preproduction.kaliuha.util.SessionAttributeExtractorUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ShoppingServlet.class);
    private static final String CART = "cart";

    private Map<String, BiConsumer<Cart, Product>> cartAction;
    private OrderService orderService;
    private CartActionDtoMapper cartActionDtoMapper;
    private OrderInfoBeanMapper infoBeanMapper;

    @Override
    public void init() {
        LOG.info("Start init");
        ServletContext context = getServletContext();
        this.orderService = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.ORDER_SERVICE);
        this.cartActionDtoMapper = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.CART_ACTION_DTO_MAPPER);
        this.infoBeanMapper = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.ORDER_INFO_BEAN_MAPPER);
        this.cartAction = ContextAttributeExtractorUtil.getAttribute(context, WebContextKey.CART_ACTIONS);
        LOG.info("Finish init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = SessionAttributeExtractorUtil.getCart(request);
        OrderInfoBean order = infoBeanMapper.map(request);
        orderService.addOrder(order);
        cart.clear();
        response.sendRedirect(JspPage.HOME_PAGE_SERVLET);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = SessionAttributeExtractorUtil.getCart(request);
        cart.clear();
        request.getSession().setAttribute(CART, cart);
        request.setAttribute("cartItems", cart.getProductsFromCart());
        request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = SessionAttributeExtractorUtil.getCart(request);
        CartActionDto cartActionDto = cartActionDtoMapper.map(request);
        updateCart(cartActionDto.getProductId(), cartActionDto.getAction(), cart);
        request.getSession().setAttribute(CART, cart);
        request.setAttribute("cartItems", cart.getProductsFromCart());
        request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
    }

    private void updateCart(long productId, String action, Cart cart) {
        Optional<Product> processedProduct = cart.getCart().keySet().stream()
                .filter(key -> productId == key.getId())
                .findAny();
        processedProduct.ifPresent(product -> cartAction.get(action).accept(cart, product));
    }
}
