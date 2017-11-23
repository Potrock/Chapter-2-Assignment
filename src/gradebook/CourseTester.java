package gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CourseTester {

    public static void main(String[] args) {
        Course ics4u = new Course("ICS4U", "Parth Patel", 100);
        ics4u.addStudent("Jackson Brajer");
        ics4u.addStudents("Patrick Blais", "Kyle Cheng", "Dylan Lu", "Andre Corona", "Sawyer Tang");
        ics4u.addGradeToStudent("Jackson Brajer", "Unit 1 Test", 25.0/24.0);
        System.out.println(ics4u);
        Course copy = copyCourse(ics4u);
        copy.addGradeToStudent("Jackson Brajer", "New Unit", 30.0/35.0);
        copy.addGradeToStudent("Patrick Blais", "New Unit", 79.0/85.0);
        copy.addGradeToStudent("Patrick Blais", "Final Exam", 110/100);
        System.out.println(copy);
        System.out.println(copy.getStudentAverage("Jackson Brajer"));
        System.out.println(ics4u);
        copy.addStudents("Andre Corona", "Patrick Su", "Felix Yu", "Patrick Blais");
        copy.removeStudents("Andre Corona", "Felix Yu");
        System.out.println("Copy Course " + copy);
        List<String> droppedStudents = new ArrayList<>();
        droppedStudents.add("Patrick Su");
        copy.removeStudents(droppedStudents);
        copy.removeStudent("Jackson Brajer");
        System.out.println(copy);

        //Default Constructor Demo
        Course defaultCourse = new Course();
        System.out.println(defaultCourse);
    }

    public static Course copyCourse(Course c) {
        Course copy = new Course(c.courseCode, c.teacher, c.MAX_STUDENTS);
        copy.addStudents(c.getStudents());
        HashMap<String, HashMap<String, Double>> grades = c.getGrades();
        Iterator<String> studentNameIterator = grades.keySet().iterator();
        while (studentNameIterator.hasNext()) {
            String curStudent = studentNameIterator.next();
            Iterator<String> assignmentNameIterator = grades.get(curStudent).keySet().iterator();
            while (assignmentNameIterator.hasNext()) {
                String curAssignment = assignmentNameIterator.next();
                if (grades.get(curStudent).get(curAssignment) != null){
                    copy.addGradeToStudent(curStudent, curAssignment, grades.get(curStudent).get(curAssignment));
                }
            }
        }
        return copy;
    }

}
