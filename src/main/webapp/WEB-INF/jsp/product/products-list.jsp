<%@page import="java.util.List"%>
<%@page import="com.practice.web.model.entity.product.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Products</title>

<style>
body {
    padding: 0 64px; /* padding atas-bawah 0px, kiri-kanan 24px */
}
.links-container {
    display: flex;
    justify-content: space-between;
    width: 100%; /* Atur lebar sesuai kebutuhan */
}

.links-container a {
    text-decoration: none;
}
</style>

</head>
<body>
	<H3 align = "center">List Products</H3>
	
	<div class="links-container">
    <a href="<%= request.getContextPath() %>/admin/new-product">New Product</a>
    <a href="<%= request.getContextPath() %>/admin/download-csv">Download CSV</a>
</div>
	
	<table border="10" width="100%" align="center">
		<tr>
			<th>No</th>
			<th>Product Name</th>
			<th>Type</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
		
		<c:forEach items="${ data_products }" var="item"> 
		<tr>
			<td>${ item.id }</td>
			<td>${ item.name }</td>
			<td>${ item.type }</td>
			<td>Rp. ${ item.price }</td>
			<td align = "center">
				<a href="${pageContext.request.contextPath}/admin/detail-product?id=${ item.id }">View</a>
				<a href="${pageContext.request.contextPath}/admin/delete?id=${ item.id }">Delete</a>
			</td>
		</tr>
		</c:forEach>
		
	</table>
</body>
</html>