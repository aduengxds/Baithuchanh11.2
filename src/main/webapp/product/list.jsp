<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            color: #222;
        }

        h1 {
            color: #1b2a7a;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background: #1b2a7a;
            color: white;
        }

        tr:nth-child(even) {
            background: #f5f5f5;
        }

        a {
            margin-right: 8px;
        }

        .search-form {
            margin-top: 20px;
        }

        input {
            padding: 8px;
            width: 250px;
        }

        button {
            padding: 8px 14px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<h1>Danh sách sản phẩm</h1>

<p>
    <a href="${pageContext.request.contextPath}/products?action=create">
        Thêm sản phẩm mới
    </a>
</p>

<form class="search-form"
      action="${pageContext.request.contextPath}/products"
      method="get">

    <input type="hidden" name="action" value="search">

    <input type="text"
           name="keyword"
           placeholder="Nhập tên sản phẩm cần tìm"
           value="<c:out value='${keyword}'/>">

    <button type="submit">Tìm kiếm</button>

    <a href="${pageContext.request.contextPath}/products">
        Xóa tìm kiếm
    </a>
</form>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Nhà sản xuất</th>
        <th>Thao tác</th>
    </tr>
    </thead>

    <tbody>
    <c:choose>
        <c:when test="${empty products}">
            <tr>
                <td colspan="5">Không tìm thấy sản phẩm phù hợp.</td>
            </tr>
        </c:when>

        <c:otherwise>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><c:out value="${product.id}"/></td>
                    <td>
                        <c:out value="${product.name}"/>
                    </td>
                    <td>
                        <c:out value="${product.price}"/> VNĐ
                    </td>
                    <td>
                        <c:out value="${product.manufacturer}"/>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/products?action=view&id=${product.id}">
                            Chi tiết
                        </a>

                        <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}">
                            Sửa
                        </a>

                        <a href="${pageContext.request.contextPath}/products?action=delete&id=${product.id}">
                            Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

</body>
</html>