package Day_3;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    @Setter
    @Getter
    Path path;

    public static void main(String[] args) throws IOException {
        Part1 part1 = new Part1();
        part1.setPath(Utils.getPathToFile(part1));
        part1.run();
    }

    public int run() throws IOException {
        long start = System.currentTimeMillis();
        List<String> lines = Files.readAllLines(path);
        Pattern numberPattern = Pattern.compile("(\\d+)");

        int result = 0;

        for (int i = 0; i < lines.size(); i++) {
            Matcher numberMatcher = numberPattern.matcher(lines.get(i));

            int matchStartIndex = 0;

            while (numberMatcher.find(matchStartIndex)) {
                MatchResult matchResult = numberMatcher.toMatchResult();
                String group = numberMatcher.group(1);
                int startIndex = matchResult.start();
                int endIndex = matchResult.end();
                matchStartIndex = endIndex;

                if (isSymbolAdjacent(lines, i, startIndex  == 0 ? 0: startIndex - 1, endIndex)) {
                    result += Integer.parseInt(group);
                }

            }
        }


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n", (end - start));
        return result;
    }

    public boolean isSymbolAdjacent(List<String> lines, int lineIndex, int startIndex, int endIndex) {
        Pattern symbolPattern = Pattern.compile("[^\\d.]");
        int prevLine = lineIndex - 1;
        int nextLine = lineIndex + 1;
        if (endIndex == lines.get(0).length()) {
            endIndex -= 1;
        }

        if (prevLine >= 0 && symbolPattern.matcher(lines.get(prevLine).substring(startIndex, endIndex + 1)).find())
            return true;
        if (nextLine < lines.size() && symbolPattern.matcher(lines.get(nextLine).substring(startIndex, endIndex + 1)).find())
            return true;
        String line = lines.get(lineIndex);
        String prevAndNext = line.substring(startIndex, startIndex + 1) + line.substring(endIndex, endIndex +1);
        if (symbolPattern.matcher(prevAndNext).find())
            return true;

        return false;
    }
}
