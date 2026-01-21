import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String courseName;
    private String department;
    private String instructorId;
    private int credits;
    private int capacity;
    private ArrayList<String> prerequisites;
    private ArrayList<String> enrolledStudents;

    public Course(String courseCode, String courseName, String department, String instructorId, int credits, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.department = department;
        this.instructorId = instructorId;
        this.credits = credits;
        this.capacity = capacity;
        this.prerequisites = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDepartment() {
        return department;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public int getCredits() {
        return credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean isValid() {
        if (credits <= 0) return false;
        if (capacity <= 0) return false;
        if (instructorId == null || instructorId.equals("")) return false;
        return true;
    }

    public void addPrerequisite(String courseCode) {
        if (!prerequisites.contains(courseCode)) {
            prerequisites.add(courseCode);
        }
    }

    public boolean hasPrerequisites(ArrayList<String> completedCourses) {
        for (int i = 0; i < prerequisites.size(); i++) {
            if (!completedCourses.contains(prerequisites.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        if (enrolledStudents.size() >= capacity) {
            return true;
        }
        return false;
    }

    public boolean enrollStudent(String studentId, ArrayList<String> completedCourses) {
        if (isFull()) return false;
        if (!hasPrerequisites(completedCourses)) return false;
        if (enrolledStudents.contains(studentId)) return false;
        enrolledStudents.add(studentId);
        return true;
    }

    public boolean dropStudent(String studentId) {
        return enrolledStudents.remove(studentId);
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
