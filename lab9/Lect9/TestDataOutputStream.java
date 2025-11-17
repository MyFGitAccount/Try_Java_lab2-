import java.io.*;
public class TestDataOutputStream {
  public static void main (String[] args) throws IOException {
      //set up DataOutputStream object to write data to sample2.txt 
      DataOutputStream outDataStream  = new DataOutputStream(
         new FileOutputStream("sample2.dat") );

      //write values of primitive data types to the stream
      outDataStream.writeInt(987654321);
      outDataStream.writeFloat(22222222F);
      outDataStream.writeDouble(3333333D);
	
      //output done, so close the stream
      outDataStream.close();
  }
}
