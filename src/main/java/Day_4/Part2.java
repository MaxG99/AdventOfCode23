package Day_4;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {
    @Setter
    @Getter
    Path path;

    public static void main(String[] args) throws IOException {
        Part2 part2 = new Part2();
        part2.setPath(Utils.getPathToFile(part2));
        part2.run();
    }

    public int run() throws IOException {
        long start = System.currentTimeMillis();
        int result = 0;

        List<String> lines = Files.readAllLines(path);

        for (int i = 0; i < lines.size(); i++) {
            String[] winningOwn = lines.get(i).split(":")[1].trim().split("\\|");
            List<Integer> winning = Arrays.stream(winningOwn[0].trim().split("\\s+")).map(string -> Integer.parseInt(string.trim())).collect(Collectors.toList());
            List<Integer> own = Arrays.stream(winningOwn[1].trim().split("\\s+")).map(string -> Integer.parseInt(string)).collect(Collectors.toList());

            long hits = own.stream().filter(integer -> winning.contains(integer)).count();

            result += 1 + recursiveLottery(lines, i, hits);

        }

        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n", (end - start));
        return result;
    }

    public int recursiveLottery(List<String> lines, int currIndex, long wins) {

        int sumCards = 0;

        for (int i = 1; i <= wins; i++) {
            int nextCardIndex = currIndex + i;
            if (nextCardIndex >= lines.size())
                continue;
            String[] winningOwn = lines.get(nextCardIndex).split(":")[1].trim().split("\\|");
            List<Integer> winning = Arrays.stream(winningOwn[0].trim().split("\\s+")).map(string -> Integer.parseInt(string.trim())).collect(Collectors.toList());
            List<Integer> own = Arrays.stream(winningOwn[1].trim().split("\\s+")).map(string -> Integer.parseInt(string)).collect(Collectors.toList());

            long hits = own.stream().filter(integer -> winning.contains(integer)).count();

            sumCards += 1 + recursiveLottery(lines, nextCardIndex, hits);
        }

        return sumCards;
    }
}
