package com.ppanticona.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";
    public static final String CAJERO = "ROLE_CAJERO";

    public static final String MESERO = "ROLE_MESERO";

    public static final String COCINERO = "ROLE_COCINERO";

    public static final String GERENTE = "ROLE_GERENTE"; 
    public static final String INVENTARIO = "ROLE_INVENTARIO";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
 
    private AuthoritiesConstants() {}
}
