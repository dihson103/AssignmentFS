package com.example.assignment.utils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil<T> {

    public static Gson gson = new Gson();

    public static <T> T convertToObject(BufferedReader reader, Class<T> classObject) {
        StringBuilder jsonBuilder = new StringBuilder();
        try (reader) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            System.out.println(jsonBuilder);
            return gson.fromJson(jsonBuilder.toString(), classObject);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
