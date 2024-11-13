<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Cart</title>
    <style>
		body {
		    padding: 0 64px; /* padding atas-bawah 0px, kiri-kanan 24px */
		}
	</style>
</head>
<body>
	<a href="${pageContext.request.contextPath}/customer/order-product-list">back</a>

    <h4>Customer : ${ order.user.username }</h4>
    <h4>Address  : ${ order.user.address }</h4>

    <table border="10" width="100%" align="center">
        <tr>
            <th>Name</th>
            <th>Price</th>
			<th>Qty</th>
			<th>Total</th>
            <th>Quantity</th>
            <th>Total Price</th>
        </tr>

        <c:forEach var="item" items="${order.items}">
            <tr>
                <td>${ item.product.name }</td>
                <td>${ item.product.name }</td>
				<td>${ item.product.type }</td>
				<td>Rp. ${ item.product.price }</td>
                <td>${ item.quantity }</td>
                <td>Rp. ${item.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>

    <h3 align="right">Total Price: Rp. ${order.totalPrice}</h3>

    <form align="right" action="${pageContext.request.contextPath}/customer/add-order" method="post">
        <button type="submit">Place Order</button>
    </form>

</body>
</html>