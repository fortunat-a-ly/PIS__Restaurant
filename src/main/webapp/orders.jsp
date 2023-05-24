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
            <li><a href="/restaurant/meals">Meals</a></li>
            <li><a href="/restaurant/logout">Log out</a></li>
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
        <th>Total Price</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Order> orderList = (List<Order>) request.getAttribute("list");
        for (Order order : orderList) { %>
    <tr>
        <td><%= order.getId() %></td>
        <td><%= order.getCustomerId() %></td>
        <td><%= order.getMeal().getName() %></td>
        <td><%= order.getMeal().getPrice() %></td>
        <td><%= order.getStatus().toString() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
