import java.util.*;

public class Group extends ArrayList<Student> {
    public Group(Student ...students) {
        this.addAll(Arrays.asList(students));
    }
}
