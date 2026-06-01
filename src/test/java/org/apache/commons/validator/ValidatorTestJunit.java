package org.apache.commons.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
    
    @Mock
    private ValidatorResources mockResources;
    
    @Mock
    private Form mockForm;
    
    private Validator validator;
    private static final String TEST_FORM_NAME = "testForm";
    private static final String TEST_FIELD_NAME = "testField";

    @Before
    public void setUp() throws Exception {
        when(mockResources.getForm(any(Locale.class), eq(TEST_FORM_NAME)))
            .thenReturn(mockForm);
        when(mockForm.validate(anyMap(), any(), anyInt(), anyString()))
            .thenReturn(new ValidatorResults());
    }

    // 测试点1: 构造函数 - 验证null资源会抛异常，正常资源可创建
    @Test
    public void testConstructor() {
        // 测试传入null应该抛异常
        try {
            new Validator(null, TEST_FORM_NAME, TEST_FIELD_NAME);
            fail("应该抛出 IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Resources cannot be null.", e.getMessage());
        }
        
        // 测试正常创建
        validator = new Validator(mockResources, TEST_FORM_NAME, TEST_FIELD_NAME);
        assertNotNull(validator);
        assertEquals(TEST_FORM_NAME, validator.getFormName());
    }

    // 测试点2: 参数设置和获取
    @Test
    public void testSetAndGetParameter() {
        validator = new Validator(mockResources);
        
        // 设置参数
        validator.setParameter(Validator.BEAN_PARAM, "test@example.com");
        validator.setParameter("test.int", 123);
        
        // 获取参数验证
        assertEquals("test@example.com", validator.getParameterValue(Validator.BEAN_PARAM));
        assertEquals(123, validator.getParameterValue("test.int"));
        assertNull(validator.getParameterValue("non.existent"));
    }

    // 测试点3: 验证功能
    @Test
    public void testValidate() throws ValidatorException {
        validator = new Validator(mockResources, TEST_FORM_NAME);
        validator.setParameter(Validator.BEAN_PARAM, "test@example.com");
        
        ValidatorResults results = validator.validate();
        
        assertNotNull(results);
        verify(mockResources, atLeastOnce()).getForm(any(Locale.class), eq(TEST_FORM_NAME));
    }
}
