package hexlet.code.formatter;

import java.util.Map;

public class Stylish {
    public static String format(Map<String, String> diffs, Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder stringBuilder = new StringBuilder("{\n");

        for (String key : diffs.keySet()) {
            switch (diffs.get(key)) {
                case "added" -> stringBuilder.append("  + " + key + ": " + data2.get(key) + "\n");
                case "deleted" -> stringBuilder.append("  - " + key + ": " + data1.get(key) + "\n");
                case "changed" -> {
                    stringBuilder.append("  - " + key + ": " + data1.get(key) + "\n");
                    stringBuilder.append("  + " + key + ": " + data2.get(key) + "\n");
                }
                default -> stringBuilder.append("    " + key + ": " + data1.get(key) + "\n");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
