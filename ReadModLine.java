import java.io.*;

public class ReadModLine {
	private BufferedInputStream in;
    private String[] nextLine;
    private boolean endOfFile;
    private String[] arrayToBeReturned;
    
	public ReadModLine(BufferedInputStream file1) throws java.io.IOException{
		in = file1;
		endOfFile=false;
	}

	public String[] readLine() throws java.io.IOException{
		int ch;
		char nextChar;
		StringBuffer buf = new StringBuffer();
		arrayToBeReturned = new String[500];
		ch = in.read();
		int i=0;
		nextChar = Character.toUpperCase((char)ch);
		while(i<500){
			while(nextChar!='X'&&ch!=-1){
				buf.append(nextChar);
				ch = in.read();
				nextChar = Character.toUpperCase((char)ch);
			}
			arrayToBeReturned[i]=buf.toString();
			
			ch = in.read();
			nextChar = Character.toUpperCase((char)ch);
			i++;
			buf= new StringBuffer();
		}
		return arrayToBeReturned;
	}
}
