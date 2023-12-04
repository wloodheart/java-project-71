package hexlet.code;

import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;

import java.util.Map;

public class Formatter {
    public static String styleByFormat(Map<String, String> diffs, Map<String, Object> data1, Map<String, Object> data2, String format) {
        switch (format) {
            case "plain" -> {
                return Plain.format(diffs, data1, data2);
            }
            default -> {
                return Stylish.format(diffs, data1, data2);
            }
        }
    }
}
