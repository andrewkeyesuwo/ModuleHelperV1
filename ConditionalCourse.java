import java.util.LinkedList;

/* 
 * @author Andrew Keyes 
 * Keyesandrew@live.com
 */

public class ConditionalCourse {
	private LinkedList<Course> conditionalCourses;
	private float credits;
	
	public ConditionalCourse(Course conditionalCourse){
		this.conditionalCourses = new LinkedList<Course>(); 
		this.conditionalCourses.add(conditionalCourse);
		credits = conditionalCourse.getCredits();
	}
	
	public void setCredits(float credits){
		this.credits = credits;
	}
	
	public float getCredits(){
		return credits;
	}
	
	public void addCourse(Course courseAdd){
		conditionalCourses.add(courseAdd);
	}
	
	public LinkedList<Course> getCourses(){
		return conditionalCourses;
	}
	
	
}
