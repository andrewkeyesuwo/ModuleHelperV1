import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		//Load all the possible modules into an array
		BufferedInputStream mods = null;
		try{
			//Open a buffered input stream for the mods list
			mods  = new BufferedInputStream(new FileInputStream("C:\\Users\\Andrew\\1027B\\WesternModuleSearcher\\src\\ModulesCompSci.txt"));
			
			//feed mods to the readLine class
			ReadModLine modslist = new ReadModLine(mods);
			String[] myList = modslist.readLine();
			for(int i=0;i<myList.length-1;i++){
				if(myList[i]!=null){
					myList[i]=myList[i].replace(" ", "");
					myList[i]=myList[i].replace("-", " ");
				}
			}
			
			Module[] myModArray = new Module[10];
			int i=0;
			int j=0;
			while(i<50){
				Module myMod = new Module(myList[i]);
				i++;
				myMod.setGradeRequirement(new Float(myList[i]));
				i++;
				myMod.setminGradeRequirement(new Float(myList[i]));
				i++;
				while(!(myList[i].substring(0, 1).equals("*"))){
					Course addMyCourse = new Course(myList[i+1],(new Float(myList[i])));
					myMod.setPrereq(addMyCourse);
					i++;
					i++;
				}
				myModArray[j]=myMod;
				j++;
			}
			Module myTestMod;
			for(int q=0;q<5;q++){
				myTestMod = myModArray[q];
			}
			
			//Have the student Enter there name
			System.out.println("Please enter your name:\n");
			
			
			//Have the student enter there class, class average, and number of credits one at a time 
			
			//Spit out all the possible modules that I can do
		}
		finally{
			mods.close();
		}
	}

}
