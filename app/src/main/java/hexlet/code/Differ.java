package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(File file1, File file2) throws Exception {
        Map<String, Object> data1 = getData(file1);
        Map<String, Object> data2 = getData(file2);

        Map<String, String> diffMap = genDiff(data1, data2);
        String diffs = diffToString(diffMap, data1, data2);
        return diffs;
    }

    public static Map<String, Object> getData(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(file, Map.class);
        return data;
    }

    public static Map<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            if (!data1.containsKey(key)) {
                result.put(key, "added");
            } else if (!data2.containsKey(key)) {
                result.put(key, "deleted");
            } else if (!data1.get(key).equals(data2.get(key))) {
                result.put(key, "changed");
            } else {
                result.put(key, "unchanged");
            }
        }

        return result;
    }

    public static String diffToString(Map<String, String> diffs, Map<String, Object> data1, Map<String, Object> data2) {
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
