package com.company.cbtest;

import java.util.List;
import java.util.Map;

public class JsonHelper {

    public static final String ARRAY_KEY = "array";
    public static final String SUM_KEY = "sum";

    public static Map processSum(Map json) {
        if (json != null && json.containsKey(ARRAY_KEY)) {
            JsonHelper.processArray((List) json.get(ARRAY_KEY));
        }
        return json;
    }

    private static void processArray(List elements) {
        if (elements != null) {
            elements.forEach(el -> JsonHelper.processElement((Map) el));
        }
    }

    private static void processElement(Map element) {
        if (element != null) {
            double sum = element.values().stream().mapToDouble(value -> JsonHelper.parseValue(value.toString())).sum();
            element.put(SUM_KEY, sum);
        }
    }

    private static double parseValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
