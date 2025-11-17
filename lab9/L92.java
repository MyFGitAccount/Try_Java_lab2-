/*import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class L92{
   public static void main(String[] arr){
      String file="pRecords.txt";
      try(BufferedReader reader=new BufferedReader(new FileReader(file))){
          String line;
          ArrayList<String> readData = new ArrayList<>();
          while ((line=reader.readLine())!=null){
             readData.add(line);
          }
        System.out.println(readData.toString());
      }catch(Exception e){System.err.println("Error:"+e);}
    //System.out.println(readData.toString());
   }
}*/

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
// Record Format: <pID>,<pName>,<pType>,<pWeight>
public class L92 {
   public static ArrayList<Player> readPFiles(String fName){
   ArrayList<Player> retAList = new ArrayList<Player>(); // ArrayList
   try{ ///////////// READ data from file
      BufferedReader bufferReader = new BufferedReader(new FileReader(fName));
      // while loop to read file as string lines, until end (null string)
      String strLine;
      while ((strLine = bufferReader.readLine()) != null) { // read a line data
         String[] ps = strLine.split(",");
         Player p = new Player(Integer.parseInt(ps[0]), ps[1]);
         p.setType(ps[2]); p.setWeight(Double.parseDouble(ps[3]));
         retAList.add(p); // add a player to arraylist
      }
      for(Player p:retAList){
          System.out.println()
      }
      bufferReader.close(); //close the stream
      } catch (IOException ioE){
        System.out.println(">>> Exception, IOException");
        return null;
      }
      return retAList;
    }
    public static void writePFiles(String fName, ArrayList<Player> pAList){
    try{ ///////////// WRITE data into file
       PrintWriter outStream = new PrintWriter(fName);
       int pSize = pAList.size();
       for (int i=0; i<pSize; i++)
         outStream.println(pAList.get(i).getPInfo());//write data into file, in a line
         outStream.close(); //close the stream
         System.out.println("OK> Writing File [" + fName + "] FINISHED" );
       } catch (FileNotFoundException fnfE){
         System.out.println(">>> Exception, FileNotFoundException");
       }
 }
      public static void main(String[] args){
         String inFile = "pRecords.txt";
         String outFile = "pRecordsUpdated.txt";
         System.out.println("--- Lab Program: Player Text File Write/Read ---");
         ArrayList<Player> pAList = readPFiles(inFile); // call to read file
         Player nP = Player.userCreateP();
         pAList.add( nP );
         writePFiles(outFile, pAList); // call to write file
}
}
