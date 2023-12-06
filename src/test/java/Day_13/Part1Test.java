package Day_13;




import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

class Part1Test {

    static Part1 part1 = new Part1();

    @BeforeAll
    static void setup() throws FileNotFoundException {
        part1.setBr(Utils.getBufferedReaderForTest(new Part1Test()));
    }

    @Test
    void run() throws IOException {
        Assertions.assertEquals(0, part1.run());
    }
}