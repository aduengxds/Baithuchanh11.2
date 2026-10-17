<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>
</head>
<body>

<h1>Thêm sản phẩm mới</h1>

<c:if test="${not empty error}">
    <p style="color: red">
        <c:out value="${error}"/>
    </p>
</c:if>

<form action="${pageContext.request.contextPath}/products?action=create"
      method="post">

    <p>
        <label for="name">Tên sản phẩm:</label><br>
        <input id="name" type="text" name="name"
               value="<c:out value='${product.name}'/>" required>
    </p>

    <p>
        <label for="price">Giá sản phẩm (VNĐ):</label><br>
        <input id="price" type="number" name="price"
               min="0.01" step="0.01"
               value="${product.price > 0 ? product.price : ''}" required>
    </p>

    <p>
        <label for="description">Mô tả:</label><br>
        <textarea id="description" name="description"
                  rows="4" cols="40" required><c:out value="${product.description}"/></textarea>
    </p>

    <p>
        <label for="manufacturer">Nhà sản xuất:</label><br>
        <input id="manufacturer" type="text" name="manufacturer"
               value="<c:out value='${product.manufacturer}'/>" required>
    </p>

    <button type="submit">Lưu sản phẩm</button>
</form>

<p>
    <a href="${pageContext.request.contextPath}/products">
        Quay lại danh sách
    </a>
</p>

</body>
</html>