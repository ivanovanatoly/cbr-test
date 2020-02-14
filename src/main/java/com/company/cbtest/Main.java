package com.company.cbtest;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static final String INPUT_DEFAULT_NAME = "input.json";
    public static final String OUTPUT_DEFAULT_NAME = "output.json";

    public static void main(String[] args) {
        String inputFileName = args.length > 0 ? args[0] : INPUT_DEFAULT_NAME;
        String outputFileName = args.length > 1 ? args[1] : OUTPUT_DEFAULT_NAME;
        try (
            BufferedReader reader = Files.newBufferedReader(Paths.get(inputFileName));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName));
        ) {
            Gson gson = new GsonBuilder().
                    registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
                        if(src == src.longValue()) {
                            return new JsonPrimitive(src.longValue());
                        }
                        return new JsonPrimitive(src);
                    }).setPrettyPrinting().create();
            Map mapJson = JsonHelper.processSum(gson.fromJson(reader, Map.class));
            gson.toJson(mapJson, writer);
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found!");
        } catch (IOException e) {
            System.err.println("Some I/O error!");
        }
    }
}
