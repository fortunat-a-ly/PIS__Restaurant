<%@ page import="edu.restaurant.datasource.entities.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.restaurant.datasource.entities.Meal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meal List</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
        }
    </style>
</head>
<body>
<h1>Meal List</h1>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Meal> orderList = (List<Meal>) request.getAttribute("mealList");
        for (Meal meal : orderList) { %>
    <tr>
        <td><%= meal.getName() %></td>
        <td><%= meal.getPrice() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
