package _22_01_2024_buffered;

import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
//Сгруппируйте стримы по названию и запишите количество стримов в каждой группе в новый файл.
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\projects\\homework\\src\\_22_01_2024\\f.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("result_task.txt"))) {

            Map<String, Long> streamCounts = reader.lines()
                    .map(line -> line.split(", ")[1])
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            for (Map.Entry<String, Long> entry : streamCounts.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
