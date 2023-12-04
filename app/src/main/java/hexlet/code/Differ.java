package hexlet.code;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = Parser.parse(new File(filePath1));
        Map<String, Object> data2 = Parser.parse(new File(filePath2));

        List<Map<String, Object>> diffMap = genDiff(data1, data2);

        return Formatter.styleByFormat(diffMap, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static List<Map<String, Object>> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keysSet = new TreeSet<>(data1.keySet());
        keysSet.addAll(data2.keySet());

        for (String key : keysSet) {
            Map<String, Object> map = new LinkedHashMap<>();

            if (!data1.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", data2.get(key));
                map.put("status", "added");

            } else if (!data2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", data1.get(key));
                map.put("status", "deleted");

            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                map.put("key", key);
                map.put("oldValue", data1.get(key));
                map.put("newValue", data2.get(key));
                map.put("status", "changed");

            } else {
                map.put("key", key);
                map.put("oldValue", data1.get(key));
                map.put("status", "unchanged");
            }
            result.add(map);
        }
        return result;
    }
}
