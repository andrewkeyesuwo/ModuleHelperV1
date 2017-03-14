
public class Course {
		private String CourseName;
		private Course[] prereqs;
		private float credits;
		private int arrayPosition;
		private int Size;
		private float grade;
		
		public Course(String name, float Credit){
			CourseName=name;
			Course[] prereqs = new Course[Size];
			arrayPosition=0;
			credits=Credit;
		}
		
		//Set a Course as a prerequisite 
		public void setPrereq(Course preCourse){
			prereqs[arrayPosition] = preCourse;
			arrayPosition++;
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
		
		public int getSize(){
			return arrayPosition;
		}
		
		public void setGrade(float gradenow){
			grade=gradenow;
		};
		
		public float getGrade(){
			return grade;
		}		
}
