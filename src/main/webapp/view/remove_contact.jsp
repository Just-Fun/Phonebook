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
        <td>${contact.name}</td>
        <td>${contact.mobileNumber}</td>
    </tr>
</c:forEach>