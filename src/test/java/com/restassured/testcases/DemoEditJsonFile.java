package com.restassured.testcases;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.restassured.helpers.JsonHelper;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DemoEditJsonFile {
    @Test
    public void testUpdateValueInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty("additionalneeds", "Update New Value");
            jsonObject.getAsJsonObject("bookingdates").addProperty("checkout", "2025-10-10");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile01.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateDataJson(){
        JsonHelper.updateValueInJson("src/test/resources/testdata/TestJsonFile01.json", "totalprice", 2000);
        JsonHelper.updateValueInJson("src/test/resources/testdata/TestJsonFile01.json", "depositpaid", false);
    }

    @Test
    public void testAddNewPropertyInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Create array value
            JsonArray objectArray = new JsonArray();
            objectArray.add("Support");
            objectArray.add("ERP");

            //Add array value to key "department"
            jsonObject.add("department", objectArray);

            //Add simple key:value
            jsonObject.addProperty("key1", "Value for Key1");

            //Add key:{object}
            Map< String, Object > objectMap = new HashMap< >();
            objectMap.put("name", "Anh Tester");
            objectMap.put("age", 27);
            JsonElement jsonElement = gson.toJsonTree(objectMap);
            jsonObject.add("student", jsonElement);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile01Edited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddNewKey(){
        JsonHelper.addKeyInJson("src/test/resources/testdata/TestJsonFile01.json", "key2", "Value for Key2");
    }

    @Test
    public void addArrayInJson() {
        JsonHelper.addArrayInJson("src/test/resources/testdata/TestJsonFile01.json", "department", new String[]{"Support", "ERP"});
    }

    @Test
    public void testAddObjectInJson() {
        JsonHelper.addObjectInJson("src/test/resources/testdata/TestJsonFile01.json","new object", Map.of("student", "John", "age", "20"));
    }


    @Test
    public void testRemovePropertyInJson() {

        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile02.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            jsonObject.remove("age");

            //Xác định vị trí của property cần xoá
            JsonObject positionObject = jsonObject
                    .get("department").getAsJsonObject()
                    .get("position").getAsJsonObject();

            //Xoá key "years" trong cấu trúc propert
            positionObject.remove("years");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile02Edited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRemoveKeyInJson() {
        JsonHelper.removeKeyInJson("src/test/resources/testdata/TestJsonFile01.json", "key2");
    }


}
