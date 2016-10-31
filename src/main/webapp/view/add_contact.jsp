<fieldset>
    <legend align="left">Adding</legend>
    <table>
        <tr>
            <td>
                <input type="text" name="newSubscriberName" placeholder="Name"> name
            </td>
        </tr>


        <%--// TODO realised--%>
      <%--  <tr>
            <td><input type="text" name="newSubscriberName" size="30" placeholder="Enter name"
                       value="${requestScope.newSubscriberName}"></td>
            <c:if test="${empty requestScope.validNewSubscriberName}">
                <c:set var="validNewSubscriberName" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${!requestScope.validNewSubscriberName}">
                    <td style="color: red">
                        Name must consist only letters.
                        Amount of signs mustn't be less than 5 signs.
                    </td>
                </c:when>
                <c:when test="${requestScope.subscriberExist}">
                    <td style="color: red">
                        The subscriber with specified name already exists!.
                    </td>
                </c:when>
                <c:otherwise> </c:otherwise>
            </c:choose>
        </tr>--%>


        <tr>
            <td>
                <input type="text" name="mobileNumber" placeholder="+380XXXXXXXXX"> phone
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
