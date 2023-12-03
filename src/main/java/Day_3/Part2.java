package Day_3;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    @Setter
    @Getter
    Path path;

    public static void main(String[] args) throws IOException {
        Part2 part2 = new Part2();
        part2.setPath(Utils.getPathToFile(part2));
        part2.run();
    }

    public long run() throws IOException {
        long start = System.currentTimeMillis();
        List<String> lines = Files.readAllLines(path);
        Pattern gearPattern = Pattern.compile("(\\*)");

        long result = 0;

        for (int i = 0; i < lines.size(); i++) {
            Matcher gearMatcher = gearPattern.matcher(lines.get(i));

            int matchStartIndex = 0;

            while (gearMatcher.find(matchStartIndex)) {
                MatchResult matchResult = gearMatcher.toMatchResult();
                int endIndex = matchResult.end();
                matchStartIndex = endIndex;

                List<Integer> surroundingNumbers = getSurroundingNumbers(lines, i, matchResult);
                if (surroundingNumbers.size() == 2) {
                    result += surroundingNumbers.get(0) * surroundingNumbers.get(1);
                }

            }
        }


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n", (end - start));
        return result;
    }

    public List<Integer> getSurroundingNumbers(List<String> lines, int lineIndex, MatchResult matchResult) {
        List<Integer> numbers = new ArrayList<>();
        int prevLine = lineIndex - 1;
        int nextLine = lineIndex + 1;

        int searchIndexStart = matchResult.start() - 3 < 0 ? 0 : matchResult.start() - 3;
        int searchIndexEnd = matchResult.end() + 3 > lines.get(0).length() ? lines.get(0).length() - 1 : matchResult.end() + 3;

        addNumbersForLine(lines.get(lineIndex), searchIndexStart, searchIndexEnd, matchResult, numbers);
        if (prevLine >= 0)
            addNumbersForLine(lines.get(prevLine), searchIndexStart, searchIndexEnd, matchResult, numbers);
        if (nextLine < lines.size())
            addNumbersForLine(lines.get(nextLine), searchIndexStart, searchIndexEnd, matchResult, numbers);

        return numbers;
    }

    private void addNumbersForLine(String line, int searchIndexStart, int searchIndexEnd, MatchResult foundGear, List<Integer> numbers) {
        Pattern numberPattern = Pattern.compile("(\\d+)");
        Matcher numberMatcher = numberPattern.matcher(line.substring(searchIndexStart, searchIndexEnd));
        while (numberMatcher.find()) {
            MatchResult matchResult = numberMatcher.toMatchResult();
            if (isAdjacent(matchResult.start()) || isAdjacent(matchResult.end() - 1)) {
                numbers.add(Integer.parseInt(matchResult.group(1)));
            }
        }
    }

    private boolean isAdjacent(int number) {
        return number >= 2 && number <= 4;
    }
}
