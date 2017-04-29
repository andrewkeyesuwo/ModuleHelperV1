import java.io.*;

public class ReadModLine {
	private BufferedInputStream fileIn;
	private int endOfLine = -1;
    
	public ReadModLine(BufferedInputStream fileIn) throws java.io.IOException{
		this.fileIn = fileIn;
	}
	
	public String[] readLine() throws java.io.IOException{
		String[] arrayOfLine = new String[50];
		char[] arrayOfWord = new char[256];
		int i = 0;
		int j=0;
		char ch = (char) fileIn.read(); 
		if(endOfLine==(int)ch){
			return null;
		}
		while(ch==' '){
			ch=(char)fileIn.read();
		}
		while(ch!=System.lineSeparator().charAt(0)){
			if(ch==' '||ch=='+'){
				arrayOfLine[i] = new String(arrayOfWord);
				System.out.println("Added word: " + arrayOfLine[i]);
				i++;
				j=0;
				arrayOfWord = new char[256];
			}
			else{
				arrayOfWord[j] = ch;
				j++;
			}
			ch = (char)fileIn.read();
		}
		arrayOfLine[i] = new String(arrayOfWord);
		System.out.println("Added word: " + arrayOfLine[i]);
		ch = (char)fileIn.read();
		return arrayOfLine;
	}
	
	
}
