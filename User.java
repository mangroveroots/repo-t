import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String first;
	public String last;
	private String userID;
	private String pword;
	
	User(String userID, String pword, String first, String last) {
		this.userID = userID;
		this.pword = pword;
		this.first = first;
		this.last = last;
	}
	
	void setLogin(String user, String pass) {
		userID = user;
		pword = pass;
	}
	
	String getLogin() {
		return " Username: " + userID +
			 "    Password: " + pword;
	}
	
	String getUser() {
		return userID;
	}
	String getPass() {
		return pword;
	}
	String getFirst() {
		return first;
	}

	void setFirst(String first) {
		this.first = first;
	}

	String getLast() {
		return last;
	}

	void setLast(String last) {
		this.last = last;
	}
	
	//**************************************************
	/* Method to accept string parameter and break it into first name and last name values
	 * that need to be accessible by student class, so that I can use them to create a student object
	 */
	void setFullName(String fullName) {
		int i = fullName.lastIndexOf(' ');
		String firstName = fullName.substring(0, i);
		String lastName  = fullName.substring(i + 1); 
		this.first = firstName;
		this.last = lastName;
	}
	
	String getFullName() {
		String full = getFirst() + " " + getLast();
		return full;
	}
	
	//Method that finds the index of a course with the course ID and ArrayList of courses as inputs.
	public static int findIndex(ArrayList<Course> list, String id, int sec) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCourseID().contains(id) && list.get(i).getSection() == sec) {
				return i;
				}
			}
		return -1;
		} 
	
	void viewAll(ArrayList<Course> list) {
		for(Course c : list)
        {
            System.out.println(c.getCourseName()+"   "+c.getCourseID()+"   "+c.getProf()+"   "+c.getLocation());
        }
	}
	//View all courses that the current student is registered in 
		public String viewStudent(ArrayList<Course> list, String first, String last) {
				String out = "";
				//String first = stu.getFirst();
				//String last = stu.getLast();
				for (Course c : list) {
					ArrayList<Student> stuList = c.getNames();
					for (Student s : stuList) {
						if (s.getFirst().equals(first) && s.getLast().equals(last)) {
							out += c.getCourseName();
							return c.getCourseName();
						} 
					}
				}
				if (out.equals("")) {
					return "This student either does not exist in this course system, or is not yet enrolled in any courses.";
				}
				return out;
			}
	
	/*
	 * Method to view all courses (Admin's should override with specificity)
	 * Method to show courses for specific student courses for given student
	 * To exit
	 */


}
