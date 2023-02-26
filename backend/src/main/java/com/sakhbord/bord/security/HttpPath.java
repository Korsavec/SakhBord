package com.sakhbord.bord.security;

public class HttpPath {


    // Использую константы потому что они одиночно используются в UserDetailsServiceImpl
    public static final String LOGIN_USER = "/api/loginUser";


    public static final String LOGIN_ADMIN = "/api/loginAdmin";
    public static final String GUARD_ADMIN = "/api/auth/admin";


    public static final String GUARD_ADD_ANNOUNCEMENT = "/api/auth/addAnnouncement";

    public static final String GET_ANNOUNCEMENTS = "/api/data/announcement";

    public static final String GET_RULES = "/api/data/rules";

    public static final String RESET_PASS_USER = "/api/resetPasswordUser";



    private HttpPath() {
    }

    protected static final String[] API_USER = {
            "/api/registrationUser",
            "/api/confirmEmailUser",
            RESET_PASS_USER,
            "/api/checkTokenUserResetPassword",
            "/api/newPasswordUser",
            LOGIN_USER
    };

    //---

    protected static final String[] API_ADMIN = {
            "/api/resetPasswordAdmin",
            "/api/checkTokenAdminResetPassword",
            "/api/newPasswordAdmin",
            LOGIN_ADMIN
    };

    //---

}
