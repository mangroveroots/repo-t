import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Student extends User implements StudentInt, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Student(String userID, String pword, String first, String last) {
		super(userID, pword, first, last);
	}
	Student(String first, String last) {
		this(setUsername(), setPassword(), first, last);
	}
	static String setUsername() {
		Random rand = new Random();
		int n = rand.nextInt(1000);
		n += 100;
		String num = Integer.toString(n);
		String user = "stu" + num;
		return user;
	}
	static String setPassword() {
		Random rand = new Random();
		int n = rand.nextInt(1000);
		n += 100;
		String num = Integer.toString(n);
		String pword = "p" + num;
		return pword;
	}
	
	
	static void menuStu() {
		System.out.println("----------------------");
		System.out.println("COURSE MANAGEMENT");
		System.out.println("----------------------");
		System.out.println("0: Save all changes and exit the system");
		System.out.println("1: View all courses");
		System.out.println("2: View all open courses");
		System.out.println("3: Register in a course");
		System.out.println("4: Withdraw from a course");
		System.out.println("5: View all courses that you are registered in");
		System.out.println("100: Enter '100' to immediately exit the system without saving any changes.");
		System.out.println("----------------------");
		System.out.println("What is your choice?");
		}
	
	
	//Method that finds the index of a student with arraylist of student objects pertaining to 
	//a course and the first and last name of the student as inputs.
		public int findIndex(ArrayList<Student> stuList, String first, String last) {
			for (int i = 0; i < stuList.size(); i++) {
				if (stuList.get(i).getFirst().contains(first) && stuList.get(i).getLast().contains(last)) {
					return i;
					}
				}
			return -1;
			} 
		//USING STUDENT OBJ AS INPUT
		public static int findIndex(ArrayList<Student> stuList, Student stu) {
			for (int i = 0; i < stuList.size(); i++) {
				if (stuList.get(i).getFirst().contains(stu.getFirst()) && stuList.get(i).getLast().contains(stu.getLast())) {
					return i;
					}
				}
			return -1;
			} 
	
	//View all courses that are not full
	public void viewOpen(ArrayList<Course> list) {
		System.out.println("The following courses are open.");
		for(Course c : list)
        {
            if (c.getCurrent() < c.getMax()) {
            	System.out.println(c.getCourseName());
            	}
            }
	}
	
	//REGISTER USING STUDENT OBJECT
	public void register(ArrayList<Course> list, String id, 
			int sec, Student stu) {
		
		int i = findIndex(list, id, sec);
		System.out.println("index is : " + i);

		ArrayList<Student> stuList = list.get(i).getNames();
		//list.get(i).setNames(stu);
		//System.out.println("setnames: "+list.get(i).setNames(stu));
		//stuList = list.get(i).setNames(stu);
		System.out.println("stuListbefore: "+stuList);
		stuList.add(stu);
		//stuList = list.get(i).setNames(stu);
		System.out.println("stuListafter: "+stuList);
		
		System.out.println("You are being enrolled into: " + list.get(i).getCourseName());
 		System.out.println(list.get(i).addCurrent(list.get(i)));
	}
	
	
	//Withdraw from a course 
	public void withdraw(ArrayList<Student> stuList, int i) {

		System.out.println("stuListbefore: "+stuList);
		stuList.remove(i);
		System.out.println("stuListafter: "+stuList);
		
	}

}
