package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(Map<String, String> diffs, Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder stringBuilder = new StringBuilder("\n");

        for (String key : diffs.keySet()) {

            switch (diffs.get(key)) {

                case "added" -> stringBuilder.append("Property ").append(formatValue(key)).append(" was added with value: ")
                        .append(formatValue(data2.get(key))).append("\n");

                case "deleted" -> stringBuilder.append("Property ").append(formatValue(key)).append(" was removed\n");

                case "changed" -> stringBuilder.append("Property ").append(formatValue(key)).append(" was updated. ")
                        .append("From ").append(formatValue(data1.get(key))).append(" to ")
                        .append(formatValue(data2.get(key))).append("\n");
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
