<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h1>Login:</h1>

<form action="login.do" method="POST">
  <table>
    <tr>
      <td><input type="text" name="name" size="30" placeholder="Name"></td>
      <td><input type="Submit" value="Submit" name="Submit"></td>
    </tr>
    <tr>
      <td><input type="password" name="password" size="30" placeholder="Password"></td>
      <td>
        <form action="registry.jsp">
            <input type="Submit" value="Sign in" name="Sign in">
        </form>
      </td>
    </tr>
  </table>
</form>

</body>
</html>
