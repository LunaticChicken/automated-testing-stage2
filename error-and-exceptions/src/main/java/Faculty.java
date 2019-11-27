import exceptions.*;
import java.util.*;

public class Faculty extends ArrayList<Group>{
    public Faculty(Group ...groups) throws GroupNotFoundException {
        if (groups.length == 0) throw new GroupNotFoundException("Faculty doesn't have any group");
        this.addAll(Arrays.asList(groups));
    }
}
