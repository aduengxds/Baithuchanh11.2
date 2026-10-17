# Product Management

Ứng dụng quản lý sản phẩm sử dụng kiến trúc MVC với Java Servlet và JSP.

## Công nghệ

- Java 17
- Maven
- Jakarta Servlet 6.0
- JSP 3.1
- JSTL 3.0
- Apache Tomcat 10.1+

## Chức năng

- Hiển thị danh sách sản phẩm
- Thêm sản phẩm mới
- Cập nhật thông tin sản phẩm
- Xóa sản phẩm
- Xem chi tiết sản phẩm
- Tìm kiếm sản phẩm theo tên

## Kiến trúc MVC

- Model: Product
- Service: ProductService, ProductServiceImpl
- Controller: ProductServlet
- View: Các trang JSP trong thư mục product

## Chạy ứng dụng

Build dự án:

```bash
mvn clean package