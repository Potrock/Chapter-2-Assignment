package gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Course {

	public String courseCode;
	public String teacher;
	public final int MAX_STUDENTS;
	private List<String> students = new ArrayList<>();
	//First hashmap key is student name, value is hashmap with key assignment name and value grade in the assignment.
	private HashMap<String, HashMap<String, Double>> grades = new HashMap<String, HashMap<String, Double>>();

	public Course() {
		this("Undefined Course", "Undefined Teacher");
	}

	public Course(String courseCode, String teacher) {
		this(courseCode, teacher, 30);
	}

	public Course(String courseCode, String teacher, int MAX_STUDENTS) {
		this.courseCode = courseCode;
		this.teacher = teacher;
		this.MAX_STUDENTS = MAX_STUDENTS;
	}

	public HashMap<String, HashMap<String, Double>> getGrades() {
		HashMap<String, HashMap<String, Double>> newGrades = new HashMap<String, HashMap<String, Double>>();
		Iterator<String> studentNameIterator = this.grades.keySet().iterator();
		while (studentNameIterator.hasNext()) {
			String curStudent = studentNameIterator.next();
			newGrades.put(curStudent, new HashMap<String, Double>());
			Iterator<String> assignmentNameIterator = this.grades.get(curStudent).keySet().iterator();
			while (assignmentNameIterator.hasNext()) {
				String curAssignment = assignmentNameIterator.next();
				newGrades.get(curStudent).put(curAssignment, this.grades.get(curStudent).get(curAssignment));
			}
		}
		return newGrades;
	}

	public void addGradeToStudent(String studentName, String assignmentName, double grade) {
		//If the student isnt in the grade book add them
		if (!this.grades.containsKey(studentName))
			this.grades.put(studentName, new HashMap<String, Double>());
		//Add their grade with the assignment name into the inner hashmap
		this.grades.get(studentName).put(assignmentName, grade);
	}

	//Divides the total grades by the amount of grades... average
	public double getStudentAverage(String name) {
		double avg = 0;
		if (!this.grades.containsKey(name))
			return -1;
		Iterator<Double> it = this.grades.get(name).values().iterator();
		while (it.hasNext()) {
			avg += it.next();
		}
		avg /= this.grades.get(name).size();
		return avg;
	}

	//getter
	public List<String> getStudents() {
		List<String> newStudents = new ArrayList<>();
		newStudents.addAll(this.students);
		return newStudents;
	}

	//setter
	public boolean addStudents(List<String> students) {
		if (students.size() + this.students.size() < this.MAX_STUDENTS) {
			this.students.addAll(students);
			return true;
		}
		return false;
	}

	//setter
	public boolean addStudents(String... names) {
		if (names.length + this.students.size() < this.MAX_STUDENTS) {
			for (String name : names)
				this.students.add(name);
			return true;
		}
		return false;
	}

	//setter
	public boolean addStudent(String name) {
		if (this.students.size() < this.MAX_STUDENTS) {
			this.students.add(name);
			return true;
		}
		return false;
	}

	//Removing one student by name
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

	//setter with varargs
	public boolean[] removeStudents(String... names) {
		boolean[] errors = new boolean[names.length];
		for (int i = 0; i < names.length; i++) {
			errors[i] = removeStudent(names[i]);
		}
		return errors;
	}

	//SETTER if you have something against varargs or have a list of kids who dropped the course
	public boolean[] removeStudents(List<String> names) {
		boolean[] errors = new boolean[names.size()];
		for (int i = 0; i < names.size(); i++) {
			errors[i] = removeStudent(names.get(i));
		}
		return errors;
	}

	//remove one person
	public void removeStudent(int i) {
		if (this.students.size() > i && i >= 0)
			this.students.remove(i);
	}

	public String toString() {
		return "Max Students: " + this.MAX_STUDENTS + " | " + "\"" + this.courseCode + "\"" + " taught by: " + "\"" + this.teacher
				+ "\"" + " | Students: " + this.students + " | " + "Grades: " + this.grades;
	}

}