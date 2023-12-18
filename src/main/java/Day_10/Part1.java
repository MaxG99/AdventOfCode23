package Day_10;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Stack;

public class Part1 {
    @Setter@Getter
    Path path;
    int topVal = 1;
    int rightVal = 2;
    int bottomVal = 4;
    int leftVal = 8;

    public static void main(String[] args) throws IOException {
       Part1 part1 = new Part1();
       part1.setPath(Utils.getPathToFile(part1));
       part1.run();
    }

    public int run() throws IOException {
        long start = System.currentTimeMillis();
        String line;

        int result = 0;
        Point startPoint = null;


        List<String> strings = Files.readAllLines(path);
        int[][] map = new int[strings.size()][strings.get(0).length()];
        for (int i = 0; i < strings.size(); i++) {
            String[] pipesPerLine = strings.get(i).split("");
            for (int j = 0; j < pipesPerLine.length; j++) {
                String symbol = pipesPerLine[j];
                if (symbol.equals("S")) {
                    startPoint = new Point(j, i);
                }
                map[i][j] = getPipeVal(symbol);
            }
        }

        map[startPoint.y][startPoint.x] = getStartPointVal(startPoint, map);

        Stack<Point> visited = new Stack<>();
        Point curr = startPoint;
        visited.push(startPoint);
        int lastNodeDir = 0;

        while (visited.size() == 1 || !curr.equals(startPoint)) {
            int currVal = map[curr.y][curr.x];
            if (connectsTop(currVal) && lastNodeDir != topVal) {
                lastNodeDir = bottomVal;
                curr = new Point(curr.x, curr.y-1);
                visited.push(curr);
                continue;
            }
            if (connectsRight(currVal) && lastNodeDir != rightVal) {
                lastNodeDir = leftVal;
                curr = new Point(curr.x+1, curr.y);
                visited.push(curr);
                continue;
            }
            if (connectsBottom(currVal) && lastNodeDir != bottomVal) {
                lastNodeDir = topVal;
                curr = new Point(curr.x, curr.y+1);
                visited.push(curr);
                continue;
            }
            if (connectsLeft(currVal) && lastNodeDir != leftVal) {
                lastNodeDir = rightVal;
                curr = new Point(curr.x-1, curr.y);
                visited.push(curr);
            }
        }

        result = visited.size() / 2;


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        return result;
    }

    private int getStartPointVal(Point startPoint, int[][] map) {
        int startPointVal = 0;
        Point top = null;
        Point right = null;
        Point bottom = null;
        Point left = null;
        if (startPoint.y > 0)
            top = new Point(startPoint.x, startPoint.y-1);
        if (startPoint.y < map.length-1)
            bottom = new Point(startPoint.x, startPoint.y+1);
        if (startPoint.x > 0)
            left = new Point(startPoint.x - 1, startPoint.y);
        if (startPoint.x < map[0].length-1)
            right = new Point(startPoint.x + 1, startPoint.y);
        if (top != null && connectsBottom(map[top.y][top.x]))
            startPointVal += topVal;
        if (right != null && connectsLeft(map[right.y][right.x]))
            startPointVal += rightVal;
        if (bottom != null && connectsTop(map[bottom.y][bottom.x]))
            startPointVal += bottomVal;
        if (left != null && connectsRight(map[left.y][left.x]))
            startPointVal += leftVal;
        return startPointVal;
    }

    public boolean connectsTop(int pointVal) {
        return pointVal == 3 || pointVal == 5 || pointVal == 9;
    }
    public boolean connectsRight(int pointVal) {
        return pointVal == 3 || pointVal == 6 || pointVal == 10;
    }
    public boolean connectsBottom(int pointVal) {
        return pointVal == 5 || pointVal == 6 || pointVal == 12;
    }
    public boolean connectsLeft(int pointVal) {
        return pointVal == 9 || pointVal == 10 || pointVal == 12;
    }

    public int getPipeVal(String pipe) {
//        assumed the following value map for directions
//         N     1
//       W   E  8 2
//         S     4
//        every symbol has a value from which directing connection can be derived
        switch (pipe) {
            case "|":
                return 5;
            case "-":
                return 10;
            case "J":
                return 9;
            case "L":
                return 3;
            case "7":
                return 12;
            case "F":
                return 6;
            default:
                return 0;
        }
    }
}
