package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getDataByFile(File file) throws Exception {
        String fileType = getTypeByFile(file);
        ObjectMapper mapper;
        switch (fileType) {
            case "yaml" -> mapper = new YAMLMapper(new YAMLFactory());
            default -> mapper = new ObjectMapper();
        }
        Map<String, Object> result = mapper.readValue(Files.readString(file.toPath()), Map.class);
        return result;
    }

    public static String getTypeByFile(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.indexOf(".") + 1);
    }
}
