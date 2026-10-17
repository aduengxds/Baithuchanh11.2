package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null || action.isBlank() || action.equals("list")) {
            listProducts(request, response);
            return;
        }

        switch (action) {
            case "create":
                request.getRequestDispatcher("/product/create.jsp")
                        .forward(request, response);
                break;

            case "edit":
                showProduct(request, response, "edit");
                break;

            case "delete":
                showProduct(request, response, "delete");
                break;

            case "view":
                showProduct(request, response, "view");
                break;

            case "search":
                searchProducts(request, response);
                break;

            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        switch (action) {
            case "create":
                createProduct(request, response);
                break;

            case "edit":
                updateProduct(request, response);
                break;

            case "delete":
                deleteProduct(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

    private void listProducts(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("products", productService.findAll());

        request.getRequestDispatcher("/product/list.jsp")
                .forward(request, response);
    }

    private void searchProducts(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        List<Product> products = productService.searchByName(keyword);

        request.setAttribute("products", products);
        request.setAttribute("keyword", keyword == null ? "" : keyword);

        request.getRequestDispatcher("/product/list.jsp")
                .forward(request, response);
    }

    private void showProduct(HttpServletRequest request,
                             HttpServletResponse response,
                             String action)
            throws ServletException, IOException {

        Product product = getProduct(request);

        if (product == null) {
            showNotFound(request, response);
            return;
        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/product/" + action + ".jsp")
                .forward(request, response);
    }

    private Product getProduct(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            return productService.findById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void createProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        Product product = readProduct(request);

        String error = validateProduct(product);

        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("product", product);

            request.getRequestDispatcher("/product/create.jsp")
                    .forward(request, response);
            return;
        }

        productService.save(product);

        response.sendRedirect(
                request.getContextPath() + "/products");
    }

    private void updateProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        Product existing = getProduct(request);

        if (existing == null) {
            showNotFound(request, response);
            return;
        }

        Product product = readProduct(request);
        String error = validateProduct(product);

        if (error != null) {
            product.setId(existing.getId());

            request.setAttribute("product", product);
            request.setAttribute("error", error);

            request.getRequestDispatcher("/product/edit.jsp")
                    .forward(request, response);
            return;
        }

        productService.update(existing.getId(), product);

        response.sendRedirect(
                request.getContextPath() + "/products");
    }

    private void deleteProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        Product product = getProduct(request);

        if (product == null) {
            showNotFound(request, response);
            return;
        }

        productService.remove(product.getId());

        response.sendRedirect(
                request.getContextPath() + "/products");
    }

    private Product readProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        String priceText = request.getParameter("price");
        String description = request.getParameter("description");
        String manufacturer = request.getParameter("manufacturer");

        double price = 0;

        try {
            if (priceText != null && !priceText.isBlank()) {
                price = Double.parseDouble(priceText);
            }
        } catch (NumberFormatException e) {
            price = -1;
        }

        return new Product(
                0,
                name == null ? "" : name.trim(),
                price,
                description == null ? "" : description.trim(),
                manufacturer == null ? "" : manufacturer.trim()
        );
    }

    private String validateProduct(Product product) {
        if (product.getName().isBlank()) {
            return "Tên sản phẩm không được để trống.";
        }

        if (product.getPrice() <= 0) {
            return "Giá sản phẩm phải lớn hơn 0.";
        }

        if (product.getDescription().isBlank()) {
            return "Mô tả sản phẩm không được để trống.";
        }

        if (product.getManufacturer().isBlank()) {
            return "Nhà sản xuất không được để trống.";
        }

        return null;
    }

    private void showNotFound(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        request.getRequestDispatcher("/error-404.jsp")
                .forward(request, response);
    }
}