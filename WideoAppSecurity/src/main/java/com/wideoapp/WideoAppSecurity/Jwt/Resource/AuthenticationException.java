package com.wideoapp.WideoAppSecurity.Jwt.Resource;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) { super(message, cause);}
}
