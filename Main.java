import java.io.*;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import java.util.Iterator;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/* 
 * @author Andrew Keyes 
 * Keyesandrew@live.com
 */


public class Main {
	public static Student person;
	public static String[][] goodMods = new String[25][1];
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Load all the possible modules into an array
		
		
		Module[] moduleHolder = addModules();
		//Ask student for their modules
		GUI visuals = new GUI();
		person = visuals.getStudent();
		
		while(!visuals.doneAdding()){
				Thread.sleep(100);
		}
		
		
		System.out.println("Student entered all courses");
		
		//Compare courses to the available modules
		//If the student has all the modules available then add it to the linked list and return the name
		Comparator comparethis = new Comparator();
		LinkedList<Course> studentCoursees = person.getCourses();
		int i = 0;
		int b = 0;
		while(i<5){
			Module module = moduleHolder[i];
			boolean fail = false;
			boolean meetsReqs = true;
			boolean containCourse=false;
			float studentModGrade;
				
			LinkedList<Course> usedStudCoursees = new LinkedList<Course>();
				
			//Compares regular prerequisite courses against the courses that the student has entered.
			Iterator<Course> preCourses = module.getPrereqs();
			while(meetsReqs==true&&preCourses.hasNext()){
				Course preCourse = preCourses.next();
				int k = 0;
				//Loops through student courses to check if they meet that prerequisite
				while(k<studentCoursees.size()){
					if(comparethis.compare(preCourse.getName(),studentCoursees.get(k).getName())){
						containCourse=true;
						usedStudCoursees.add(studentCoursees.get(k));
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
					if(comparethis.compare(preConCourse, studentCoursees.get(k))){
						containCourse=true;
						credits+=studentCoursees.get(k).getCredits();
						usedStudCoursees.add(studentCoursees.get(k));
					}
					k++;
				}
				if(!containCourse||credits<preConCourse.getCredits()){
					meetsReqs = false;
				}
			}
			
			boolean gradeReq=true;
			float sum =0;
			for(int w=0;w<usedStudCoursees.size();w++){
				sum+=usedStudCoursees.get(w).getGrade();
				if(usedStudCoursees.get(w).getGrade()<module.getminGradeRequirement()){
					gradeReq=false;
				}
			}
			if(sum/usedStudCoursees.size()<module.getGradeRequirement()){
				gradeReq=false;
			}
			
			if(meetsReqs&&gradeReq){
				System.out.println("Student average for the module equals "+ sum/usedStudCoursees.size());
				System.out.println("Student meets the qualifications for "+ module.getName());
				goodMods[b][0] = module.getName();
				b++;
			}
			i++;
		}
		visuals.buildMajorList(goodMods);
	}
	
	public static Module[] addModules(){
		//Module holder
		BufferedInputStream mods = null;
		Module[] moduleHolder = new Module[11];;
		
		try{
			//Open a buffered input stream for the mods list
			mods  = new BufferedInputStream(new FileInputStream("C:\\Users\\Andrew\\1027B\\ModuleHelper\\src\\ModulesCompSci.txt"));
			
			//Create and add all module
			for(int j=0;j<5;j++){
				
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
		}catch(Exception e){
			e.printStackTrace();
		}
		return moduleHolder;
	}
}
