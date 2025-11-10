// OOP, Reference Only, Given for Lab, 2025

import java.io.*;
import javax.swing.*;
public class ExceptionLab {
  public static final String FILENAME = "myName.txt";  // file name
  public static void main(String[] args) {
    String [] dataStr = {"My SID", "My Name", "My Prog"};	// data str, max 3
    String  inputStr = JOptionPane.showInputDialog(null, 
            "How many data lines you want, MAX 3: ");	// ask user input
    try{
    int lineSize = Integer.parseInt(inputStr); 			//convert String to int
    }catch(ArrayIndexOutOfBoundsException ArrIndexOutBounds){
       System.out.println("Array out of Bounds Err:"+ArrIndexOutBounds.getMessage());
       System.out.println("Exit system with code 69");
       System.exit(69);
    }catch(NumberFormatException NFE){
      System.out.println("Fuck youjust enter a normalk number dude:"+NFE.getMessage());
      System.out.println("Exit system with code 69");
      System.exit(69);
    }
    try{
    int lineSize = Integer.parseInt(inputStr); 
    PrintWriter outStream = new PrintWriter(FILENAME);	//open text write File
    for (int i=0; i<lineSize; i++)						// loop to write data
      outStream.println(dataStr[i]);
      outStream.close();					// close text write File
  }catch(FileNotFoundException FileN){
     System.out.println("File not Found:"+FileN.getMessage());
     System.exit(69);
  }
  }
}


