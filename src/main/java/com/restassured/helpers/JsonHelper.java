package com.restassured.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.restassured.constants.ConfigData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JsonHelper {
    public static void updateValueInJson(String filePath, String key, String value) {

        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(key, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueInJson(String filePath, String key, int value) {

        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(key, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueInJson(String filePath, String key, Boolean value) {

        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(key, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Java
    public static void addArrayInJson(String filePath, String key, String[] values) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            // Create a new array and add values
            JsonArray jsonArray = new JsonArray();
            for (String value : values) {
                jsonArray.add(value);
            }
            jsonObject.add(key, jsonArray);

            System.out.println("Modified JSON: " + jsonObject);

            // Save to file
            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                outputStream.write(gson.toJson(jsonObject).getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Java
    public static void addObjectInJson(String filePath, String arrayKey, Map<String, String> value) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            // Get or create the array
            JsonArray jsonArray = jsonObject.has(arrayKey) && jsonObject.get(arrayKey).isJsonArray()
                    ? jsonObject.getAsJsonArray(arrayKey)
                    : new JsonArray();

            // Convert Map to JsonObject and add to array
            JsonObject newObject = new JsonObject();
            for (Map.Entry<String, String> entry : value.entrySet()) {
                newObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(newObject);

            // Put the array back in the object
            jsonObject.add(arrayKey, jsonArray);

            System.out.println("Modified JSON: " + jsonObject);

            // Save to file
            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                outputStream.write(gson.toJson(jsonObject).getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addKeyInJson(String filePath, String key, String value) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            // Add new key-value pair
            jsonObject.addProperty(key, value);

            System.out.println("Modified JSON: " + jsonObject);

            // Save to file
            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                outputStream.write(gson.toJson(jsonObject).getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeKeyInJson(String filePath, String key) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);


            jsonObject.remove(key);
            System.out.println("Modified JSON: " + jsonObject);

            try (Writer writer = new FileWriter(filePath)) {
                gson.toJson(jsonObject, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Get value from JSON file with multiple keys
    public static String getValueJsonObject(String... keys) {
        String value = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readTree(new File(ConfigData.JSON_DATA_FILE_PATH));

            for (String key : keys) {
                node = node.path(key);
            }

            value = node.asText();
            System.out.println("Value: " + value);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    // Get value from JSON file in new file path with multiple keys
    public static String getValueJsonObject_FilePath(String filePath, String... keys) {
        String value = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readTree(new File(filePath));

            for (String key : keys) {
                node = node.path(key);
            }

            value = node.asText();
            System.out.println("Value: " + value);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    // Get value from JSON array
    public static String getValueJsonArray(int itemIndex, String... keys) {
        String value = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(ConfigData.JSON_DATA_FILE_PATH));

            // Lấy item tại index từ mảng gốc
            JsonNode itemNode = rootNode.get(itemIndex);
            if (itemNode == null || !itemNode.isObject()) {
                throw new IllegalArgumentException("Item index không hợp lệ hoặc không phải object.");
            }

            JsonNode current = itemNode;
            for (String key : keys) {
                current = current.path(key);
            }

            value = current.asText();
            System.out.println("Value: " + value);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    // Get value from JSON array in new file path
    public static String getValueJsonArray_FilePath(String filePath, int itemIndex, String... keys) {
        String value = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            // Lấy item tại index từ mảng gốc
            JsonNode itemNode = rootNode.get(itemIndex);
            if (itemNode == null || !itemNode.isObject()) {
                throw new IllegalArgumentException("Item index không hợp lệ hoặc không phải object.");
            }

            JsonNode current = itemNode;
            for (String key : keys) {
                current = current.path(key);
            }

            value = current.asText();
            System.out.println("Value: " + value);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }



}
