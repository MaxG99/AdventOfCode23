package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static BufferedReader getBufferedReaderForInput(Object a) throws FileNotFoundException {
        FileReader in = new FileReader("./src/main/java/"+a.getClass().getPackage().getName()+"/input.txt");
        return new BufferedReader(in);
    }

    public static BufferedReader getBufferedReaderForTest(Object a) throws FileNotFoundException {
        FileReader in = new FileReader("./src/test/java/"+a.getClass().getPackage().getName()+ "/" + a.getClass().getSimpleName()+"input.txt");
        return new BufferedReader(in);
    }

    public static Path getPathToFile(Object a) {
        return Path.of("./src/main/java/" + a.getClass().getPackage().getName() + "/input.txt");
    }

    public static Path getPathToTestFile(Object a) {
        return Path.of("./src/test/java/"+a.getClass().getPackage().getName()+ "/" + a.getClass().getSimpleName()+"input.txt");
    }

    public static List<Integer> getNumbersFromString(String numbers) {
        return getNumbersFromString(numbers, "\\s+");
    }
    public static List<Long> getLongNumbersFromString(String numbers) {
        return getLongNumbersFromString(numbers, "\\s+");
    }

    public static List<Integer> getNumbersFromString(String numbers, String divider) {
        return Arrays.stream(numbers.trim().split(divider)).map(string -> Integer.valueOf(string.trim())).toList();
    }

    public static List<Long> getLongNumbersFromString(String numbers, String divider) {
        return Arrays.stream(numbers.trim().split(divider)).map(string -> Long.valueOf(string.trim())).toList();
    }
}
