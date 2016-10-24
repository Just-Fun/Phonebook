<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        fieldset {
            width: 20px;
            border-radius: 8px;
        }

        .contact_table {
            border: 1px solid black;
        }

        .contact_table td {
            border: 1px solid black;
        }

        .contact_table th {
            border: 1px solid black;
        }
    </style>
    <title>Phonebook</title>
</head>
<body>
<form method="post" action="main.do">
    <table>
        <c:if test='${not empty sessionScope.user}'>
            <tr>
                <td>Logged as <a href="#" class="button username">${sessionScope.user.name}</a></td>
                <td>
                    <input type="submit" name="button" value="logout"/>
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
        <c:when test="${sessionScope.search}">
            <jsp:include page="search_contact.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="actions.jsp"/>
        </c:otherwise>
    </c:choose>
    <p></p>

    <table>
        <tr>
            <td>
                <input type="text" name="subscriberName" placeholder="Name">
            </td>
            <td>
                <input type="submit" name="searchButton" value="Search">
            </td>
            <td>
                <input type="submit" name="allContacts" value="All Contacts">
            </td>
        </tr>
    </table>

    <table class="contact_table">
        <tr>
            <c:if test="${!sessionScope.add}">
                <th></th>
            </c:if>
            <th>Name</th>
            <th>Mob. number</th>
        </tr>

        <c:set var="start" value="${pageNumber * 5 - 5}"/>
        <c:set var="stop" value="${pageNumber * 5 - 1}"/>

        <c:forEach var="contact" items="${sessionScope.contacts}" begin="${start}" end="${stop}">
            <tr>
                <c:if test="${!sessionScope.add}">
                    <td>
                        <input type="radio" name="select" value="${contact.contactId}" checked="checked">
                    </td>
                </c:if>
                <td>${contact.name}</td>
                <td>${contact.mobileNumber}</td>
            </tr>
        </c:forEach>

        <table>
            <tr>
                <td>
                    <c:if test="${pageNumber gt 1}">
                        <input type="submit" name="pageButton" value="Prev">
                    </c:if>
                </td>
                <td></td>
                <td>
                    <c:set var="amountOfPages" value="${sessionScope.amountOfContacts / 5 + sessionScope.delta}"
                           scope="session"/>

                    <c:if test="${pageNumber lt (sessionScope.amountOfPages)}">
                        <input type="submit" name="pageButton" value="Next">
                    </c:if>
                </td>
            </tr>
        </table>
    </table>
</form>
</body>
</html>
