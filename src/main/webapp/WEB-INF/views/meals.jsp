<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<header>
    <nav>
        <ul>
            <li><a href="/orders">Orders</a></li>
            <li><a href="/logout">Log out</a></li>
        </ul>
    </nav>
</header>
<h1>Meal List</h1>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${list}">
        <tr>
            <td>${meal.name}</td>
            <td>${meal.price}</td>
            <td>
                <form action="/meals" method="post">
                    <input type="hidden" name="mealId" value="${meal.id}">
                    <input type="submit" value="Order">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
