package com.epam.preproduction.kaliuha.entity.bean;

import com.epam.preproduction.kaliuha.entity.impl.OrderedProduct;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class OrderInfoBean {

    private static final String CARD_REGEX = "\\d{16}";

    private String payment;
    private String delivery;
    private String card;
    private String status;
    private String dateTime;
    private long customerId;
    private List<OrderedProduct> orderedProducts;

    public String getInfo() {
        return getPayment() + getDelivery() + getCard();
    }

    public String getPayment() {
        if (StringUtils.isNotBlank(payment)) {
            return "Payment: " + payment;
        } else {
            return StringUtils.EMPTY;
        }
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDelivery() {
        if (StringUtils.isNotBlank(payment)) {
            return "Delivery: " + delivery;
        } else {
            return StringUtils.EMPTY;
        }
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getCard() {
        if (StringUtils.isNotBlank(payment)) {
            return "Card: " + card;
        } else {
            return StringUtils.EMPTY;
        }
    }

    public void setCard(String card) {
        if (card.matches(CARD_REGEX)) {
            this.card = card;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
