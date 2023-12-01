package Day_1.Trebuchet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalibrationDataCalculator_Part1 {
    public static void main(String[] args) throws IOException {
        FileReader in = new FileReader("src/Day_1/Trebuchet/input.txt");
        BufferedReader br = new BufferedReader(in);
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

        System.out.println(String.format("Result was: %s", result));
        long end = System.currentTimeMillis();
        System.out.println(String.format("Calculation took %d Milliseconds",(end - start)));
        in.close();
    }
}
