package com.rapport.generator.exception;

public class TestScenarioFetchException extends RuntimeException {
    public TestScenarioFetchException(String message) {
        super(message);
    }

    public TestScenarioFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
