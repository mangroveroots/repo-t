import java.util.ArrayList;

interface AdminInt {
	
	/*
	 * Course Management Methods
	 *  Create new course, 
	 *  Delete course, 
	 *  Edit course (change instructor name),
	 *  Display info for a course 
	 *  Register a student
	 */
	
	void create(ArrayList<Course> list, String name, String ID, int max, int curr, 
			ArrayList<Student> names, String prof, int sec, String loc);
	void delete(ArrayList<Course> list, int i);
	void editProf(Course c, ArrayList<Course> list, int i, String newProf);
	void display(ArrayList<Course> list, int i);
	void register(String first, String last, ArrayList<Student> track);


	
	/*
	 * Course Reports
	 * View all courses and see info for each course
	 * View all full courses
	 * Write to file the list of courses that are full
	 * View names of students registered in a course
	 * View list of courses for a given student *THIS CAN BE SHARED WITH STUDENT, SO IS IN USER CLASS
	 */
	
	void viewAll(ArrayList<Course> list);
	void full(ArrayList<Course> list);
	void fullFile(ArrayList<Course> list);
	void viewNames(ArrayList<Course> list, String id, int sec);
	void sortCourse(ArrayList<Course> list);
	
	
	

}
