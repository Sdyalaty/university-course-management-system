import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Grade> grades = new ArrayList<>();

        grades.add(new Grade.LetterGrade("S001", "CS101", 'A'));
        grades.add(new Grade.NumericalGrade("S001", "MATH102", 85));
        grades.add(new Grade.PassFailGrade("S001", "ENG103", true));

        double gpa = calculateGPA(grades);

        System.out.println("Grades:");
        for (Grade grade : grades) {
            System.out.println(grade.courseCode + ": " + grade.getDisplayValue());
        }

        System.out.println("GPA: " + gpa);
    }

    public static double calculateGPA(List<Grade> grades) {
        double total = 0;
        int count = 0;

        for (Grade grade : grades) {
            total += grade.getGradePoint();
            count++;
        }

        return count == 0 ? 0.0 : total / count;
    }
}
