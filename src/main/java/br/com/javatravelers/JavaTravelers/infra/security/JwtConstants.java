package br.com.javatravelers.JavaTravelers.infra.security;

public class JwtConstants {
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 60 ;
    public static final String SIGNING_KEY = "GamaToken";
    public static final String TOKEN_PREFIX = ""; //"Bearer "
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}
