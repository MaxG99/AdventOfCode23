package Day_7;

import lombok.Getter;
import lombok.Setter;
import utils.Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    @Setter@Getter
    Path path;

    public static void main(String[] args) throws IOException {
        Part1 part1 = new Part1();
        part1.setPath(Utils.getPathToFile(part1));
        part1.run();
    }

    public long run() throws IOException {
        long start = System.currentTimeMillis();

        long result = 0;

        List<String> hands = Files.readAllLines(path);

        hands.sort((o1, o2) -> compare(o1, o2));

        for (int i = 0; i < hands.size(); i++) {
            int bid = Integer.parseInt(hands.get(i).split("\\s+")[1].trim());
            result+=bid*(i+1);
        }


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));
        return result;
    }

    private int compare(String v1, String v2) {

        HashMap<String, Integer> cardCountMapO1 = getCardCountMap(v1.split(" ")[0]);
        List<Integer> hand1 = cardCountMapO1.values().stream().sorted().toList();
        HashMap<String, Integer> cardCountMapO2 = getCardCountMap(v2.split(" ")[0]);
        List<Integer> hand2 = cardCountMapO2.values().stream().sorted().toList();

        if (cardCountMapO1.size() > cardCountMapO2.size())
            return -1;

        if (cardCountMapO1.size() < cardCountMapO2.size())
            return 1;

        int handCompare = hand1.get(hand1.size() - 1).compareTo(hand2.get(hand2.size() - 1));

        if (handCompare == 0) {
            String[] splitHand1 = v1.split("");
            String[] splitHand2 = v2.split("");

            for (int i = 0; i < splitHand1.length; i++) {
                if (getCardVal(splitHand1[i]) > getCardVal(splitHand2[i]))
                    return 1;
                if (getCardVal(splitHand1[i]) < getCardVal(splitHand2[i]))
                    return -1;
            }
        }

        return handCompare;

    }

    private int getCardVal(String card) {
        switch (card) {
            case "A": return 14;
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            case "T": return 10;
            case "9": return 9;
            case "8": return 8;
            case "7": return 7;
            case "6": return 6;
            case "5": return 5;
            case "4": return 4;
            case "3": return 3;
            case "2": return 2;
            default: return 0;
        }
    }

    private HashMap<String, Integer> getCardCountMap(String hand) {
        HashMap<String, Integer> cardCount = new HashMap<>();

        for (String card : hand.split("")) {
            cardCount.merge(card, 1, (integer, integer2) -> integer + 1);
        }
        return cardCount;
    }
}
