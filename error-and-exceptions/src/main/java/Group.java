import exceptions.*;
import java.util.*;

public class Group extends ArrayList<Student> {
    public Group(Student ...students) throws StudentNotFoundException {
        if (students.length == 0) throw new StudentNotFoundException("Group doesn't have any student");
        this.addAll(Arrays.asList(students));
    }
}
