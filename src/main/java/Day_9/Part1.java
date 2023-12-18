package Day_9;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
        for (String line : lines) {
            int nextNumber = 0;
            ArrayList<Integer[]> predictionArrayList = new ArrayList<>();
            predictionArrayList.add(Utils.getNumbersFromString(line).toArray(new Integer[0]));
            addCalculatedPredictionLists(predictionArrayList.get(0), predictionArrayList);
            addPaddingWithZero(predictionArrayList);
            for (int i = predictionArrayList.size()-1; i > 0; i--) {
                Integer[] cur = predictionArrayList.get(i);
                Integer[] prev = predictionArrayList.get(i-1);
                prev[prev.length-1] = prev[prev.length-2] + cur[cur.length-1];
            }
            nextNumber = predictionArrayList.get(0)[predictionArrayList.get(0).length-1];
            result += nextNumber;

        }


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        return result;
    }

    private static void addPaddingWithZero(ArrayList<Integer[]> predictionArrayList) {
        for (int i = 0; i < predictionArrayList.size(); i++) {
            Integer[] integers = predictionArrayList.get(i);
            Integer[] padded = Arrays.copyOf(integers, integers.length +1);
            padded[padded.length-1] = 0;
            predictionArrayList.set(i, padded);
        }
    }

    private static void addCalculatedPredictionLists(Integer[] numbersFromString, ArrayList<Integer[]> predictionArrayList) {
        Integer[] nextList = new Integer[numbersFromString.length-1];
        boolean allProcessed = true;
        for (int i = 0; i < numbersFromString.length-1; i++) {
            int cur = numbersFromString[i];
            int next = numbersFromString[i+1];
            int dif = next - cur;
            nextList[i] = dif;
            if (dif != 0)
                allProcessed = false;
        }
        predictionArrayList.add(nextList);
        if (!allProcessed)
            addCalculatedPredictionLists(nextList, predictionArrayList);
    }
}
