package com.restassured.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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


}
