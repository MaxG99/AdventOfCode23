package Day_8.model;

import lombok.Getter;

import java.util.HashMap;

public class Node {
    @Getter
    String id;
    @Getter
    HashMap<String, String> paths = new HashMap<>();

    public Node(String id, String left, String right) {
        this.id = id;
        paths.put("L", left);
        paths.put("R", right);
    }
}
