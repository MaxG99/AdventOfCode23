package Day_2;

import Day_2.model.CubeInfo;
import Day_2.model.Game;
import lombok.Getter;
import lombok.Setter;
import utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Part1 {
    @Setter
    @Getter
    BufferedReader br;

    public static void main(String[] args) throws IOException {
        Part1 part1 = new Part1();
        part1.setBr(Utils.getBufferedReaderForInput(part1));
        part1.run();
    }

    public int run() throws IOException {
        long start = System.currentTimeMillis();
        String line;

        HashMap<String, Integer> loadedCubes = new HashMap<String, Integer>() {{
            put("red", 12);
            put("green", 13);
            put("blue", 14);
        }};

        List<Game> gamesList = new ArrayList<>();
        int sumPossibleGameIDs = 0;

        //parse Data
        while ((line = br.readLine()) != null) {

            String[] gameSetSplit = line.split(":");
            Game game = new Game(Integer.parseInt(gameSetSplit[0].split(" ")[1]));
            String[] sets = gameSetSplit[1].split(";");
            for (String set : sets) {
                Set<CubeInfo> cubeInfoSet = new HashSet<>();
                String[] cubeInfos = set.split(",");

                for (String cubeInfoMapping : cubeInfos) {
                    String[] cubeInfoSplit = cubeInfoMapping.trim().split(" ");
                    CubeInfo cubeInfo = new CubeInfo(Integer.parseInt(cubeInfoSplit[0]), cubeInfoSplit[1]);
                    cubeInfoSet.add(cubeInfo);
                }
                game.cubeSetList.add(cubeInfoSet);
            }
            gamesList.add(game);
        }

        //processing
        for (Game game : gamesList) {
            boolean isPossible = true;
            for (Set<CubeInfo> cubeInfoSet : game.cubeSetList) {
                for (CubeInfo cubeInfo : cubeInfoSet) {
                    if (loadedCubes.get(cubeInfo.color) < cubeInfo.count)
                        isPossible = false;
                }
            }

            if (isPossible) {
                sumPossibleGameIDs += game.id;
            }
        }

        System.out.printf("Result was: %s%n", sumPossibleGameIDs);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n", (end - start));
        br.close();
        return sumPossibleGameIDs;
    }
}
