package com.epam.preproduction.kaliuha.dto;

import org.apache.log4j.Logger;

import java.util.Objects;

public class CartActionDto {

    private final static Logger LOG = Logger.getLogger(CartActionDto.class);

    private String action;
    private long productId;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        try {
            this.productId = Long.parseLong(productId);
        } catch (NumberFormatException e) {
            LOG.warn("Product id isn't correct. Set default exception (-1)");
            this.productId = -1;
        }
    }

    @Override
    public String toString() {
        return "CartActionDto{" +
                "action='" + action + '\'' +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartActionDto that = (CartActionDto) o;
        return productId == that.productId &&
                Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, productId);
    }
}
