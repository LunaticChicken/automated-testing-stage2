import java.util.*;

public class Faculty extends ArrayList<Group>{
    public Faculty(Group ...groups) {
        this.addAll(Arrays.asList(groups));
    }
}
