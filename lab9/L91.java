import javax.swing.*;
import java.util.*;
import java.io.*;
public class L91 {
public static void main(String[] args){
String fName = JOptionPane.showInputDialog
(null, "Type in the text file name:");
String fDataLine = JOptionPane.showInputDialog
(null, " Type in one text line for writing:");
String numLineStr = JOptionPane.showInputDialog
(null, "How many lines to be written/duplicated?");
int numLine = Integer.parseInt(numLineStr); // convert String to int
try{ ///////////// WRITE data into file
PrintWriter outStream = new PrintWriter(fName);
for (int i=0; i< numLine; i++)
outStream.println(fDataLine); // write data into file, in a line
outStream.close(); //close the stream
System.out.println("OK> Writing File [" + fName + "] FINISHED" );
} catch (FileNotFoundException fnfE){
System.out.println(">>> Exception, FileNotFoundException");
}
try{ ///////////// READ data from file
BufferedReader bufferReader = new BufferedReader(new FileReader(fName));
ArrayList<String> retAList = new ArrayList<String>(); // arraylist
// while loop to read file as string lines, until end (null string)
String strLine;
while ((strLine = bufferReader.readLine()) != null) { // read a line
retAList.add(strLine); // add read string line data to arraylist
}
bufferReader.close(); //close the stream
// BELOW: Convert ArrayList to String array
String[] strLines = retAList.toArray(new String[retAList.size()]);
System.out.println("OK> Reading File [" + fName
+ "] FINISHED. Data below:" );
for (int i=0; i<strLines.length; i++) // display all string lines
System.out.println(strLines[i]);
} catch (IOException ioE){
System.out.println(">>> Exception, IOException");
}
}
}
