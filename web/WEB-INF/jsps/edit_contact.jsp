<fieldset>
    <legend align="left">Editing</legend>
    <table>
        <tr>
            <td>
                <input type="text" name="editName" value="${sessionScope.contact.name}" placeholder="Edit name">
            </td>
        </tr>
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