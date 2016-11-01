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
        assertEquals(true, validate("Vasiliy", Validation.getFiveLettersPattern()));
    }

    @Test
    public void testValidateFalse() throws Exception {
        assertEquals(false, validate("Vas", Validation.getFiveLettersPattern()));
    }

    @Test
    public void testValidateEmailTrue() throws Exception {
        String regex = Validation.getEmailPattern();
        assertEquals(true, validate("mkyong@yahoo.com", regex));
        assertEquals(true, validate("mkyong-100@yahoo.com", regex));
        assertEquals(true, validate("mkyong.100@yahoo.com", regex));
        assertEquals(true, validate("mkyong.100.test@yahoo.com", regex));
        assertEquals(true, validate("mkyong-100@yahoo-test.com", regex));
        assertEquals(true, validate("mkyong_100@yahoo-test.com.ua", regex));
    }

    @Test
    public void testValidateEmailFalse() throws Exception {
        String regex = Validation.getEmailPattern();
        assertEquals(false, validate("mkyong – must", regex));
        assertEquals(false, validate("mkyong@.com.my", regex));
        assertEquals(false, validate("mkyong123@gmail.a", regex));
        assertEquals(false, validate(".mkyong@mkyong.com", regex));
        assertEquals(false, validate("mkyong()*@gmail.com", regex));
        assertEquals(false, validate("mkyong@mkyong@gmail.com", regex));
        assertEquals(false, validate("mkyong@gmail.com.1a", regex));
    }

    @Test
    public void testValidateUkrainePhoneNumberFalse() throws Exception {
        String regex = Validation.getPhoneRegex();
        assertEquals(false, validate("+38066579445623", regex));
        assertEquals(false, validate("380665794456", regex));
        assertEquals(false, validate("80665794456", regex));
        assertEquals(false, validate("+38066579", regex));
    }

    @Test
    public void testValidateUkrainePhoneNumberTrue() throws Exception {
        String regex = Validation.getPhoneRegex();

        assertEquals(true, validate("+38-066-283-93-57", regex));
        assertEquals(true, validate("+38-044-283-93-57", regex));
        assertEquals(true, validate("+380442839357", regex));
        assertEquals(true, validate("+38(044)537-28-07", regex));
        assertEquals(true, validate("+38044223-95-26", regex));
        assertEquals(true, validate("+38063223-95-26", regex));
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