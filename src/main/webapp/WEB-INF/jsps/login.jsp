<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/login.css" type="text/css" rel="stylesheet"/>
    <%--???--%>
</head>
<body>


<div class="login-page">
    <div class="container">
        <h1>Login:</h1>

        <div class="form">

            <form class="login-form" action="login.do" method="POST">
                <table>
                    <tr>
                        <td><input type="text" name="name" size="30" placeholder="Name"></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="password" size="30" placeholder="Password"></td>
                        <td><input type="Submit" value="Submit" name="Submit"></td>
                    </tr>
                </table>
                </br>
                <p class="message">Not registered? <%--<a href="#">Create an account</a>--%></p>
                <tr>
                    <td>
                        <form action="registry.jsp">
                            <input type="button" value="go to registry" name="go to registry">
                        </form>
                    </td>
                </tr>
            </form>

        </div>

    </div>
</div>

<%--
<h2>Log-in to your account</h2>
<form class="modal-content" action="login.do" method="POST">
    <div class="container">

        <label><b>Username</b></label>

        <<input type="text" name="name" size="30" placeholder="Name">

        <label><b>Password</b></label>

        <input type="password" name="password" size="30" placeholder="Password">

        <<input class="btn btn-default" type="Submit" value="Log-in" name="Submit">

    </div>
</form>
<div class="container">
    New to us?
    <form action="registry.jsp">
        <input class="btn btn-default" type="Submit" value="Sign in" name="Sign in">
        <%--<button type="Submit" value="Sign in" name="Sign in">--%>

</body>
</html>
