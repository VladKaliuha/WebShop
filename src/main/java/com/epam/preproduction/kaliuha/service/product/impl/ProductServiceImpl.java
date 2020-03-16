package com.epam.preproduction.kaliuha.service.product.impl;

import com.epam.preproduction.kaliuha.dto.PaginationDto;
import com.epam.preproduction.kaliuha.dao.product.ProductDao;
import com.epam.preproduction.kaliuha.dataBase.transaction.TransactionManager;
import com.epam.preproduction.kaliuha.entity.bean.ProductFilterBean;
import com.epam.preproduction.kaliuha.entity.impl.Product;
import com.epam.preproduction.kaliuha.service.product.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private TransactionManager transactionManager;

    public ProductServiceImpl(ProductDao productDao, TransactionManager transactionManager) {
        this.productDao = productDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public Optional<Product> getProduct(long id) {
        return productDao.getProduct(id);
    }

    @Override
    public List<Product> getAllProducts(ProductFilterBean filterBean, PaginationDto paginationParam, String sortingRule) {
        int itemsCount = paginationParam.getCount();
        int start = (paginationParam.getPage() - 1) * itemsCount;
        return productDao.getAllProducts(filterBean)
                .sortBy(sortingRule)
                .skip(start)
                .number(itemsCount)
                .result();
    }

    @Override
    public Long getProductsCount(ProductFilterBean filterBean) {
        return productDao.getProductsCount(filterBean);
    }

    @Override
    public void addProduct(Product product) {
        transactionManager.doTransaction(() -> productDao.addProduct(product));
    }
}
