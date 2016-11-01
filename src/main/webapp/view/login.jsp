<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/main.css"/>

    <title>Login page</title>
</head>
<body>

<div class="login-page">
    <div class="container">
        <h1>Login:</h1>

        <div class="form">

            <form class="login-form" action="login" method="POST">

                <table>
                    <tr>
                        <td><input type="text" name="name" size="30"
                        <%--TODO login istead of Enter--%>
                                   placeholder="Login" value="${requestScope.name}"></td>
                        <c:if test="${empty requestScope.rightInput}">
                            <c:set var="rightInput" value="true" scope="request"/>
                        </c:if>
                        <c:choose>
                            <c:when test="${!requestScope.rightInput}">
                                <td style="color: red">
                                    The username or password is incorrect.
                                </td>
                            </c:when>
                            <c:otherwise> </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td><input type="password" name="password" size="30" placeholder="Password"></td>
                        <td><input type="Submit" value="Submit" name="Submit"></td>
                    </tr>
                </table>
                <br>

                <p class="lead">
                    Not registered?
                    <a href="registry">Sign up</a>
                </p>

            </form>
        </div>
    </div>
</div>

</body>
</html>
