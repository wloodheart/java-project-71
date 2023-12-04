package hexlet.code;

import hexlet.code.formatter.Stylish;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {

    public static String generate(File file1, File file2, String format) throws Exception {
        Map<String, Object> data1 = Parser.parse(file1);
        Map<String, Object> data2 = Parser.parse(file2);

        Map<String, String> diffMap = genDiff(data1, data2);
        String diffs = styleByFormat(diffMap, data1, data2, format);
        return diffs;
    }

    public static String generate(File file1, File file2) throws Exception {
        return generate(file1, file2, "stylish");
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
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                result.put(key, "changed");
            } else {
                result.put(key, "unchanged");
            }
        }

        return result;
    }

    public static String styleByFormat(Map<String, String> diffs, Map<String, Object> data1, Map<String, Object> data2, String format) {
        switch (format) {
            default -> {
                return Stylish.format(diffs, data1, data2);
            }
        }
    }
}
