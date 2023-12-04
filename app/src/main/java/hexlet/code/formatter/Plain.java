package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> diffs) {
        StringBuilder stringBuilder = new StringBuilder("\n");

        for (Map<String, Object> map : diffs) {

            String key = map.get("key").toString();
            var oldValue = map.get("oldValue");
            var newValue = map.get("newValue");

            switch (map.get("status").toString()) {

                case "added" -> stringBuilder.append("Property ").append(formatValue(key))
                        .append(" was added with value: ").append(formatValue(newValue)).append("\n");

                case "deleted" -> stringBuilder.append("Property ").append(formatValue(key)).append(" was removed\n");

                case "changed" -> stringBuilder.append("Property ").append(formatValue(key))
                        .append(" was updated. ").append("From ").append(formatValue(oldValue))
                        .append(" to ").append(formatValue(newValue)).append("\n");

                default -> {
                }
            }
        }
        return stringBuilder.toString().trim();
    }

    public static String formatValue(Object value) {

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value == null) {
            return null;
        }
        return value.toString();
    }
}
