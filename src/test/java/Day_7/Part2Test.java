package Day_7;


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
        part2.setBr(Utils.getBufferedReaderForTest(new Part2Test()));
    }

    @Test
    void run() throws IOException {
        Assertions.assertEquals(0, part2.run());
    }
}