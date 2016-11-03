<fieldset>
    <legend align="left">Editing</legend>
    <table>
        <tr>
            <td>
                <input type="text" name="editName" value="${sessionScope.contact.name}" placeholder="Edit name">
            </td>
        </tr>


       <%-- <tr>
            <td><input type="text" name="editName" size="20"
                       placeholder="Name" value="${sessionScope.contact.name}"></td>
            <c:if test="${empty requestScope.validSubscriberName}">
                <c:set var="validSubscriberName" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.emptySubscriberName}">
                    <td style="color: red">
                        Field can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.validSubscriberName}">
                    <td style="color: red">
                        Name must be at least 4 characters and consist only latin letters.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        Name
                    </td>
                    <td style="color: blue">
                            &lt;%&ndash;${requestScope.newSubscriberName}&ndash;%&gt;
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>--%>



        <tr>
            <td>
                <input type="text" name="mobileNumber" value="${sessionScope.contact.mobileNumber}"
                       placeholder="Edit mobile number">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="ok" value="Ok">
                <input type="submit" name="cancel" value="Cancel">
            </td>
        </tr>
    </table>
</fieldset>