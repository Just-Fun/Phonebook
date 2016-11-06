<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="contact" items="${sessionScope.contacts}">
    <tr>
        <c:choose>
            <c:when test="${sessionScope.edit}">
                <td>
                    <input type="radio" name="select" value="Selected">
                </td>
            </c:when>
            <c:when test="${sessionScope.remove}">
                <td>
                    <input type="checkbox" name="select" value="Selected" >
                </td>
            </c:when>
        </c:choose>

      &lt;%&ndash;  <td>${contact.name}</td>
        <td>${contact.mobileNumber}</td>&ndash;%&gt;
    </tr>
</c:forEach>--%>
