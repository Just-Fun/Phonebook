package ua.com.serzh.validation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Serzh on 10/31/16.
 */
public class ValidationTest {

    @Test
    public void testValidateTrue() throws Exception {
        boolean result = Validation.validate("Vasiliy", "\\w{5,}");
        assertEquals(true, result);
    }

    @Test
    public void testValidateFalse() throws Exception {
        boolean result = Validation.validate("Vas", "\\w{5,}");
        assertEquals(false, result);
    }


}