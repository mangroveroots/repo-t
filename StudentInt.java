import java.util.ArrayList;

public interface StudentInt {
	
	/*
	 * Methods to 
	 * View all courses (this is in User)
	 * View all OPEN courses (not full)
	 * Register in a course (enter course name, section and student name
	 * Withdraw from a course (enter name and course name, and stu name will be removed)
	 * View all courses student is registered in -- is in User class; viewStudent() method
	 * 
	 */
	
	void viewOpen(ArrayList<Course> list);
	void register(ArrayList<Course> list, String id, int sec, Student stu);
	void withdraw(ArrayList<Student> stuList, int i);

	
	

}
