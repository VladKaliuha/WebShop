package com.epam.preproduction.kaliuha.service.order;

import com.epam.preproduction.kaliuha.entity.bean.OrderInfoBean;
import com.epam.preproduction.kaliuha.service.Service;

public interface OrderService extends Service {

    void addOrder(OrderInfoBean orderInfoBean);
}
