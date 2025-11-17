import java.io.*;
import java.util.*;
public class TestFileInputStream  {
    public static void main(String[] args){  // main, for testing

		//set up file and stream
		File inFile = new File( "sampleInput.dat" );
		FileInputStream inStream = null;
		try {
			inStream = new FileInputStream(inFile);
		// we may also create directly, with specified file name
		// inStream = new FileInputStream("sample1.txt");
			// set up an array to read data in
			int    fileSize  = (int) inFile.length();
			byte[] byteArray = new byte[fileSize];
			//read data in and display them
			inStream.read(byteArray);
			for (int i = 0; i < fileSize; i++){
				System.out.println(byteArray[i]);
			}
			//input done, so close the stream
			inStream.close();
		} catch (IOException ioe){
			System.out.println("ERR: IOException-->"+ioe);
		}

	}
}
