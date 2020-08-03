package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.*;

public class AppTest {
    @org.junit.jupiter.api.Test
    public void existsFile() { // файл существует
        File file = new File("data.json");
        Assertions.assertTrue(file.exists());
    }

    @org.junit.jupiter.api.Test
    public void jsonNotNull() { // файл не пустой
        File file = new File("data.json");
        Assertions.assertNotNull(file);
    }

    @org.junit.jupiter.api.Test
    public void equalsFiles() throws IOException { // сравнение двух json по содержимому
        File file = new File("data.json");
        File file2 = new File("data2.json");

        String contentFile1 = getContent(file);
        String contentFile2 = getContent(file2);
        Assertions.assertEquals(contentFile1, contentFile2);
    }

    public static String getContent(File file) throws IOException { // считывание файла
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
    @Test
    public void containsHuman() throws IOException { // Hamcrest // содержание Inav?
        File file = new File("data.json");
        String contentFile = getContent(file);
        assertThat(contentFile, containsString("Inav"));
    }
    @Test
    public void equalsPath() throws IOException { // Hamcrest // Лежат ли файлы в одной директории?
        File file = new File("data.json");
        File file2 = new File("data.json");
        assertThat(file.getAbsolutePath(), equalTo(file2.getAbsolutePath()));
    }
}
