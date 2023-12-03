package Day_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

class Part2Test {

    static Part2 part2 = new Part2();

    @BeforeAll
    static void setup() throws FileNotFoundException {
        part2.setPath(Utils.getPathToTestFile(new Part1Test()));
    }

    @Test
    void run() throws IOException {
        Assertions.assertEquals(467835, part2.run());
    }
}