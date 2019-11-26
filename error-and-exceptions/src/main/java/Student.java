import java.util.*;

public class Student extends HashMap<String, Double> {
    public Student(String[] subjects, double[] averageMark) {
        for (int i = 0; i < subjects.length; i++) {
            this.put(subjects[i], averageMark[i]);
        }
    }
}
