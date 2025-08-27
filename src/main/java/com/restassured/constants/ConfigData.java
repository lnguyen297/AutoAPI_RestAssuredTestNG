package com.restassured.constants;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.helpers.PropertiesHelper;
import com.restassured.helpers.SystemHelper;

import java.io.File;
import java.io.IOException;

public class ConfigData {
    private ConfigData() {
        // Ngăn chặn khởi tạo class
    }

    // Load all properties files
    static {
        PropertiesHelper.loadAllFiles();
    }

    public static final String PROJECT_PATH = SystemHelper.getCurrentDir();
    public static final String EXCEL_DATA_FILE_PATH = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
    public static final String JSON_DATA_FILE_PATH = PropertiesHelper.getValue("JSON_DATA_FILE_PATH");
    public static final String JSON_CONFIG_FILE_PATH = PropertiesHelper.getValue("JSON_CONFIG_FILE_PATH");
    public static final String TEST_DATA_FOLDER_PATH = PropertiesHelper.getValue("TEST_DATA_FOLDER_PATH");

    public static String getValueJsonConfig(String platform, String device, String propertyName) {
        // Initialize Jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // Read JSON file
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(new File(ConfigData.JSON_CONFIG_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String result = rootNode
                .path("platforms")
                .path(platform.trim().toLowerCase())
                .path("devices")
                .path(device.trim().toLowerCase())
                .path(propertyName)
                .asText();

        System.out.println("***" + propertyName + ": " + result);
        return result;
    }
}
