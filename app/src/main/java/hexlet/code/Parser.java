package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> parse(File file) throws Exception {
        String fileType = getFileType(file);
        ObjectMapper mapper;
        switch (fileType) {
            case "yaml" -> mapper = new YAMLMapper(new YAMLFactory());
            case "json" -> mapper = new JsonMapper();
            default -> mapper = new ObjectMapper();
        }
        TreeMap<String, Object> result = mapper.readValue(Files.readString(file.toPath()), new TypeReference<>() { });
        return result;
    }

    public static String getFileType(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.indexOf(".") + 1);
    }
}
