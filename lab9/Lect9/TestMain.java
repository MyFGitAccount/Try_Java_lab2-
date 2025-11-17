// import statements
import javax.swing.*;
import java.io.*;

public class TestMain {
  public static void main(String[] args) {

//    String fileName = "sample.txt";
//	String fileName = "sRecord.txt";
//	String fileName = "sample1.dat";
	String fileName = "foobarbaz.txt";
	
    File inFile = new File(fileName);
    // If inFile is associated to a real file correctly, 
    // show a message dialog "sample.txt exists"
    if ( inFile.exists() ) {
      JOptionPane.showMessageDialog(null, fileName + " exists.");
    } else {
      JOptionPane.showMessageDialog(null, fileName + " doesn't exist!");
    }
	
    // List the name of all files in the directory "D:/"
    File directory = new File("/home/young/lang/java/lab9/Lect9");
    String fileName2[] = directory.list();
    System.out.println(java.util.Arrays.toString(fileName2));
    for (int i = 0; i < fileName2.length; i++) {
      System.out.println(fileName2[i]);
    }
  }
}
