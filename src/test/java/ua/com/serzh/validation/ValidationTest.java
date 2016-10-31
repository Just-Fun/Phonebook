package ua.com.serzh.validation;

import org.junit.Test;

import static org.junit.Assert.*;
import static ua.com.serzh.validation.Validation.validate;

/**
 * Created by Serzh on 10/31/16.
 */
public class ValidationTest {

    @Test
    public void testValidateTrue() throws Exception {
        boolean result = validate("Vasiliy", "\\w{5,}");
        assertEquals(true, result);
    }

    @Test
    public void testValidateFalse() throws Exception {
        boolean result = validate("Vas", "\\w{5,}");
        assertEquals(false, result);
    }

    @Test
    public void testValidatePhoneNumber() throws Exception {
        String regex = "[+]\\d{12}";
        assertEquals(true, validate("+380665794456", regex));
        assertEquals(false, validate("380665794456", regex));
        assertEquals(false, validate("80665794456", regex));
        assertEquals(false, validate("+38066579", regex));
    }

    @Test
    public void testValidateUkrainePhoneNumber() throws Exception {
        String regex = "^((\\+38)-?\\s?)(\\(?044\\)?)?-?\\s?\\d{3}-?\\s?\\d{2}-?\\s?\\d{2}$";

        assertEquals(true, validate("+38-044-283-93-57", regex));
        assertEquals(true, validate("+380442839357", regex));
        assertEquals(true, validate("+38(044)537-28-07", regex));
        assertEquals(true, validate("+38044223-95-26", regex));
        assertEquals(true, validate("+38044223-95-26", regex));
        assertEquals(true, validate("+38044 223 95 26", regex));
        assertEquals(true, validate("+38 044 223 95 26", regex));

        //TODO fix
        assertEquals(true, validate("+38(044537-28-07", regex));

        assertEquals(false, validate("+83-044-283-93-57", regex));
        assertEquals(false, validate("8-044-283-93-57", regex));
        assertEquals(false, validate("80442839357", regex));
        assertEquals(false, validate("380442839357", regex));
        assertEquals(false, validate("+0442839357", regex));
        assertEquals(false, validate("0442839357", regex));
    }
}