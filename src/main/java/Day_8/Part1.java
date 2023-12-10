package Day_8;

import Day_8.model.Node;
import lombok.Getter;
import lombok.Setter;
import utils.Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
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

        int result = 0;

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

        Node startNode = nodeMap.get("AAA");

        String[] route = path.split("");
        int routeIndex = 0;
//        int stepsTaken = 0;
        boolean endReached = false;

        Node curr = startNode;

        while (!endReached) {
            String foundpath = route[routeIndex];
            curr = nodeMap.get(curr.getPaths().get(foundpath));
            routeIndex++;
            result++;
            if (routeIndex == route.length) {
                routeIndex = 0;
            }

            if (curr.getId().equals("ZZZ"))
                endReached = true;
        }



        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        return result;
    }
}
