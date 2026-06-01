package org.apache.commons.validator;

import org.junit.*;
import static org.junit.Assert.*;

public class ValidatorTestJunit {
    
    // 先测试基本功能
    @Test
    public void getParameterValue() {
        ValidatorResources resources = new ValidatorResources();
        Validator validator = new Validator(resources, "testForm", "testField");
        validator.setParameter(Validator.BEAN_PARAM, "test@example.com");
        String value = (String) validator.getParameterValue(Validator.BEAN_PARAM);
        assertEquals("test@example.com", value);
    }

    @Test
    public void getFormName() {
        ValidatorResources resources = new ValidatorResources();
        Validator validator = new Validator(resources, "testForm", "testField");
        assertEquals("testForm", validator.getFormName());
    }
    
    // 暂时注释掉 validate 测试
    // @Test
    // public void validate() throws ValidatorException {
    //     // 这个测试需要完整的资源加载
    // }
}
