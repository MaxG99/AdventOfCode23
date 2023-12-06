package Day_5.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Mapping {

    @Getter
    long sourceStart;
    @Getter
    long targetStart;
    @Getter
    long range;
}
