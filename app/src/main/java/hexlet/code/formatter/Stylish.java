package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diffs) {
        StringBuilder stringBuilder = new StringBuilder("{\n");

        for (Map<String, Object> map : diffs) {

            String key = map.get("key").toString();
            var oldValue = map.get("oldValue");
            var newValue = map.get("newValue");

            switch (map.get("status").toString()) {
                case "added" -> stringBuilder.append("  + ").append(key).append(": ").append(newValue).append("\n");
                case "deleted" -> stringBuilder.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                case "changed" -> {
                    stringBuilder.append("  - ").append(key).append(": ").append(oldValue).append("\n");
                    stringBuilder.append("  + ").append(key).append(": ").append(newValue).append("\n");
                }
                default -> stringBuilder.append("    ").append(key).append(": ").append(oldValue).append("\n");
            }
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
