package Day_8;

import Day_8.model.Node;
import lombok.Getter;
import lombok.Setter;
import utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Part2 {
    @Setter@Getter
    Path path;

    public static void main(String[] args) throws IOException {
        Part2 part2 = new Part2();
        part2.setPath(Utils.getPathToFile(part2));
        part2.run();
    }

    public long run() throws IOException {
        long start = System.currentTimeMillis();

        long result = 0;

        List<String> lines = Files.readAllLines(path);
        HashMap<String, Node> nodeMap = new HashMap<>();
        String path = lines.get(0);

        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            String id = line.substring(0,3);
            String left = line.substring(7,10);
            String right = line.substring(12,15);
            nodeMap.put(id, new Node(id, left, right));
        }

        String[] route = path.split("");

        List<Node> curr = new ArrayList<>();
        nodeMap.keySet().forEach(key -> {
            if (key.endsWith("A")) {
                curr.add(nodeMap.get(key));
            }
        });

        for (Node node : curr) {
            long steps = stepsToDestination(route, nodeMap, node);
            if (result == 0) {
                result =steps;
            } else {
                result = lcm(result, steps);
            }
        }


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        return result;
    }

    public static long lcm(long val1, long val2) {
        if (val1 == 0 || val2 == 0) {
            return 0;
        }
        long higherNumber = Math.max(val1, val2);
        long lowerNumber = Math.min(val1, val2);
        long lcm = higherNumber;
        while (lcm % lowerNumber != 0) {
            lcm += higherNumber;
        }
        return lcm;
    }

    public long stepsToDestination(String[] route, HashMap<String, Node> nodeHashMap, Node start) {
        boolean endReached = false;
        int routeIndex = 0;
        long result = 0;
        Node currNode = start;
        while (!endReached) {
            String foundpath = route[routeIndex];
            currNode = nodeHashMap.get(currNode.getPaths().get(foundpath));
            routeIndex++;
            result++;
            if (routeIndex == route.length) {
                routeIndex = 0;
            }

            if (currNode.getId().endsWith("Z"))
                endReached = true;
        }

        return result;
    }
}
