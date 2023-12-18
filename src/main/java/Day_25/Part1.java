package Day_25;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class Part1 {
    @Setter@Getter
    Path path;

    public static void main(String[] args) throws IOException {
       Part1 part1 = new Part1();
       part1.setPath(Utils.getPathToFile(part1));
       part1.run();
    }

    public int run() throws IOException {
        long start = System.currentTimeMillis();
        String line;

        int result = 0;


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        return result;
    }
}
