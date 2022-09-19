import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Comparable<Course>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseName;
	private String courseID;
	private int max;		//Maximum number of students that can be enrolled
	private int currentStu;	//Number of students currently enrolled
	private ArrayList<Student> names; 	//Names of students enrolled into the class
	private String prof;	//Name of course instructor
	private int section;	//Course section number
	private String loc;		//Course location
	
	Course (String courseName, String courseID, int max, int currentStu, 
			ArrayList<Student> names, String prof, int section, String loc)	{
		this.courseName = courseName;
		this.courseID = courseID;
		this.max = max;
		this.currentStu = currentStu;
		this.names = names;
		this.prof = prof;
		this.section = section;
		this.loc = loc;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public String getCourseID() {
		return courseID;
	}
	public int getMax() {
		return max;
	}
	public int getCurrent() {
		return currentStu;
	}
	public ArrayList<Student> getNames() {
		ArrayList<Student> names = new ArrayList<Student>();
		return names;
	}
	public String getProf() {
		return prof;
	}
	public int getSection() {
		return section;
	}
	public String getLocation() {
		return loc;
	}
	public String changeProf(String newProf) {
		prof = newProf;
		return newProf;
	}
	public ArrayList<Student> addStu(ArrayList<Student> stuList) {
		names = stuList;
		return stuList;
	}
	public ArrayList<Student> setNames(Student stu) {
		ArrayList<Student> stuList = new ArrayList<Student>();
		stuList = getNames();
		stuList.add(stu);
		this.names = stuList;
		return names;
		
		/*//Populate ArrayList<Student> with a default student so that the ArrayList is not left as null. 
		//This system assumes that there is one default student user.
		ArrayList<Student> names = new ArrayList<Student>();
		Student defaultStu = new Student("stufirst","stulast");
		names.add(defaultStu);
		this.names = names;*/
	}
	public int compareTo(Course c) {
        int compareC = c.getCurrent();
        return this.currentStu-compareC;
    }
	public String addCurrent(Course c) {
		int currentVal = c.getCurrent();
		currentVal += 1;
		this.currentStu = currentVal;
		return "The course "+c.getCourseName()+" now has "+c.currentStu+" student(s) in the class.";
	}
	public String removeCurrent(Course c) {
		int currentVal = c.getCurrent();
		if (currentVal > 0) {
			currentVal -= 1;
		}
		this.currentStu = currentVal;
		return "The course "+c.getCourseName()+" now has "+c.currentStu+" student(s) in the class.";
	}
	
	
	

}

	


