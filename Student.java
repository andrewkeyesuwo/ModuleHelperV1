import java.util.LinkedList;
import java.util.List;

/* 
 * @author Andrew Keyes 
 * Keyesandrew@live.com
 */

public class Student {
	float averageGrade;
	private LinkedList<Course> CourseesTaken;
	
	public Student(){
		CourseesTaken = new LinkedList<Course>();
	}
	
	public void addCourse(Course newCourse){
		if(CourseesTaken.contains(newCourse)){
			System.out.println("This course has already been added");
			return;
		}
		else{
			CourseesTaken.add(newCourse);
		}
	}
	
	public LinkedList<Course> getCourses(){
		return CourseesTaken;
	}
	
	public boolean removeCourse(Course removeCourse){
		return CourseesTaken.remove(removeCourse);
	}
	
	public float getAverageGrade(){
		int i=0;
		float curGrade=0;
		float sum=0;
		while(CourseesTaken.get(i)!=null){
			curGrade = CourseesTaken.get(i).getGrade()*CourseesTaken.get(i).getCredits();
			sum=curGrade+sum;
			i++;
		}
		return sum/i;
		
	}
}
