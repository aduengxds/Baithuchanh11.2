<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
        }

        table {
            border-collapse: collapse;
            width: 600px;
            max-width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            width: 180px;
            background: #f2f2f2;
        }
    </style>
</head>
<body>

<h1>Chi tiết sản phẩm</h1>

<table>
    <tr>
        <th>ID</th>
        <td><c:out value="${product.id}"/></td>
    </tr>
    <tr>
        <th>Tên sản phẩm</th>
        <td><c:out value="${product.name}"/></td>
    </tr>
    <tr>
        <th>Giá</th>
        <td><c:out value="${product.price}"/> VNĐ</td>
    </tr>
    <tr>
        <th>Mô tả</th>
        <td><c:out value="${product.description}"/></td>
    </tr>
    <tr>
        <th>Nhà sản xuất</th>
        <td><c:out value="${product.manufacturer}"/></td>
    </tr>
</table>

<p>
    <a href="${pageContext.request.contextPath}/products">
        Quay lại danh sách
    </a>

    <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}">
        Sửa sản phẩm
    </a>
</p>

</body>
</html>