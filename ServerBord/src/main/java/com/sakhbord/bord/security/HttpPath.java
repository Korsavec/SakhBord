package com.sakhbord.bord.security;

public class HttpPath {


    // Использую константы потому что они одиночно используются в UserDetailsServiceImpl
    public static final String API_SINGLETON_LOGIN_USER = "/api/auth/loginUser";
    public static final String API_SINGLETON_GUARD_USER = "/api/AccountGuard/user";


    public static final String API_SINGLETON_LOGIN_ADMIN = "/api/auth/loginAdmin";
    public static final String API_SINGLETON_GUARD_ADMIN = "/api/AccountGuard/admin";


    public static final String API_SINGLETON_GUARD_ADD_MESSAGE = "/api/AccountGuard/addMessage";




    private HttpPath() {
    }

    protected static final String[] API_USER = {
            "/api/auth/registrationUser",
            "/api/auth/confirmEmailUser",
            "/api/auth/resetPasswordUser",
            "/api/auth/checkTokenUserResetPassword",
            "/api/auth/newPasswordUser",
            API_SINGLETON_LOGIN_USER
    };

    protected static final String[] API_ACCOUNT_GUARD_USER = {
            API_SINGLETON_GUARD_USER
    };

    //---

    protected static final String[] API_ADMIN = {
            "/api/auth/resetPasswordAdmin",
            "/api/auth/checkTokenAdminResetPassword",
            "/api/auth/newPasswordAdmin",
            API_SINGLETON_LOGIN_ADMIN
    };

    protected static final String[] API_ACCOUNT_GUARD_ADMIN = {
            API_SINGLETON_GUARD_ADMIN,
    };

    //---

    protected static final String[] API_GENERAL = {
            "/api/checkShopName",
            "/api/all"
    };

    //---

    protected static final String[] API_GUARD_PASSPORT = {
            "/resources/Guard/image/passport/**"
    };

    //---

    protected static final String[] API_GUARD_ADD_MESSAGE = {
            API_SINGLETON_GUARD_ADD_MESSAGE
    };



}
