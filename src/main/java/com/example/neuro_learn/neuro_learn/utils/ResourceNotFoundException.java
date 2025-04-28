package com.example.neuro_learn.neuro_learn.utils;


public class ResourceNotFoundException extends RuntimeException {

     String resourceName;
     String fieldName;
     String fieldValue;
     boolean isPresent;

    public ResourceNotFoundException(String resourceName, String fieldName, String taskId, boolean isPresent) {
        super(generateMessage(resourceName, fieldName, taskId, isPresent));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = toString();
        this.isPresent = isPresent;
    }

    private static String generateMessage(String resourceName, String fieldName, String fieldValue, boolean isPresent) {
        if (isPresent) {
            return String.format("%s with %s: %s already exists", resourceName, fieldName, fieldValue);
        } else {
            return String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue);
        }
    }
}
