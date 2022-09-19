import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException {
		
		Scanner scan = new Scanner(System.in);
				
	    System.out.println("Is this the first time that this course registration system is being used? yes/no");
		String firstTime = scan.nextLine();
		if (firstTime.equals("yes")) {
			//Creating an ArrayList of courses from the CSV file
			ArrayList<Course> info = new ArrayList<Course>();
			ArrayList<Student> track = new ArrayList<Student>();
			BufferedReader br = null;
			readCSV(info, br);
			
			System.out.println("\nWelcome to our newest Course Registration System, Albert 2.0!");
			
			//The first person to use the system will be an admin user since students can only be registered into the system by the admin. 
			System.out.println("Hello admin user. Since this is your first time logging in, let us help you out with your login credentials. Enter your name to begin.");
			System.out.println("First name: ");
			String first = scan.nextLine();
			System.out.println("Last name: ");
			String last = scan.nextLine();
			Admin admin = new Admin(first, last);
			System.out.println(admin.getLogin());
			
			Admin.menuAdmin();
			String c = scan.nextLine();
			int choice = Integer.parseInt(c);
				while (choice != 100) {
					adminChoice(info, choice, track, admin);
					Admin.menuAdmin();
					c = scan.nextLine();
					choice = Integer.parseInt(c);
				}
			} 
			
		//else time to deserialize.
		else if (firstTime.equals("no")) {
			//Creating an ArrayList of courses from the CSV file
			ArrayList<Course> info = deser();
			ArrayList<Student> track = deserStu();
			//////////////////////////////////DESERIALIZE
		
			System.out.println("\nWelcome back to our Course Registration System, Albert 2.0!");
			System.out.println("Are you an administrator or a student? admin/student");
			String who = scan.nextLine();
			if (who.equals("admin")) {
				Admin admin = adminLogin();
				Admin.menuAdmin();

				String c = scan.nextLine();
				int choice = Integer.parseInt(c);
				while (choice != 100) {
					adminChoice(info, choice, track, admin);
					Admin.menuAdmin();
					c = scan.nextLine();
					choice = Integer.parseInt(c);
				}
			} else if (who.equals("student")) {
				Student stu = stuLogin(track);
				Student.menuStu();
				
				String c = scan.nextLine();
				int choice = Integer.parseInt(c);
				while (choice != 100) {
					stuChoice(info, choice, track, stu);
					Student.menuStu();
					c = scan.nextLine();
					choice = Integer.parseInt(c);
					}
				}
			}
		}
	

	public static Admin adminLogin() {
		System.out.println("Hello admin user. Is this your first time logging in to the system? yes/no");
		String str = scan.nextLine();
		if (str.equals("yes")) {
			System.out.println("If this is your first time logging in, let us help you out with your login credentials. Enter your name to begin.");
			System.out.println("First name: ");
			String first = scan.nextLine();
			System.out.println("Last name: ");
			String last = scan.nextLine();
			Admin admin = new Admin(first, last);
			System.out.println(admin.getLogin());
			return admin;
		} else if (str.equals("no")) {
			Admin admin = new Admin("adminFirst", "adminLast");
			System.out.println("Enter your login information.");
			System.out.println("Username: ");
			String user = scan.nextLine();
			System.out.println("Password: ");
			String pass = scan.nextLine();
			if (user.equals(admin.getUser()) && pass.equals(admin.getPass())) {
				System.out.println("You have successfully logged in.");
			} else {
				System.out.println("Sorry, invalid choice. Start the system again.");
				System.exit(0);
			}
			return admin;
		} else {
			System.out.println("Sorry, invalid choice. Start the system again.");
			System.exit(0);
		}
		return null;
	}
	
	public static Student stuLogin(ArrayList<Student> track) {
		System.out.println("Hello student user. Enter your login information.");
			System.out.println("Username: ");
			String user = scan.nextLine();
			System.out.println("Password: ");
			String pass = scan.nextLine();
			for (Student s : track) {
				if (s.getUser().equals(user) && s.getPass().equals(pass)) {
					System.out.println("You have successfully logged in.");
					System.out.println("Checking student info: "+s.getLogin());
					return s;
				} 
				else {
					System.out.println("Sorry, invalid choice. Start the system again.");
					System.exit(0);
					}
		}
			return null;
	}
	
	
	static void adminChoice(ArrayList<Course> list, int choice, ArrayList<Student> track, Admin admin) {
		if (choice == 0) {
			
			//serialize
			serCourse(list);
			serStu(track);
			System.out.println("Goodbye.");
			System.exit(0);
			
		} else if (choice == 1) {
			//Creating a course
			System.out.println("To create a new course please input information about this course.");
			System.out.println("Enter the name of the course.");
			String name = scan.nextLine();
			System.out.println("What is the ID of the course?");
			String ID = scan.nextLine();
			System.out.println("Who is the instructor?");
			String instructor = scan.nextLine();
			System.out.println("Where is this class located?");
			String location = scan.nextLine();
			System.out.println("What is this class's student capacity?");
			String c = scan.nextLine();
			int capacity = Integer.parseInt(c);
			System.out.println("Enter the section number.");
			String s = scan.nextLine();
			int sec = Integer.parseInt(s);
			System.out.println("sec is : "+sec+ "and capacity is "+capacity);
			
			ArrayList<Student> stuList = null;
			admin.create(list, name, ID, capacity, 0, stuList, instructor, sec, location);
			System.out.println("The course has been added. \nBack to the menu:");
		}
		else if (choice == 2) {
			//Deleting a course
			System.out.println("To delete a course, please enter its ID.");
			String id = scan.nextLine();
			System.out.println("Enter this course's section number.");
			String s = scan.nextLine();
			int sec = Integer.parseInt(s);
			int value = Admin.findIndex(list, id, sec);
			System.out.println("The index of this class is: " + value);
			System.out.println("The course at this index is: " + list.get(value).getCourseName());
			if (value >= 0) {
			admin.delete(list, value);
			}
			String cName = list.get(value).getCourseName();
			System.out.println("This course has been deleted. The course at this index is now: " + cName);
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 3) {
			//Editing a course by changing the professor
			
			System.out.println("Which course would you like to change? Please enter the course ID.");
			String courseChange = scan.nextLine(); 
			System.out.println("Please enter the section number.");
			String s = scan.nextLine();
			int sec = Integer.parseInt(s);
			int val = Admin.findIndex(list, courseChange, sec);
			System.out.println("This course is at index: " + val);
			System.out.println("Enter the name of the new professor.");
			String profChange = scan.nextLine();
			System.out.println("The professor formerly teaching this class was "+ list.get(val).getProf());
			admin.editProf(list.get(val), list, val, profChange);
			System.out.println("The professor for "+courseChange + " "
						+ "is now "+ list.get(val).getProf());
			System.out.println("\nBack to the menu:");
			
		}
		else if (choice == 4) {
			//Display info for a course using the course ID
			System.out.println("Which course's information would you like to see? Enter the course ID.");
			String id = scan.nextLine();
			System.out.println("Enter the section number.");
			String s = scan.nextLine();
			int sec = Integer.parseInt(s);
			admin.display(list, Admin.findIndex(list, id, sec));
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 5) {
			//Register a student
			System.out.println("To register a student, please enter their first and last name.");
			System.out.print("First name: ");
			String firstName = scan.nextLine();
			System.out.print("Last name: ");
			String lastName = scan.nextLine();
			admin.register(firstName, lastName, track);
			
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 6) {
			//View all courses
			System.out.println("Here are all of the courses.");
			admin.viewAll(list);
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 7) {
			//Which courses are full
			admin.full(list);
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 8) {
			//Store full courses in file
			System.out.println("The information of courses that are full has been stored in a file named fullCourses.text.");
			admin.fullFile(list);			
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 9) {
			//View names of students in specific course
			System.out.println("Which course would you like to view enrolled students for?");
			System.out.println("Course ID: ");
			String id = scan.nextLine();
			System.out.println("Course section: ");
			String s = scan.nextLine();
			int sec = Integer.parseInt(s);
			admin.viewNames(list, id, sec);
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 10) {
			//View list of courses that a given student is registered in
			System.out.println("What is the student's first name?");
			String first = scan.nextLine();
			System.out.println("What is the student's last name?");
			String last = scan.nextLine();
			//Check to see if student object with such name exists in the system
			//Student stu = track.get(Student.findIndex(track, first, last));
			Student stu = new Student(first, last);
			//if (Student.findIndex(track, first, last) == -1) {
			//	System.out.println("This student does not exist in the system.");
			//} else {
			System.out.println(admin.viewStudent(list, first, last));
			//}
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 11) {
			//Sort and print
			System.out.println("Here are all the courses sorted by current enrollment.");
			admin.sortCourse(list);
			System.out.println("\nBack to the menu:");
		}
	}
	
	 
	
	static void stuChoice(ArrayList<Course> list, int choice, ArrayList<Student> track, Student stu) {
		if (choice == 0) {
			serCourse(list);
			serStu(track);
			System.out.println("Goodbye.");
			System.exit(0);
			
		} else if (choice == 1) {
			//View all courses
			stu.viewAll(list);
		}
		else if (choice == 2) {
			//View all courses that are not full/ open
			stu.viewOpen(list);
		}
		else if (choice == 3) {
			//Register in a course
			System.out.println("To register yourself into a course please enter the following information.");
			System.out.print("Your first name: ");
			String first = scan.nextLine();
			System.out.print("Your last name: ");
			String last = scan.nextLine();
			System.out.println("The ID of the course: ");
			String id = scan.nextLine();
			System.out.print("Section number: ");
			String s = scan.nextLine();
			int sec = Integer.parseInt(s);
			
			stu = track.get(stu.findIndex(track, first, last));
			System.out.println("Here is track: " + track+" Here is track.get: "+ track.get(stu.findIndex(track, first, last))+" and here is stu: "+stu);
			
			stu.register(list, id, sec, stu);
			//stu.register(list, id, sec, first, last);
			System.out.println("\nBack to the menu:");
			
		}
		else if (choice == 4) {
			//Withdraw from a course
			System.out.println("To withdraw from a course please enter the following information.");
			System.out.print("Your first name: ");
			String firstw = scan.nextLine();
			System.out.print("Your last name: ");
			String lastw = scan.nextLine();
			System.out.println("The ID of the course: ");
			String idw = scan.nextLine();
			System.out.print("Section number: ");
			String s = scan.nextLine();
			int secw = Integer.parseInt(s);
			int courseIndex = Admin.findIndex(list, idw, secw);
			int index = stu.findIndex(list.get(courseIndex).getNames(), firstw, lastw);
			
			stu.withdraw(list.get(courseIndex).getNames(), index);
			System.out.println(list.get(courseIndex).removeCurrent(list.get(courseIndex)));
			System.out.println("\nBack to the menu:");
		}
		else if (choice == 5) {
			//View all courses the current student is registered in
			String first = stu.getFirst();
			String last = stu.getLast();
			System.out.println(stu.viewStudent(list, first, last));
			
		}
	}
		
	//Reads from CSV file "MyUniversityCourses" and stores the information as course objects in an ArrayList.
	public static void readCSV(ArrayList<Course> list, BufferedReader br) {

		try {
			br = new BufferedReader(new FileReader("MyUniversityCourses.csv"));
	    String line;
	    while ((line = br.readLine()) != null) {
	        String[] values = line.split(",");
	        if(values.length > 0)
            {
	        	ArrayList<Student> stu = new ArrayList<Student>();
                //Save the course details in Course object
                Course courses = new Course(values[0], values[1], Integer.parseInt(values[2]),
                        Integer.parseInt(values[3]), stu, values[5], Integer.parseInt(values[6]), values[7]);
                list.add(courses);
            }
	    }
    } catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	try
    {
        br.close();
    }
    catch(IOException ie)
    {
        System.out.println("Error occured while closing the BufferedReader");
        ie.printStackTrace();
        }
	}
	
	//Method to serialize ArrayList<Course> (hopefully)
	public static void serCourse(ArrayList<Course> list) {
		try{
	         FileOutputStream fos= new FileOutputStream("courseList.ser");
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(list);
	         
	         oos.close();
	         fos.close();
	         
	         System.out.println("Serialization complete");

	         } catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	//Method to deserialize
	@SuppressWarnings("unchecked")
	public static ArrayList<Course> deser() {
		try
        {
			ArrayList<Course> deser = new ArrayList<Course>();
            FileInputStream fis = new FileInputStream("courseList.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            deser = (ArrayList<Course>)ois.readObject();
            ois.close();
            fis.close();
	        System.out.println("File successfully deserialized");    

            return deser;
            } 
		catch(IOException ioe) {
			ioe.printStackTrace();
			return null;
			}
		catch(ClassNotFoundException cnfe) {
			System.out.println("Class not found");
			cnfe.printStackTrace();
            return null;
            }
		}
	
	//Method to serialize ArrayList<Student> (hopefully)
		public static void serStu(ArrayList<Student> list) {
			try{
		         FileOutputStream fos= new FileOutputStream("stuList.ser");
		         ObjectOutputStream oos= new ObjectOutputStream(fos);
		         oos.writeObject(list);
		         
		         oos.close();
		         fos.close();
		         
		         System.out.println("Serialization complete");

		         } catch(IOException ioe){
		            ioe.printStackTrace();
		        }
		}
		//Method to deserialize
		@SuppressWarnings("unchecked")
		public static ArrayList<Student> deserStu() {
			try
	        {
				ArrayList<Student> deser = new ArrayList<Student>();
	            FileInputStream fis = new FileInputStream("stuList.ser");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            deser = (ArrayList<Student>)ois.readObject();
	            ois.close();
	            fis.close();
		        System.out.println("File successfully deserialized");    

	            return deser;
	            } 
			catch(IOException ioe) {
				ioe.printStackTrace();
				return null;
				}
			catch(ClassNotFoundException cnfe) {
				System.out.println("Class not found");
				cnfe.printStackTrace();
	            return null;
	            }
			}
}
	
