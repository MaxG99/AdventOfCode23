package Day_1;

import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalibrationDataCalculator_Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Utils.getBufferedReaderForInput(new CalibrationDataCalculator_Part1());
        long start = System.currentTimeMillis();
        Pattern numberPattern = Pattern.compile("(\\d)");
        String line;

        int result = 0;

        while ((line = br.readLine()) != null) {
            boolean isFirstNumber = true;
            int lastMatch = 0;
            Matcher numberMatcher = numberPattern.matcher(line);
            while(numberMatcher.find()) {
                lastMatch = Integer.valueOf(numberMatcher.group(1));
                if (isFirstNumber)
                    result += lastMatch * 10;
                isFirstNumber = false;
            }
            result += lastMatch;
        }

        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        br.close();
    }
}
