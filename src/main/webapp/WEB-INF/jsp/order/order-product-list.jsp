<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Lists</title>
    <style>
        body {
            padding: 20px;
        }

        h2 {
            text-align: center;
        }

        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            justify-items: center;
        }

        .product-card {
        	padding: 0 0 16px 0;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 100%;
            max-width: 300px;
            text-align: center;
        }

    </style>
</head>
<body>

<h2>Product List</h2>
<a href="<%= request.getContextPath() %>/customer/order-cart">View Order Cart</a>
<div class="product-grid">
    <c:forEach var="product" items="${data_products}">
        <div class="product-card">
            <h3>${product.name}</h3>
            <p>${product.type}</p>
            <p>Rp. ${product.price}</p>
            <form action="${pageContext.request.contextPath}/customer/add-to-cart?id=${ product.id }" method="POST">
	            <div>
		            <label for="quantity">Quantity:</label>
		            <input type="number" id="quantity" name="quantity" value = "1">
	       		</div>
     			<button>Add to Cart</button>
            </form>
        </div>
    </c:forEach>
</div>

</body>
</html>