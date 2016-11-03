<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fieldset>
    <legend align="left">Removing</legend>
    <table>

        <tr>
            <td>
                Are you sure you want to delete contact '${sessionScope.contact.surname} ${sessionScope.contact.name}'?
            </td>

        <tr>
            <td colspan="2">
                <input type="submit" name="ok" value="Ok">
                <input type="submit" name="cancel" value="Cancel">
            </td>
        </tr>
    </table>
</fieldset>