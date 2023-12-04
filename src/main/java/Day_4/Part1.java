package Day_4;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    @Setter
    @Getter
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
            String[] winningOwn = line.split(":")[1].trim().split("\\|");
            List<Integer> winning = Arrays.stream(winningOwn[0].trim().split("\\s+")).map(string -> Integer.parseInt(string.trim())).collect(Collectors.toList());
            List<Integer> own = Arrays.stream(winningOwn[1].trim().split("\\s+")).map(string -> Integer.parseInt(string)).collect(Collectors.toList());
            long hits = own.stream().filter(integer -> winning.contains(integer)).count();

            result += hits > 0 ? 1 << hits -1 : 0;

        }

        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n", (end - start));
        br.close();
        return result;
    }
}
