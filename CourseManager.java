import java.util.ArrayList;

public class CourseManager {
    private ArrayList<Course> courses;

    public CourseManager() {
        courses = new ArrayList<>();
    }

    public boolean addCourse(Course course) {
        if (!course.isValid()) {
            return false;
        }
        if (getCourseByCode(course.getCourseCode()) != null) {
            return false;
        }
        courses.add(course);
        return true;
    }

    public boolean updateCourse(String courseCode, String newName, String newDepartment, String newInstructor, int newCredits, int newCapacity) {
        Course course = getCourseByCode(courseCode);
        if (course == null) {
            return false;
        }
        course.setCourseName(newName);
        course.setDepartment(newDepartment);
        course.setInstructorId(newInstructor);
        course.setCredits(newCredits);
        course.setCapacity(newCapacity);
        return course.isValid();
    }

    public boolean deleteCourse(String courseCode) {
        Course course = getCourseByCode(courseCode);
        if (course == null) {
            return false;
        }
        courses.remove(course);
        return true;
    }

    public Course getCourseByCode(String courseCode) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseCode().equals(courseCode)) {
                return courses.get(i);
            }
        }
        return null;
    }

    public ArrayList<Course> filterByDepartment(String department) {
        ArrayList<Course> result = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getDepartment().equalsIgnoreCase(department)) {
                result.add(courses.get(i));
            }
        }
        return result;
    }

    public ArrayList<Course> filterByInstructor(String instructorId) {
        ArrayList<Course> result = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getInstructorId().equalsIgnoreCase(instructorId)) {
                result.add(courses.get(i));
            }
        }
        return result;
    }

    public ArrayList<Course> getAllCourses() {
        return courses;
    }
}
