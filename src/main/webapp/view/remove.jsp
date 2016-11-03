<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fieldset>
    <legend align="left">Removing</legend>
    <table>

        <tr>
            <td>
              Are you ... to delete  ${sessionScope.contact.surname} ?
            </td>


           <%-- <tr>
                <td><input type="text" name="surname" size="20"
                           placeholder="Surname" value="${sessionScope.contact.surname}"></td>

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
                        <td style="color: blue">
                                &lt;%&ndash;${requestScope.surname}&ndash;%&gt;
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
           --%>

        <tr>
            <td colspan="2">
                <input type="submit" name="ok" value="Ok">
                <input type="submit" name="cancel" value="Cancel">
            </td>
        </tr>
    </table>
</fieldset>