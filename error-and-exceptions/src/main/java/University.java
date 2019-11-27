import exceptions.*;
import java.util.*;

public class University extends ArrayList<Faculty> {
    public University(Faculty ...faculties) throws FacultyNotFoundException {
        if (faculties.length == 0) throw new FacultyNotFoundException("University doesn't have any faculty");
        this.addAll(Arrays.asList(faculties));
    }

    public double getAverageMarkOfAStudent(Student student) {
        double sum = 0;
        for (Map.Entry<String, Double> subjectAndAverageMark : student.entrySet()) {
            sum += subjectAndAverageMark.getValue();
        }
        return sum/student.size();
    }

    public double getAverageMarkOfASubjectInAGroup(String subject, Group group) {
        double sum = 0;
        for (Student student : group) {
            if (student.containsKey(subject)) sum += student.get(subject);
        }
        return sum/group.size();
    }

    public double getAverageMarkOfASubjectInUniversity(String subject) {
        double sum = 0;
        int amountOfGroups = 0;
        for (Faculty faculty : this) {
            for (Group group : faculty) {
                if (group.get(0).containsKey(subject)) {
                    sum += getAverageMarkOfASubjectInAGroup(subject, group);
                    amountOfGroups++;
                }
            }
        }
        return sum/amountOfGroups;
    }
}
