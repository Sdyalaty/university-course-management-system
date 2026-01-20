import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String courseName;
    private int capacity;
    private List<String> enrolledStudents;
    private List<String> prerequisites;

    public Course(String courseCode, String courseName, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public boolean enrollStudent(String studentId, List<String> completedCourses) {
        if (isFull()) return false;
        if (!completedCourses.containsAll(prerequisites)) return false;
        if (enrolledStudents.contains(studentId)) return false;
        enrolledStudents.add(studentId);
        return true;
    }

    public boolean dropStudent(String studentId) {
        return enrolledStudents.remove(studentId);
    }

    public void addPrerequisite(String courseCode) {
        if (!prerequisites.contains(courseCode)) {
            prerequisites.add(courseCode);
        }
    }

    public void removePrerequisite(String courseCode) {
        prerequisites.remove(courseCode);
    }
}
