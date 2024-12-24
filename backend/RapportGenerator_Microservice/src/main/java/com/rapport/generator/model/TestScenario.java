package com.rapport.generator.model;

public class TestScenario {
    private String testName;
    private String content;
    private String status;

    // Constructor
    public TestScenario(String testName, String content, String status) {
        this.testName = testName;
        this.content = content;
        this.status = status;
    }

    // Getters and Setters
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TestScenario{" +
                "testName='" + testName + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
