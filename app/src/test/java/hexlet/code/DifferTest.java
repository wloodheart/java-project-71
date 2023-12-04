package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    String expected = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    String expectedPlain = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";

    @Test
    void testGenerate() throws Exception {

        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");

        assertThat(Differ.generate(filePath1.toFile(), filePath2.toFile())).isEqualTo(expected);
    }

    @Test
    void testGenerateYml() throws Exception {

        Path filePath1 = Path.of("src/test/resources/file1.yaml");
        Path filePath2 = Path.of("src/test/resources/file2.yaml");

        assertThat(Differ.generate(filePath1.toFile(), filePath2.toFile())).isEqualTo(expected);
    }

    @Test
    void testPlainGenerate() throws Exception {

        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");

        assertThat(Differ.generate(filePath1.toFile(), filePath2.toFile(), "plain")).isEqualTo(expectedPlain);
    }

    @Test
    void testPlainGenerateYml() throws Exception {

        Path filePath1 = Path.of("src/test/resources/file1.yaml");
        Path filePath2 = Path.of("src/test/resources/file2.yaml");

        assertThat(Differ.generate(filePath1.toFile(), filePath2.toFile(), "plain")).isEqualTo(expectedPlain);
    }

    @Test
    void testJsonGenerate() throws Exception {

        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");

        String actual = Differ.generate(filePath1.toFile(), filePath2.toFile(), "json");
        String expectedJson = Files.readString(Path.of("src/test/resources/expected.json"));

        assertThat(actual).isEqualTo(expectedJson);
    }

    @Test
    void testJsonGenerateYml() throws Exception {

        Path filePath1 = Path.of("src/test/resources/file1.yaml");
        Path filePath2 = Path.of("src/test/resources/file2.yaml");

        String actual = Differ.generate(filePath1.toFile(), filePath2.toFile(), "json");
        String expectedJson = Files.readString(Path.of("src/test/resources/expectedYaml.json"));

        assertThat(actual).isEqualTo(expectedJson);
    }
}
