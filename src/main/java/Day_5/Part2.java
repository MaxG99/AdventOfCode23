package Day_5;

import Day_5.model.Mapping;
import Day_5.model.SeedListing;
import Day_5.model.SrcTargetMap;
import lombok.Getter;
import lombok.Setter;
import utils.Utils;
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

    public long run() throws IOException {
        long start = System.currentTimeMillis();
        String line;

        long result = Long.MAX_VALUE;
        List<String> strings = Files.readAllLines(path);

        List<Long> seeds = Utils.getLongNumbersFromString(strings.get(0).split(":")[1]);
        List<SeedListing> seedListings = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i+=2) {
            seedListings.add(new SeedListing(seeds.get(i), seeds.get(i+1)));
        }

        List<SrcTargetMap> maps = new ArrayList<>();
        SrcTargetMap current = null;

        for (int i = 1; i < strings.size(); i++) {
            line = strings.get(i);
            if (line.trim().isEmpty())
                continue;
            if (line.contains("map")) {
                SrcTargetMap newMap = new SrcTargetMap();
                current = newMap;
                maps.add(current);
                continue;
            }
            List<Long> mappingStrings = Utils.getLongNumbersFromString(line);
            current.mappings.add(new Mapping(mappingStrings.get(1),mappingStrings.get(0),mappingStrings.get(2)));
        }

        for (SeedListing seedListing : seedListings) {
            System.out.printf("Starting new Sequence of Seeds start: %d count: %d", seedListing.getStart(), seedListing.getCount());
            System.out.println();
            for (long s = seedListing.getStart(); s < seedListing.getStart() + seedListing.getCount(); s++) {
                Long currentMappingNumber = s;
                for (SrcTargetMap map : maps) {
                    for (Mapping mapping : map.mappings) {
                        if(currentMappingNumber >= mapping.getSourceStart()
                                && currentMappingNumber <= (mapping.getSourceStart() + mapping.getRange())) {
                            long offset = currentMappingNumber - mapping.getSourceStart();
                            currentMappingNumber = mapping.getTargetStart() + offset;
                            break;
                        }
                    }
                }
                if (currentMappingNumber < result)
                    result = currentMappingNumber;
            }
        }


        System.out.printf("Result was: %s%n", result);
        long end = System.currentTimeMillis();
        System.out.printf("Calculation took %d Milliseconds%n",(end - start));

        return result;
    }
}
