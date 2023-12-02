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
import java.util.concurrent.atomic.AtomicInteger;

public class Part2 {
    @Setter
    @Getter
    BufferedReader br;

    public static void main(String[] args) throws IOException {
        Part2 part2 = new Part2();
        part2.setBr(Utils.getBufferedReaderForInput(part2));
        part2.run();
    }

    public int run() throws IOException {
        long start = System.currentTimeMillis();
        String line;

        List<Game> gamesList = new ArrayList<>();
        int sumPower = 0;

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
            HashMap<String, Integer> minNumberPerColor = new HashMap<>();
            AtomicInteger power = new AtomicInteger(1);
            for (Set<CubeInfo> cubeInfoSet : game.cubeSetList) {
                for (CubeInfo cubeInfo : cubeInfoSet) {
                    if (!minNumberPerColor.containsKey(cubeInfo.color))
                        minNumberPerColor.put(cubeInfo.color, cubeInfo.count);
                    if (cubeInfo.count > minNumberPerColor.get(cubeInfo.color))
                        minNumberPerColor.put(cubeInfo.color, cubeInfo.count);
                }
            }

            //calculate power
            minNumberPerColor.forEach((s, integer) -> power.set(power.get() * integer));

            sumPower += power.get();
        }

        System.out.printf("Result was: %s%n", sumPower);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n", (end - start));
        br.close();
        return sumPower;
    }
}