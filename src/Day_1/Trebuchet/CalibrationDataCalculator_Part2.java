package Day_1.Trebuchet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalibrationDataCalculator_Part2 {
    public static void main(String[] args) throws IOException {
        FileReader in = new FileReader("src/Day_1/Trebuchet/input.txt");
        BufferedReader br = new BufferedReader(in);
        long start = System.currentTimeMillis();
        Pattern numberPattern = Pattern.compile("(\\d|one|two|three|four|five|six|seven|eight|nine)");
        HashMap<String, Integer> textToNumberMapping = new HashMap(){{
            put("one", 1);
            put("two", 2);
            put("three", 3);
            put("four", 4);
            put("five", 5);
            put("six", 6);
            put("seven", 7);
            put("eight", 8);
            put("nine", 9);
        }};
        String line;

        int result = 0;

        while ((line = br.readLine()) != null) {
            boolean isFirstNumber = true;
            int lastMatch = 0;
            int matchStartIndex = 0;
            Matcher numberMatcher = numberPattern.matcher(line);
            while(numberMatcher.find(matchStartIndex)) {
                MatchResult matchResult = numberMatcher.toMatchResult();
                String group = numberMatcher.group(1);
                if (textToNumberMapping.containsKey(group)) {
                    lastMatch = textToNumberMapping.get(group);
                    matchStartIndex = matchResult.end() - 1;
                } else {
                    lastMatch = Integer.valueOf(group);
                    matchStartIndex = matchResult.end();
                }
                if (isFirstNumber)
                    result += lastMatch * 10;
                isFirstNumber = false;
            }
            result += lastMatch;
        }

        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        in.close();
    }
}
