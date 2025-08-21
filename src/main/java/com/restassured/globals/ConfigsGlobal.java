package com.restassured.globals;

import com.restassured.helpers.PropertiesHelper;

public class ConfigsGlobal {
    public static String URI = PropertiesHelper.getValue("URI");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static String ACCEPT = PropertiesHelper.getValue("ACCEPT");
    public static String CONTENT_TYPE = PropertiesHelper.getValue("CONTENT_TYPE");
}
