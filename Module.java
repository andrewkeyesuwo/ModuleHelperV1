import java.util.Iterator;
import java.util.LinkedList;

/* 
 * @author Andrew Keyes 
 * Keyesandrew@live.com
 */

public class Module {
	private LinkedList<Course> ModPrereqs;
	private int numCredits;
	private float gradeRequirement;
	private float mingradeRequirement;
	private String name;
	private LinkedList<ConditionalCourse> ModConditionalCoursesPreReq;
	
	
	public Module(String nameOfMod){
		name=nameOfMod;
		ModPrereqs = new LinkedList<Course>();
		ModConditionalCoursesPreReq = new LinkedList<ConditionalCourse>();
	}
	//Add a conditional course to the list of conditional courses
	public void addConditionalPreReq(ConditionalCourse addCourse){
		ModConditionalCoursesPreReq.add(addCourse);
	}
	
	//Return a list of conditional courses
	public Iterator<ConditionalCourse> getConditionalPreReq(){
		return ModConditionalCoursesPreReq.iterator();
	}

	//Add a course to the list of courses
	public void setPrereq(Course preCourse){
		ModPrereqs.add(preCourse);
	}
	
	//Return a list of courses
	public Iterator<Course> getPrereqs(){
		return ModPrereqs.iterator();
	}
	
	//Set the number of credits in this module
	public void setNumCredits(int num){
		numCredits=num;
	}
	
	//Return the number of credits in the module
	public int getNumCredits(){
		return numCredits;
	}
	
	//Set the average grade required to register in this module
	public void setGradeRequirement(float grade){
		gradeRequirement=grade;
	}
	
	//Set the minimum grade in any course
	public void setminGradeRequirement(float grade){
		mingradeRequirement=grade;
	}
	
	//Return the grade required to register in this module
	public float getGradeRequirement(){
		return gradeRequirement;
	}
	
	//Return the minimum grade in any course 
	public float getminGradeRequirement(){
		return mingradeRequirement;
	}
	
	//Return the name of the course
	public String getName(){
		return name;
	}
}
