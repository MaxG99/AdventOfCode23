package Day_6;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;

public class Part2 {
    @Setter
    @Getter
    BufferedReader br;

    public static void main(String[] args) throws IOException {
        Part2 part2 = new Part2();
        part2.setBr(Utils.getBufferedReaderForInput(part2));
        part2.run();
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
