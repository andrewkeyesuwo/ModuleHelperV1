import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		//Load all the possible modules into an array
		BufferedInputStream mods = null;
		try{
			//Open a buffered input stream for the mods list
			mods  = new BufferedInputStream(new FileInputStream("C:\\Users\\Andrew\\1027B\\ModuleHelper\\src\\ModulesCompSci.txt"));
			
			//Read the module
			ReadModLine modslist = new ReadModLine(mods);
			String[] moduleString = modslist.readLine();
			//Create and add module
			Module moduleToAdd = new Module(moduleString[0]);
			moduleToAdd.setGradeRequirement(Float.parseFloat(moduleString[1]));
			moduleToAdd.setminGradeRequirement(Float.parseFloat(moduleString[2]));
			int i=3;
			while(i<10){
				//Check whether or not it is a credit, if it is then begin adding the course
				System.out.println(moduleString[i].charAt(0));
				if(moduleString[i].charAt(0)>='0'&&moduleString[i].charAt(0)<='9'){
					float credits = Float.parseFloat(moduleString[i]);
					System.out.println("Number of credits = "+moduleString[i]);
					i++;
					//If the course is conditional then start a conditionalcourse until another number is ran into
					if(!(moduleString[i+1].charAt(0)>='0'&&moduleString[i+1].charAt(0)<='9')){
						System.out.println("Adding a conditional course: "+ moduleString[i]);
						ConditionalCourse courseToAdd = new ConditionalCourse(new Course(moduleString[i],credits));
						i++;
						while(!(moduleString[i].charAt(0)>='0'&&moduleString[i].charAt(0)<='9')){
							courseToAdd.addCourse(new Course(moduleString[i], credits));
							System.out.println("Adding another peice of the conditional course: "+ moduleString[i]);
							i++;
						}
						moduleToAdd.addConditionalPreReq(courseToAdd);
					}
					//Else if the course is not conditional then enter a regular course
					else{
						System.out.println("Adding a regular course" + moduleString[i]);
						Course courseToAdd = new Course(moduleString[i], credits);
						moduleToAdd.setPrereq(courseToAdd);
						i++;
					}
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
