package br.com.javatravelers.JavaTravelers.domain.enums;

public enum  Roles {
    USER,
    API,
    SWAGGER,
    ADMIN;
	public static final String PRE_USER= "hasRole('USER')";
	public static final String PRE_API= "hasRole('API')";
	public static final String PRE_ADMIN= "hasRole('ADMIN')";
	public static final String PRE_USER_ADMIN= "hasAnyRole('ADMIN','USER')";
	public static final String PRE_API_ADMIN= "hasAnyRole('ADMIN','API')";
}
