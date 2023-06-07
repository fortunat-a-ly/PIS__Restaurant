<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <li><a href="/meals">Meals</a></li>
            <li><a href="/logout">Log out</a></li>
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
    <c:forEach var="order" items="${list}">
    <tr>
        <td>${order.id}</td>
        <td>${order.customerId}</td>
        <td>${order.meal.name}</td>
        <td>${order.meal.price}</td>
        <td>${order.status}</td>
        <td>
            <form action="/orders" method="post">
                <input type="hidden" name="orderId" value="${order.id}">
                <input type="submit" value="Ready">
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
