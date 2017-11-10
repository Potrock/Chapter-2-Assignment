package gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Course {

    private String courseCode;
    private String teacher;
    private List<String> students = new ArrayList<>();
    private HashMap<String, HashMap<String, Double>> grades = new HashMap<String, HashMap<String, Double>>();
    
    public Course(String courseCode, String teacher) {
	this.courseCode = courseCode;
	this.teacher = teacher;
    }

    public String getCourseCode() {
	return this.courseCode;
    }

    public String getTeacher() {
	return this.teacher;
    }

    public void addGradeToStudent(String assignmentName, double grade, String name){
	if(!this.grades.containsKey(name))
	    this.grades.put(name, new HashMap<String, Double>());
	
	this.grades.get(name).put(assignmentName, grade);
    }
    
    public List<String> getStudents() {
	List<String> newStudents = new ArrayList<>();
	newStudents.addAll(this.students);
	return newStudents;
    }

    public void addStudents(List<String> students) {
	this.students.addAll(students);
    }

    public void addStudents(String... names) {
	for (String name : names)
	    this.students.add(name);
    }

    public void addStudent(String name) {
	this.students.add(name);
    }

    public boolean removeStudent(String name) {
	if (this.students.contains(name)) {
	    int index = -1;
	    for (int i = 0; i < this.students.size(); i++)
		if (this.students.get(i).equals(name)) {
		    index = i;
		    break;
		}
	    if (index == -1)
		return false;

	    this.students.remove(index);
	    return true;
	}
	return false;
    }

    public boolean[] removeStudents(String... names) {
	boolean[] errors = new boolean[names.length];
	for (int i = 0; i < names.length; i++) {
	    errors[i] = removeStudent(names[i]);
	}
	return errors;
    }

    public boolean[] removeStudents(List<String> names) {
	boolean[] errors = new boolean[names.size()];
	for (int i = 0; i < names.size(); i++) {
	    errors[i] = removeStudent(names.get(i));
	}
	return errors;
    }
    
    public void removeStudent(int i) {
	if (this.students.size() > i)
	    this.students.remove(i);
    }

}
