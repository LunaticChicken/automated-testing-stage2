import exceptions.*;

public class Main {
    public static void main(String[] args) throws MarkOutOfBoundsException, SubjectNotFoundException, StudentNotFoundException, GroupNotFoundException, FacultyNotFoundException {
        String[] subjects1 = {"ИКГ", "ИПЭ", "Физика", "ЭТТ"};
        String[] subjects2 = {"ВМиП", "ОАиП", "ИТАС", "ТОЭ"};
        String[] subjects3 = {"ВТвИ", "ОАиП", "Физика", "Математика"};

        Student student1 = new Student(subjects1, new double[]{6.5, 7.0, 5.9, 8.9});
        Student student2 = new Student(subjects1, new double[]{5.5, 6.0, 8.9, 4.9});
        Student student3 = new Student(subjects1, new double[]{6.2, 7.3, 5.5, 8.0});
        Student student4 = new Student(subjects2, new double[]{6.5, 7.0, 5.9, 8.1});
        Student student5 = new Student(subjects2, new double[]{5.5, 6.0, 8.9, 6.9});
        Student student6 = new Student(subjects2, new double[]{6.2, 7.3, 5.5, 8.0});
        Student student7 = new Student(subjects3, new double[]{8.5, 7.0, 5.9, 8.9});
        Student student8 = new Student(subjects3, new double[]{5.5, 6.0, 7.6, 4.9});
        Student student9 = new Student(subjects3, new double[]{6.2, 9.3, 5.5, 6.0});
        Student student10 = new Student(subjects2, new double[]{6.5, 5.6, 4.2, 8.9});
        Student student11 = new Student(subjects2, new double[]{7.5, 9.7, 8.4, 4.9});
        Student student12 = new Student(subjects2, new double[]{6.2, 5.3, 5.5, 8.0});

        Group group1 = new Group(student1, student2, student3);
        Group group2 = new Group(student4, student5, student6);
        Group group3 = new Group(student7, student8, student9);
        Group group4 = new Group(student10, student11, student12);

        Faculty faculty1 = new Faculty(group1, group2);
        Faculty faculty2 = new Faculty(group3, group4);

        University university = new University(faculty1, faculty2);

        System.out.println("An average mark of a student3 = " + university.getAverageMarkOfAStudent(student3));
        System.out.println("An average mark of 'ОАиП' subject in group2 = " +
                university.getAverageMarkOfASubjectInAGroup("ОАиП", group2));
        System.out.println("An average mark of 'Физика' subject in university = " +
                university.getAverageMarkOfASubjectInUniversity("Физика"));

        University university0 = new University();
    }
}