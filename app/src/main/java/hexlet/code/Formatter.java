package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatter.Json;
import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String styleByFormat(List<Map<String, Object>> diffs, String format) throws JsonProcessingException {
        switch (format) {
            case "plain" -> {
                return Plain.format(diffs);
            }
            case "json" -> {
                return Json.format(diffs);
            }
            default -> {
                return Stylish.format(diffs);
            }
        }
    }
}
