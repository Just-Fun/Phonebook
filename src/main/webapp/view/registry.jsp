<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
  <%--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/main.css"/>

    <title>Sign in</title>
</head>
<body>
<div class="registry-page">
<form action="registry" method="POST">
    <table>
        <tr>
           <%-- <td style="color: black">
                User name &lt;%&ndash;${requestScope.name}&ndash;%&gt;
            </td>--%>
            <td><input type="text" name="name" size="30"
                       placeholder="Enter name" value="${requestScope.name}"></td>
            <c:if test="${empty requestScope.validUserName}">
                <c:set var="validUserName" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.emptyUserName}">
                    <td style="color: red">
                        User name can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.validUserName}">
                    <td style="color: red">
                        User's name must be at least 3 characters and consist only letters.
                    </td>
                </c:when>
                <c:when test="${requestScope.userExist}">
                    <td style="color: red">
                        The user with specified name already exists! Please, change the user's name.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        User name <%--${requestScope.name}--%>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
           <%-- <td style="color: black">
                Password
            </td>--%>
            <td><input type="password" name="password" size="30"
                       placeholder="Enter password" value="${requestScope.password}"></td>
            <c:if test="${empty requestScope.correctPassword}">
                <c:set var="correctPassword" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.emptyPassword}">
                    <td style="color: red">
                        Password can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.correctPassword}">
                    <td style="color: red">
                        Passwords must be at least 5 characters and contain only letter or digits or "_".
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        Password
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
           <%-- <td style="color: black">
                Confirm password
            </td>--%>

            <td><input type="password" name="confirmPassword" size="30"
                       placeholder="Confirm password" value="${requestScope.confirmPassword}"></td>
            <c:if test="${empty requestScope.passwordsMatch}">
                <c:set var="passwordsMatch" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.emptyConfirmPassword}">
                    <td style="color: red">
                        This field can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.passwordsMatch}">
                    <td style="color: red">
                        Passwords do not match!
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        Confirm password
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td><input type="submit" name="Submit"></td>
        </tr>
    </table>

    Already registered? <br>

    <a href="login">Log in</a><br>

</form>
</div>
</body>
</html>
