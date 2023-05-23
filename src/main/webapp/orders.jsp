<%@ page import="edu.restaurant.datasource.entities.Order" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
        }
    </style>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="restaurant/meals.jsp">Shopping Cart</a></li>
            <li><a href="myServlet?command=gotomain">Main</a></li>
            <li><a href="myServlet?command=gotolibrary">Library</a></li>
            <li><a href="myServlet?command=logout">Logout</a></li>
        </ul>
    </nav>
</header>
<h1>Order List</h1>
<table>
    <thead>
    <tr>
        <th>Order ID</th>
        <th>Customer ID</th>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Total Price</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Order> orderList = (List<Order>) request.getAttribute("orderList");
        for (Order order : orderList) { %>
    <tr>
        <td><%= order.getId() %></td>
        <td><%= order.() %></td>
        <td><%= order.getQuantity() %></td>
        <td><%= order.getTotalPrice() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
