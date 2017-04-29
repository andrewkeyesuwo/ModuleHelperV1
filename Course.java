import java.util.LinkedList;

public class Course {
		private String CourseName;
		private LinkedList<Course> preReqs;
		private float credits;
		private float grade;
		
		//Constructor that takes the name of the course and the number of credits
		public Course(String name, float Credit){
			CourseName=name;
			credits=Credit;
		}
		
		//Set a Course as a prerequisite 
		public void setPrereq(Course preCourse){
			preReqs.add(preCourse);
		}
		
		//Set the name of the Course
		public void setName(String name){
			CourseName=name;
		}
		
		
		//Set the number of credits that the Course is worth
		public void setCredits(float numCreds){
			credits=numCreds;
		}
		
		public float getCredits(){
			return credits;
		}
		
		//Return the name of the Course
		public String getName(){
			return CourseName;
		}
		
		//Return the size of the array
		public int numPreReqs(){
			return preReqs.size();
		}
		
		//Set the grade in the course
		public void setGrade(float gradenow){
			grade=gradenow;
		}
		
		//Return the grade in the course
		public float getGrade(){
			return grade;
		}		
}
