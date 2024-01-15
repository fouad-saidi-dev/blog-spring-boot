package com.blog.security;

public class SecurityConstants {
    public static final long EXPIRATION_TIME=864000000;
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="authorization"; //url
    public static final String SIGN_UP_URL="/users";
    public static final String TOKEN_SECRET="xOIEv.Nwy{zd|>d{Au-p1q*j[2^8_q";
    public static final String[] URL_SWAGGER = {
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui/index.html",
            "/actuator/**"
    };
    public static final String[] URL_NOT_AUTH = {
            "/users/login","/users/add-user","/posts","files/get/**"
    };
}
