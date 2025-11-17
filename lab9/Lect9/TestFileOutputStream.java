import java.io.*;
import java.util.*;
public class TestFileOutputStream  {
    public static void main(String[] args){  // main, for testing

		//set up file and stream
		FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream ("sampleInput.dat");
			//data to save
			byte[] byteArray = {10, 20, 30, 40, 50, 60, 70, 80};
			//write data to the stream
			//outStream.write( byteArray );
                        int[] intArray={1,2,3,4,5,6,7,8,9};
                        for(int i=0;i<intArray.length;i++) outStream.write(intArray[i]);
			//output done, so close the stream
			outStream.close();
		} catch (IOException ioe){
			System.out.println("ERR: IOException");
		}

	}
}
