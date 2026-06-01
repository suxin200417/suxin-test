package org.apache.commons.validator;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Locale;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTestJunit {
    
    @Mock
    private ValidatorResources mockResources;
    
    @Mock
    private Form mockForm;
    
    private Validator validator;
    private static final String FORM_NAME = "testForm";
    private static final String FIELD_NAME = "testField";

    @Before
    public void setUp() throws Exception {
        // Mock Form 对象
        when(mockForm.validate(anyMap(), any(), anyInt(), anyString()))
            .thenReturn(new ValidatorResults());
        
        // Mock Resources 返回 Mock Form
        when(mockResources.getForm(any(Locale.class), eq(FORM_NAME)))
            .thenReturn(mockForm);
    }

    @After
    public void tearDown() throws Exception {
        validator = null;
    }

    @Test
    public void getParameterValue() {
        validator = new Validator(mockResources, FORM_NAME, FIELD_NAME);
        validator.setParameter(Validator.BEAN_PARAM, "test@example.com");
        String value = (String) validator.getParameterValue(Validator.BEAN_PARAM);
        assertEquals("test@example.com", value);
    }

    @Test
    public void getFormName() {
        validator = new Validator(mockResources, FORM_NAME, FIELD_NAME);
        String formName = validator.getFormName();
        assertEquals(FORM_NAME, formName);
    }

    @Test
    public void validate() throws ValidatorException {
        validator = new Validator(mockResources, FORM_NAME, FIELD_NAME);
        validator.setParameter(Validator.BEAN_PARAM, "test@example.com");
        ValidatorResults results = validator.validate();
        assertNotNull("验证结果不应为null", results);
    }
}
