import java.io.*;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;

/* 
 * @author Andrew Keyes 
 * Keyesandrew@live.com
 */


public class Main {

	public static void main(String[] args) throws IOException {
		//Load all the possible modules into an array
		BufferedInputStream mods = null;
		try{
			//Open a buffered input stream for the mods list
			mods  = new BufferedInputStream(new FileInputStream("C:\\Users\\Andrew\\1027B\\ModuleHelper\\src\\ModulesCompSci.txt"));
			
			//Module holder
			Module[] moduleHolder = new Module[11];
			
			//Create and add all module
			for(int j=0;j<11;j++){
				//Read the module line one at a time
				ReadModLine modslist = new ReadModLine(mods);
				String[] moduleString = modslist.readLine();
				Module moduleToAdd = new Module(moduleString[0]);
				moduleToAdd.setGradeRequirement(Float.parseFloat(moduleString[1]));
				moduleToAdd.setminGradeRequirement(Float.parseFloat(moduleString[2]));
				int i=3;
				while(moduleString[i]!=null){
					//Check whether or not it is a credit, if it is then begin adding the course
					if(moduleString[i].charAt(0)>='0'&&moduleString[i].charAt(0)<='9'){
						float credits = Float.parseFloat(moduleString[i]);
						i++;
						//If the course is conditional then start a conditionalcourse until another number is ran into
						if(!(moduleString[i+1].charAt(0)>='0'&&moduleString[i+1].charAt(0)<='9')){
							ConditionalCourse courseToAdd = new ConditionalCourse(new Course(moduleString[i],credits));
							i++;
							while(moduleString[i]!=null&&(!(moduleString[i].charAt(0)>='0'&&moduleString[i].charAt(0)<='9'))){
								courseToAdd.addCourse(new Course(moduleString[i], credits));
								i++;
							}
							moduleToAdd.addConditionalPreReq(courseToAdd);
						}
						//Else the course is not conditional then enter a regular course
						else{
							Course courseToAdd = new Course(moduleString[i], credits);
							moduleToAdd.setPrereq(courseToAdd);
							i++;
						}
					}
				
				}
				moduleHolder[j] = moduleToAdd;
			}
			System.out.println("All modules have been added");
			
			//Ask student for their modules
			Student person = new Student();
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter your courses or enter * to end");
			
			System.out.print("Enter student course: ");
			String token = reader.next();
			while(token.charAt(0)!='*'){
				person.addCourse(new Course(token, (float) 0.5));
				System.out.print("Enter student course: ");
				token = reader.next();
			}
			System.out.println("Student entered all courses");
			
			//Compare courses to the available modules
			//If the student has all the modules available then add it to the linked list and return the name
			LinkedList<Course> studentCoursees = person.getCourses();
			int i = 0;
			while(i<11){
				Module module = moduleHolder[i];
			
				boolean fail = false;
				boolean meetsReqs = true;
				boolean containCourse=false;
				
				//Compares regular prerequisite courses against the courses that the student has entered.
				Iterator<Course> preCourses = module.getPrereqs();
				while(meetsReqs==true&&preCourses.hasNext()){
					Course preCourse = preCourses.next();
					int k = 0;
					//Loops through student courses to check if they meet that prerequisite
					while(k<studentCoursees.size()){
						if(compare(preCourse.getName(),studentCoursees.get(k).getName())){
							containCourse=true;
						}
						k++;
					}
					if(!containCourse){
						meetsReqs = false;
					}
				}
				
				Iterator<ConditionalCourse> preConditionalCourses = module.getConditionalPreReq();
				while(meetsReqs==true&&preConditionalCourses.hasNext()){
					ConditionalCourse preConCourse = preConditionalCourses.next();
					int k = 0;
					float credits=0;
					//loop through student courses to check if they meet on of the requirements from the conditional course
					while(k<studentCoursees.size()){
						if(compareConditional(preConCourse, studentCoursees.get(k))){
							containCourse=true;
							credits+=studentCoursees.get(k).getCredits();
						}
						k++;
					}
					if(!containCourse||credits<preConCourse.getCredits()){
						meetsReqs = false;
					}
				}
				
				if(meetsReqs==true){
					System.out.println("Student meets the qualifications for "+ module.getName());
				}
				i++;
			}
			
			
			
			
			
			
			
			
		}
		catch(Exception e){
			System.out.println("There is a problem");
			System.out.println(e.getMessage());
		}
		finally/*catch(Exception e)*/{
			System.out.println("Finish");
		}
	}
	
	
	
	
	
	public static boolean compare(String a, String b){
		a = a.trim();
		b = b.trim();
		if(a.length()!=b.length()){
			return false;
		}
		for(int i=0;i<a.length();i++){
			if(a.charAt(i)!=b.charAt(i)){
				return false;
			}
		}
		return true;
	}
	
	public static boolean compareConditional(ConditionalCourse conCourse, Course studentCourse){
		Iterator<Course> courses = conCourse.getCourses().listIterator();
		while(courses.hasNext()){
			Course course = courses.next();
			if(compare(course.getName(), studentCourse.getName())){
				return true;
			}
		}
		return false;
		
	}

}
