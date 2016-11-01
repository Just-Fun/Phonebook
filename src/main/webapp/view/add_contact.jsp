<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fieldset>
    <legend align="left">Adding</legend>
    <table>

        <tr>
            <td><input type="text" name="newSubscriberName" size="20"
                       placeholder="Name" value="${requestScope.newSubscriberName}"></td>
            <c:if test="${empty requestScope.validSubscriberName}">
                <c:set var="validSubscriberName" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.emptySubscriberName}">
                    <td style="color: red">
                        Name can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.validSubscriberName}">
                    <td style="color: red">
                        Name must be at least 4 characters and consist only letters.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        User name ${requestScope.newSubscriberName}
                    </td>
                    <td style="color: blue">
                            ${requestScope.name}
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>


     <%--   <tr>
            <td>
                <input type="text" name="mobileNumber" placeholder="+380XXXXXXXXX"> phone
            </td>
        </tr>--%>

        <tr>
            <td><input type="text" name="mobileNumber" size="20"
                       placeholder="+380XXXXXXXXX" value="${requestScope.mobileNumber}"></td>
            <c:if test="${empty requestScope.validMobileNumber}">
                <c:set var="validMobileNumber" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.mobileNumberEmpty}">
                    <td style="color: red">
                        Mobile Number can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.validMobileNumber}">
                    <td style="color: red">
                        Mobile Number must be in format +380XXXXXXXXX, consist of '+' and 12 digits.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        Mobile Number ${requestScope.mobileNumber}
                    </td>
                    <td style="color: blue">
                            ${requestScope.mobileNumber}
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" name="ok" value="Ok">
                <input type="submit" name="cancel" value="Cancel">
            </td>
        </tr>
    </table>
</fieldset>
