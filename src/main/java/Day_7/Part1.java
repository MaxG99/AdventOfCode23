package Day_7;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Part1 {
    @Setter@Getter
    BufferedReader br;

    public static void main(String[] args) throws IOException {
       Part1 part1 = new Part1();
       part1.setBr(Utils.getBufferedReaderForInput(part1));
       part1.run();
    }

    public int run() throws IOException {
        long start = System.currentTimeMillis();
        String line;

        int result = 0;

        while ((line = br.readLine()) != null) {
            //TODO: processing
        }

        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        br.close();
        return result;
    }
}
