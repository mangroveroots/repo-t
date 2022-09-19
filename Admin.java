import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class Admin extends User	
					implements AdminInt {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Admin(String userID, String pword, String first, String last) {
		super("admin", "admin001", first, last);
		}
	Admin(String first, String last) {
		this("admin", "admin001", first, last);
	}
	static void menuAdmin() {
		System.out.println("----------------------");
		System.out.println("COURSE MANAGEMENT");
		System.out.println("----------------------");
		System.out.println("0: Save all changes and exit the system");
		System.out.println("1: Create a new course");
		System.out.println("2: Delete a course");
		System.out.println("3: Edit a course (change the professor)");
		System.out.println("4: Display information for a particular course");               
		System.out.println("5: Register a student");
		System.out.println("----------------------");
		System.out.println("REPORTS");
		System.out.println("----------------------");
		System.out.println("6: View all courses");
		System.out.println("7: View all full courses");
		System.out.println("8: Write a list of full courses to a file");
		System.out.println("9: View the names of students registered in a specific course");
		System.out.println("10: View the list of courses that a given student is registered in");
		System.out.println("11: Sort and print courses based on enrollment");
		System.out.println("100: Enter '100' to immediately exit the system without saving any changes.");
		System.out.println("----------------------");
		System.out.println("What is your choice?");
		}
	
	public void create(ArrayList<Course> list, String name, String ID, int max, int curr, 
		ArrayList<Student> names, String prof, int sec, String loc) {
		Course c = new Course(name, ID, max, 0, names, prof, sec, loc);
		//c.setNames();
		list.add(c);
	}
	
	public void delete(ArrayList<Course> list, int i) {
		list.remove(i);
	}
	
	public void editProf(Course c, ArrayList<Course> list, int i, String newProf) {
		c = list.get(i);
		c.changeProf(newProf);
		}
	
	public void display(ArrayList<Course> list, int i) {
		System.out.println(list.get(i).getCourseName()+"   "+list.get(i).getCourseID()
        +"   Class capacity: "+list.get(i).getMax()+"   Number of students currently enrolled: "+list.get(i).getCurrent()
        		+ "   Students in this course: "+
        "   Professor: "+list.get(i).getProf()+"   Section number: "+list.get(i).getSection()
        		+"   Location: "+list.get(i).getLocation());
	}
	
	//Method to register student. Need arraylist of student objects
	public void register(String first, String last, ArrayList<Student> track) {
		Student stu = new Student(first, last);
		@SuppressWarnings("static-access")
		String user = stu.setUsername();
		@SuppressWarnings("static-access")
		String pword = stu.setPassword();
		stu.setLogin(user, pword);
		track.add(stu);
		System.out.println("You have just registered student " + first + " " + last + ". "+
				 " Here is the student's login info." + stu.getLogin());
	}
	
	
	//Method to exit from user
	
	
	
	//REPORTS--------------------------------------------------------
	//View all courses
	public void viewAll(ArrayList<Course> list) {
		for(Course c : list)
        {
            System.out.println(c.getCourseName()+"   "+c.getCourseID()
            +"   Number of students currently enrolled: "+c.getCurrent()+"   Class capacity: "+c.getMax()
            +"   Names and IDs of students in this class: ");
            if (c.getNames() != null) {
            	for (Student s : c.getNames()) {
            		System.out.println(s.getFirst()+" "+s.getLast()+"  "+s.getUser());
            	}
            } else System.out.println("There are no students enrolled in this class.");
            }
	}
	
	//View courses that are full
	public void full(ArrayList<Course> list) {
		System.out.println("The following courses are full. (If no course names are printed, then good news! All courses remain open.");
		for(Course c : list)
        {
            if (c.getCurrent() == c.getMax()) {
            	System.out.println(c.getCourseName());
            	}
            }
		}
	
	//Full courses write to file
	public void fullFile(ArrayList<Course> list) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("fullCourses.txt"), StandardCharsets.UTF_8))) {
			writer.write("The following courses are full.");
			String name = "";
			for(Course c : list)
	        {
	            if (c.getCurrent() == c.getMax()) {
	            	name = c.getCourseName();
	            	writer.write("\r\n"+name);
	            	}
	            }
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
	}
	//View names of students registered in a specific course
	public void viewNames(ArrayList<Course> list, String id, int sec) {
		int i = findIndex(list, id, sec);
		ArrayList<Student> stuList = list.get(i).getNames();
		if (!stuList.isEmpty()) {
			for (Student s :  stuList) {
				System.out.println(s.getFirst() + "   " +s.getLast());
				}
			} else System.out.println("There are no students enrolled in this class.");
		}
	
	//SOrt and print
	public void sortCourse(ArrayList<Course> list) {
		Collections.sort(list);
		for(Course c: list) {
			System.out.println(c.getCourseName());
			}
	}
	 


	
	
	
	//Exit from user
		
}
