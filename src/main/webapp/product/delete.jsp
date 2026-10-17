<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Xóa sản phẩm</title>
</head>
<body>

<h1>Xác nhận xóa sản phẩm</h1>

<p>Bạn có chắc chắn muốn xóa sản phẩm này không?</p>

<ul>
    <li>ID: <c:out value="${product.id}"/></li>
    <li>Tên: <c:out value="${product.name}"/></li>
    <li>Giá: <c:out value="${product.price}"/> VNĐ</li>
    <li>Nhà sản xuất: <c:out value="${product.manufacturer}"/></li>
</ul>

<form action="${pageContext.request.contextPath}/products?action=delete"
      method="post">

    <input type="hidden" name="id" value="${product.id}">

    <button type="submit">Xác nhận xóa</button>

    <a href="${pageContext.request.contextPath}/products">
        Hủy
    </a>
</form>

</body>
</html>