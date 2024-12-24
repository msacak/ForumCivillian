package com.sacak.forumcivillian.constants;

public class RestApis {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String DEVELOPER = "/dev";
    private static final String TEST = "/test";
    private static final String PROD = "/prod";

    private static final String ROOT = VERSION+ DEVELOPER;

    public static final String ADMIN =ROOT+ "/admin";
    public static final String AUTH = ROOT + "/auth";
    public static final String USER = ROOT + "/user";
    public static final String POST = ROOT + "/post";
    public static final String COMMENT = ROOT + "/comment";
    public static final String TOPIC = ROOT + "/topic";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
}
