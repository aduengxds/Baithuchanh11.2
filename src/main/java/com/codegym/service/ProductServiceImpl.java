package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> products =
            new LinkedHashMap<>();

    private static final AtomicInteger nextId = new AtomicInteger(6);

    static {
        products.put(1, new Product(
                1, "Laptop Dell Inspiron", 15990000,
                "Laptop phục vụ học tập và làm việc",
                "Dell"));

        products.put(2, new Product(
                2, "iPhone 16", 21990000,
                "Điện thoại thông minh",
                "Apple"));

        products.put(3, new Product(
                3, "Samsung Galaxy S25", 19990000,
                "Điện thoại Android cao cấp",
                "Samsung"));

        products.put(4, new Product(
                4, "Tai nghe Sony WH-1000XM5", 6990000,
                "Tai nghe chống ồn chủ động",
                "Sony"));

        products.put(5, new Product(
                5, "Bàn phím Logitech K380", 750000,
                "Bàn phím không dây nhỏ gọn",
                "Logitech"));
    }

    @Override
    public synchronized List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public synchronized Product findById(int id) {
        return products.get(id);
    }

    @Override
    public synchronized List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();

        if (name == null || name.isBlank()) {
            return findAll();
        }

        String keyword = name.trim().toLowerCase();

        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(keyword)) {
                result.add(product);
            }
        }

        return result;
    }

    @Override
    public synchronized void save(Product product) {
        int id = nextId.getAndIncrement();
        product.setId(id);
        products.put(id, product);
    }

    @Override
    public synchronized void update(int id, Product product) {
        if (products.containsKey(id)) {
            product.setId(id);
            products.put(id, product);
        }
    }

    @Override
    public synchronized void remove(int id) {
        products.remove(id);
    }
}