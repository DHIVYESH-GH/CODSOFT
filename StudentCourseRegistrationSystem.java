import java.util.*;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

public class StudentCourseRegistrationSystem {
    private static Map<String, Course> courses = new HashMap<>();
    private static Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeCourses();
        initializeStudents();

        while (true) {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listAvailableCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    viewRegisteredCourses(scanner);
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeCourses() {
        courses.put("CS101", new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer science.", 30, "Mon & Wed 10:00 AM"));
        courses.put("CS102", new Course("CS102", "Data Structures", "Study various data structures.", 25, "Tue & Thu 2:00 PM"));
        courses.put("CS103", new Course("CS103", "Algorithms", "Understand algorithm design and analysis.", 20, "Fri 11:00 AM"));
    }

    private static void initializeStudents() {
        students.put("S001", new Student("S001", "Alice"));
        students.put("S002", new Student("S002", "Bob"));
    }

    private static void listAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) {
            System.out.println("Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + course.getAvailableSlots());
            System.out.println("-------------------------");
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentID = scanner.nextLine();
        Student student = students.get(studentID);

        if (student == null) {
            System.out.println("Invalid student ID.");
            return;
        }

        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Invalid course code.");
            return;
        }

        if (course.enrollStudent()) {
            student.registerCourse(courseCode);
            System.out.println("Successfully registered for the course " + course.getTitle() + ".");
        } else {
            System.out.println("Registration failed. No available slots.");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentID = scanner.nextLine();
        Student student = students.get(studentID);

        if (student == null) {
            System.out.println("Invalid student ID.");
            return;
        }

        System.out.print("Enter the course code to drop: ");
        String courseCode = scanner.nextLine();

        if (!student.getRegisteredCourses().contains(courseCode)) {
            System.out.println("You are not registered for this course.");
            return;
        }

        Course course = courses.get(courseCode);
        if (course != null) {
            course.dropStudent();
            student.dropCourse(courseCode);
            System.out.println("Successfully dropped the course " + course.getTitle() + ".");
        } else {
            System.out.println("Invalid course code.");
        }
    }

    private static void viewRegisteredCourses(Scanner scanner) {
        System.out.print("Enter your student ID: ");
        String studentID = scanner.nextLine();
        Student student = students.get(studentID);

        if (student == null) {
            System.out.println("Invalid student ID.");
            return;
        }

        System.out.println("\nRegistered Courses:");
        for (String courseCode : student.getRegisteredCourses()) {
            Course course = courses.get(courseCode);
            if (course != null) {
                System.out.println("Code: " + course.getCourseCode() + ", Title: " + course.getTitle());
            }
        }
    }
}

