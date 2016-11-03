<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
 <%--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/main.css"/>

    <title>Phonebook</title>
</head>
<body>
<div class="main-page">
    <form method="POST" action="main">
        <table>
            <c:if test='${not empty sessionScope.user}'>
                <tr>
                    <td>Logged as <a href="#" class="button username">${sessionScope.user.name}</a></td>
                    <td>
                        <a href="logout">logout</a><br>
                    </td>
                </tr>
            </c:if>
            <tr></tr>
        </table>

        <c:choose>
            <c:when test="${sessionScope.add}">
                <jsp:include page="add_contact.jsp"/>
            </c:when>
            <c:when test="${sessionScope.edit}">
                <jsp:include page="edit_contact.jsp"/>
            </c:when>

        <%--    &lt;%&ndash;TODO is it used?&ndash;%&gt;
            <c:when test="${sessionScope.search}">
                <jsp:include page="search_contact.jsp"/>
            </c:when>--%>


            <c:when test="${sessionScope.delete}">
                <jsp:include page="remove.jsp"/>
            </c:when>


            <c:otherwise>
                <jsp:include page="actions.jsp"/>
            </c:otherwise>
        </c:choose>
        <p></p>

        <table>
            <tr>
                <c:if test="${empty requestScope.info}">
                    <c:set var="textInfo" value="false" scope="request"/>
                </c:if>
                <c:choose>
                    <c:when test="${requestScope.info}">
                        <td style="color: blue">
                                ${requestScope.textInfo}
                        </td>
                    </c:when>
                    <c:otherwise> </c:otherwise>
                </c:choose>
            </tr>
        </table>

        <table>
            <tr>
                <td>
                    <input type="text" name="searchQuery" placeholder="Any content...">
                </td>
                <td>
                    <input type="submit" name="searchButton" value="Search">
                </td>
                <td>
                    <input type="submit" name="allContacts" value="All Contacts">
                </td>
            </tr>
        </table>

        <h3 class="lead">List of contacts:</h3>

        <table class="contact_table">
            <tr>
                <%--TODO whats this?--%>
                <c:if test="${!sessionScope.add}">
                    <th></th>
                </c:if>
                <th>Surname</th>
                <th>Name</th>
                <th>Patronymic</th>
                <th>Mob.number</th>
                <th>Home phone</th>
                <th>address</th>
                <th>e-mail</th>
            </tr>

            <%--TODO make scrolling--%>
            <c:forEach var="contact" items="${sessionScope.contacts}">
                <tr>
                        <c:if test="${!sessionScope.add && !sessionScope.edit && !sessionScope.delete}">
                        <td>
                            <%--TODO rename select -> ~selectContactId--%>
                            <input type="radio" name="select" value="${contact.contactId}" checked="checked">
                        </td>
                    </c:if>
                    <td>${contact.surname}</td>
                    <td>${contact.name}</td>
                    <td>${contact.patronymic}</td>
                    <td>${contact.mobileNumber}</td>
                    <td>${contact.homePhone}</td>
                    <td>${contact.address}</td>
                    <td>${contact.email}</td>
                </tr>
            </c:forEach>

        </table>
    </form>
</div>
</body>
</html>