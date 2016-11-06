<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fieldset>
    <legend align="left">Adding</legend>
    <table>

        <tr>
            <td><input type="text" name="surname" size="20"
                       placeholder="Surname" value="${requestScope.surname}"></td>
            <c:if test="${empty requestScope.validSurname}">
                <c:set var="validSurname" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.emptySurname}">
                    <td style="color: red">
                        Field can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.validSurname}">
                    <td style="color: red">
                        Surname must be at least 4 characters and consist only latin letters.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        Surname
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td><input type="text" name="name" size="20"
                       placeholder="Name" value="${requestScope.name}"></td>
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
                </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td><input type="text" name="patronymic" size="20"
                       placeholder="Patronymic" value="${requestScope.patronymic}"></td>
            <c:if test="${empty requestScope.validPatronymic}">
                <c:set var="validPatronymic" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.patronymicEmpty}">
                    <td style="color: red">
                        Field can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.validPatronymic}">
                    <td style="color: red">
                        Patronymic must be at least 4 characters and consist only latin letters.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        Patronymic
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td><input type="text" name="mobileNumber" size="20"
                       placeholder="+380XXXXXXXXX" value="${requestScope.mobileNumber}"></td>
            <c:if test="${empty requestScope.validMobileNumber}">
                <c:set var="validMobileNumber" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${requestScope.mobileNumberEmpty}">
                    <td style="color: red">
                        Field can not be empty.
                    </td>
                </c:when>
                <c:when test="${!requestScope.validMobileNumber}">
                    <td style="color: red">
                        Mobile Number must be in format +380XXXXXXXXX, consist of '+' and 12 digits.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        Mobile Number
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td><input type="text" name="homePhone" size="20"
                       placeholder="+380XXXXXXXXX" value="${requestScope.homePhone}"></td>
            <c:if test="${empty requestScope.validHomePhone}">
                <c:set var="validHomePhone" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${!requestScope.validHomePhone}">
                    <td style="color: red">
                        Home Phone must be in format +380XXXXXXXXX, consist of '+' and 12 digits.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        Home Phone
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td><input type="text" name="address" size="20"
                       placeholder="address" value="${requestScope.address}"></td>
            <td style="color: black">
                Address
            </td>
        </tr>

        <tr>
            <td><input type="text" name="email" size="20"
                       placeholder="name@gmail.com" value="${requestScope.email}"></td>
            <c:if test="${empty requestScope.validEmail}">
                <c:set var="validEmail" value="true" scope="request"/>
            </c:if>
            <c:choose>
                <c:when test="${!requestScope.validEmail}">
                    <td style="color: red">
                        Put valid e-mail adress.
                    </td>
                </c:when>
                <c:otherwise>
                    <td style="color: black">
                        e-mail
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
