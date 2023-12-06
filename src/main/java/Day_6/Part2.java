package Day_6;

import Day_6.model.TimeDistance;
import lombok.Getter;
import lombok.Setter;
import utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        String line;

        int result = 1;

        List<String> strings = Files.readAllLines(path);

        List<Long> times = Utils.getLongNumbersFromString(strings.get(0).split(":")[1].replace(" ", ""));
        List<Long> distances = Utils.getLongNumbersFromString(strings.get(1).split(":")[1].replace(" ", ""));
        List<TimeDistance> timeDistanceList = new ArrayList<>();

        for (int i = 0; i < times.size(); i++) {
            timeDistanceList.add(new TimeDistance(times.get(i), distances.get(i)));
        }

        for (TimeDistance timeDistance : timeDistanceList) {
            int canbeat = 0;
            for (long timeHold = 0; timeHold <= timeDistance.getTime(); timeHold++) {
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
