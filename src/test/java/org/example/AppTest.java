package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.*;
import java.util.List;

public class AppTest {

    @org.junit.jupiter.api.Test
    public void existsFile() { // файл существует
        App.writeJsonFile("[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}," +
                "{\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]");
        File file = new File("data2.json");
        Assertions.assertTrue(file.exists());
    }

    @org.junit.jupiter.api.Test
    public void jsonNotNull() throws IOException, SAXException, ParserConfigurationException { // список не пустой
        List<Employee> list = App.parseXML("data.xml");
        Assertions.assertTrue(list.size() > 0);
    }

    @org.junit.jupiter.api.Test
    public void fileNotFound() throws IOException, ParserConfigurationException, SAXException { // некорректный входной файл
        List<Employee> list = App.parseXML("uncorrect_file.xml");
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    public void collectionSize() throws IOException, ParserConfigurationException, SAXException { // Hamcrest // размерность
        List<Employee> listXML = App.parseXML("data.xml");
        assertThat(listXML, hasSize(2));
    }

    @Test
    public void haveHuman() { // Hamcrest // Есть ли
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> listCSV = App.parseCSV(columnMapping, "data.csv");
        assertThat(listCSV.get(1).firstName.equals("Inav"), equalTo(true));
    }
}
