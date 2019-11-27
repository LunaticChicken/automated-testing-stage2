import exceptions.*;
import java.util.*;

public class Student extends HashMap<String, Double> {
    public Student(String[] subjects, double[] averageMark) throws MarkOutOfBoundsException, SubjectNotFoundException {
        if (subjects.length == 0) throw new SubjectNotFoundException("Student doesn't have any subject");
        for (int i = 0; i < subjects.length; i++) {
            if (averageMark[i] < 0 || averageMark[i] > 10)
                throw new MarkOutOfBoundsException("Mark is either less than 0 or more than 10");
            this.put(subjects[i], averageMark[i]);
        }
    }
}
