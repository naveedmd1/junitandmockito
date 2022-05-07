package com.springboot.javatestingapplication.exception;

public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -355184973204869568L;

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
