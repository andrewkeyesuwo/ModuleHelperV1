
public class Module {
	private Course[] ModPrereqs;
	private int arrayPosition;
	private int numCredits;
	private float gradeRequirement;
	private float mingradeRequirement;
	private String name;
	
	
	public Module(String nameOfMod){
		ModPrereqs = new Course[10];
		name=nameOfMod;
	}
	
	public Module(int credits){
		numCredits=credits;
		ModPrereqs = new Course[10];
	}

	public void setPrereq(Course preCourse){
		ModPrereqs[arrayPosition] = preCourse;
		arrayPosition++;
	}
	public Course[] getPrereqs(){
		return ModPrereqs;
	}
	
	public void setNumCredits(int num){
		numCredits=num;
	}
	
	public int getNumCredits(){
		return numCredits;
	}
	
	public void setGradeRequirement(float grade){
		gradeRequirement=grade;
	}
	public void setminGradeRequirement(float grade){
		mingradeRequirement=grade;
	}
	
	public float getGradeRequirement(){
		return gradeRequirement;
	}
	
	public float getminGradeRequirement(){
		return mingradeRequirement;
	}
	public String getName(){
		return name;
	}
}
