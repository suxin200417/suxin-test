package org.apache.commons.validator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorResources;

public class TestValidator {
    public static void main(String[] args) {
        int totalTests = 0;
        int passedTests = 0;

        // 测试1: 传入null资源 - 预期应该抛出异常
        totalTests++;
        try {
            new Validator(null, "testForm", "testField");
            System.out.println("测试1失败: 传入null资源时未抛出异常");
        } catch (Exception e) {
            passedTests++;
            System.out.println("测试1通过: " + e.getMessage());
        }

        // 测试2: 传入非null资源 - 预期应该正常创建
        totalTests++;
        try {
            ValidatorResources resources = new ValidatorResources();
            new Validator(resources, "testForm", "testField");
            passedTests++;
            System.out.println("测试2通过: 正常创建Validator实例");
        } catch (Exception e) {
            System.out.println("测试2失败: " + e.getMessage());
        }

        // 测试3: 测试另一个未注入错误的Validator构造函数
        totalTests++;
        try {
            ValidatorResources resources = new ValidatorResources();
            new Validator(resources, "email");
            System.out.println("测试3通过: 传入有效resources时未抛出异常");
            passedTests++;
        } catch (IllegalArgumentException e) {
            System.out.println("测试3失败: 传入有效resources时抛出异常: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("测试3失败: 抛出异常类型不正确: " + e.getClass().getName());
        }

        // 测试4: 测试getParameterValue方法
        totalTests++;
        try {
            ValidatorResources resources = new ValidatorResources();
            Validator validator = new Validator(resources, "testForm", "testField");
            // 设置参数值
            String expectedValue = "testValue";
            validator.setParameter("paramName", expectedValue);
            // 安全获取参数值
            Object paramObj = validator.getParameterValue("paramName");
            String actualValue = (paramObj != null) ? paramObj.toString() : null;

            if (expectedValue.equals(actualValue)) {
                passedTests++;
                System.out.println("测试4通过: getParameterValue返回值正确");
            } else {
                System.out.println("测试4失败: 期望值=" + expectedValue + ", 实际值=" + actualValue);
            }
        } catch (Exception e) {
            System.out.println("测试4异常: " + e.getMessage());
        }

        // 汇总结果
        System.out.println("\n测试结果:");
        System.out.println("通过测试: " + passedTests + "/" + totalTests);
        System.out.println("失败测试: " + (totalTests - passedTests) + "/" + totalTests);
        if (passedTests == totalTests) {
            System.out.println("所有测试通过 - 未检测到错误");
        } else {
            System.out.println("检测到错误 - 构造函数中的条件检查可能有误");
        }
    }
}