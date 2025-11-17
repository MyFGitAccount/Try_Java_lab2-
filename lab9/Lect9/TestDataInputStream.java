import java.io.*;
public class TestDataInputStream {
    public static void main (String[] args) throws IOException {
    	// set up the DataInputStream object 
        DataInputStream inDataStream = new DataInputStream(
           new FileInputStream("sample2.dat") );

    	//read values back from the stream and display them
    	System.out.println(inDataStream.readInt());
    	System.out.println(inDataStream.readFloat());
    	System.out.println(inDataStream.readDouble());
    	
    	//input done, so close the stream
    	inDataStream.close();
    }
}
