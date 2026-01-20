import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();

        Course cs101 = new Course("CS101", "Intro to Programming", 2);
        Course cs102 = new Course("CS102", "Data Structures", 2);
        cs102.addPrerequisite("CS101");

        courses.add(cs101);
        courses.add(cs102);

        List<String> studentCompletedCourses = new ArrayList<>();
        String studentId = "S001";

        boolean enrolled1 = cs101.enrollStudent(studentId, studentCompletedCourses);
        System.out.println("Enrolled in CS101: " + enrolled1);

        studentCompletedCourses.add("CS101");

        boolean enrolled2 = cs102.enrollStudent(studentId, studentCompletedCourses);
        System.out.println("Enrolled in CS102: " + enrolled2);

        System.out.println("Students in CS101: " + cs101.getEnrolledStudents());
        System.out.println("Students in CS102: " + cs102.getEnrolledStudents());

        cs101.dropStudent(studentId);
        System.out.println("After drop, CS101 students: " + cs101.getEnrolledStudents());
    }
}
