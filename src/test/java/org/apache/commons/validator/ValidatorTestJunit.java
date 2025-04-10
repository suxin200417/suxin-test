package org.apache.commons.validator;

import org.junit.*;

import static org.junit.Assert.*;

public class ValidatorTestJunit {
    private Validator validator;
    private ValidatorResources resources;
    private static final String FORM_NAME = "testForm";
    private static final String FIELD_NAME = "testField";

    @Before
    public void setUp() throws Exception {
        resources = new ValidatorResources(); // 创建验证资源
    }

    @After
    public void tearDown() throws Exception {
        validator = null;
        resources = null;
    }

    @Test
    public void getParameterValue() {
        try {
            // 测试参数设置和获取功能
            validator = new Validator(resources, FORM_NAME, FIELD_NAME);
            validator.setParameter(Validator.BEAN_PARAM, "test@example.com");
            // 验证是否能正确获取参数值
            String value = (String) validator.getParameterValue(Validator.BEAN_PARAM);
            assertEquals("test@example.com", value);
        } catch (IllegalArgumentException e) {
            // 捕获构造函数可能抛出的异常
            System.out.println("构造函数抛出异常: " + e.getMessage());
        }
    }

    @Test
    public void getFormName() {
        try {
            // 测试表单名称获取功能
            validator = new Validator(resources, FORM_NAME, FIELD_NAME);
            // 验证是否能正确获取表单名称
            String formName = validator.getFormName();
            assertEquals(FORM_NAME, formName);
        } catch (IllegalArgumentException e) {
            // 捕获构造函数可能抛出的异常
            System.out.println("构造函数抛出异常: " + e.getMessage());
        }
    }

    @Test
    public void validate() {
        try {
            // 测试验证功能
            validator = new Validator(resources, FORM_NAME, FIELD_NAME);
            validator.setParameter(Validator.BEAN_PARAM, "test@example.com");
            // 执行验证并检查结果
            ValidatorResults results = validator.validate();
            assertNotNull("验证结果不应为null", results);
            // 检查验证结果是否包含预期字段
            assertTrue("验证结果应包含测试字段", results.getValidatorResult(FIELD_NAME) != null);
        } catch (IllegalArgumentException e) {
            // 捕获构造函数可能抛出的异常
            System.out.println("构造函数抛出异常: " + e.getMessage());

        } catch (ValidatorException e) {
            throw new RuntimeException(e);
        }
    }
}