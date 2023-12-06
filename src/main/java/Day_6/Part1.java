package Day_6;

import Day_6.model.TimeDistance;
import lombok.Getter;
import lombok.Setter;
import utils.Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

        int result = 1;

        List<String> strings = Files.readAllLines(path);


        List<Long> times = Utils.getLongNumbersFromString(strings.get(0).split(":")[1]);
        List<Long> distances = Utils.getLongNumbersFromString(strings.get(1).split(":")[1]);
        List<TimeDistance> timeDistanceList = new ArrayList<>();

        for (int i = 0; i < times.size(); i++) {
            timeDistanceList.add(new TimeDistance(times.get(i), distances.get(i)));
        }

        for (TimeDistance timeDistance : timeDistanceList) {
            int canbeat = 0;
            for (int timeHold = 0; timeHold <= timeDistance.getTime(); timeHold++) {
                if (timeHold * (timeDistance.getTime() - timeHold) > timeDistance.getDistance()) {
                    canbeat++;
                }
            }
            result *= canbeat;
        }


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        return result;
    }
}
