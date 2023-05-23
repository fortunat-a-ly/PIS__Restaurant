<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<header>
  <nav>
    <ul>
      <li><a href="meals.jsp">Meals</a></li>
      <li><a href="registration.jsp">Register</a></li>
    </ul>
  </nav>
</header>
<body>
<form action="<%= request.getContextPath() %>/login.jsp" method="post">
  <table>
    <tr>
      <td>Email</td>
      <td><input type="email" name="email" /></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" name="password" /></td>
    </tr>
  </table>
  <input type="submit" value="Submit" />
</form>
</body>
</html>