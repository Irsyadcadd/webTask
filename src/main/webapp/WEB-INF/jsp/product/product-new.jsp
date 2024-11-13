<%@ page import="com.practice.web.model.entity.product.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Product</title>
</head>
<body>
    <h3 align="center">Create New Product</h3>

    <form action="${pageContext.request.contextPath}/admin/new-product" method="POST">
	    <table border="0" width="20%" align="center">
	        <tr>
	            <td><label for="name"><strong>Name</strong></label></td>
	            <td>: <input type="text" id="name" name="name"></td>
	        </tr>
	        <tr>
	            <td><label for="type"><strong>Type</strong></label></td>
	            <td>: 
	                <select id="type" name="type">
					    <c:forEach items="${ product_types }" var="type">
					        <option value="${type}">${type}</option>
					    </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td><label for="price"><strong>Price</strong></label></td>
	            <td>: Rp. <input type="number" id="price" name="price"></td>
	        </tr>
	        <tr>
	            <td colspan="2" align="center">
	                <button type="submit">Save</button>
	            </td>
	        </tr>
	    </table>
    </form>
</body>
</html>