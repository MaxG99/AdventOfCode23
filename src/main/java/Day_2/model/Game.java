package Day_2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Game {
    public int id;
    public List<Set<CubeInfo>> cubeSetList = new ArrayList<>();

    public Game(int id) {
        this.id = id;
    }
}