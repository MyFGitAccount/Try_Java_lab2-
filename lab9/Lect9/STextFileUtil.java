// STextFileUtil.java
import java.io.*;
import java.util.*;
public class STextFileUtil  {
    public static void main(String[] args){  // main, for testing
        String fName = "sRecord.txt";
        String [] fData = {"20202020,LAU Andy,AENG,68.5",
                            "20202021,CHOW Betty,HDIT,61",
                            "20202022,AU Candy,AENG,60"};

        STextFileUtil.writeTextFile(fName, fData);
        STextFileUtil.appendTextFile(fName, fData);
        String [] fDataStrs = STextFileUtil.readTextFile(fName);
        System.out.println(">>> File Read  [" + fName 
                     + "] FINISHED, and data below:" );
        for (int i=0; i<fDataStrs.length; i++)
            System.out.println(fDataStrs[i]);
    }

// continue ...
// More coming
    public static boolean writeTextFile(String fileName, 
                              String [] fileContent){
        try{
            PrintWriter outStream = new PrintWriter(fileName);
            for (int i=0; i<fileContent.length; i++)
                outStream.println(fileContent[i]); //write into file, in a line
            outStream.close(); //close the stream
            System.out.println(">>> Writing File [" + fileName + "] FINISHED" );
        } catch (FileNotFoundException fnfE){
            System.out.println(">>> Exception, FileNotFoundException");
            return false;
        }
        return true;
    }

    public static boolean appendTextFile // class method to append
		(String fileName, String [] fileContent) {
        try{
            //Creates a new PrintWriter, with specified file name
            PrintWriter outStream = new PrintWriter(
		new FileWriter(fileName, true)); // true for appending
            for (int i=0; i<fileContent.length; i++)
                outStream.println(fileContent[i]);// write into file, in a line
            outStream.close(); //close the stream
            System.out.println(">>> Writing File [" + fileName + "] FINISHED" );
        } catch (IOException ioE){
            System.out.println(">>> Exception, FileNotFoundException");
            return false;
        }
        return true;
    }

    public static String[] readTextFile(String fileName){
        ArrayList<String> retAList = new ArrayList<String>(); // arraylist
        try{
            String strLine;
            BufferedReader bufferReader = 
				new BufferedReader(new FileReader(fileName));
            // read file to string until end, one line a time
            while ((strLine = bufferReader.readLine()) != null) {
                retAList.add(strLine); // add the read line data to arraylist
            }
            bufferReader.close(); //close the stream
            System.out.println(">>> Reading File [" + fileName + "] FINISHED" );
        } catch (FileNotFoundException fnfE){
            System.out.println(">>> Exception, FileNotFoundException");
            return null;
        } catch (IOException ioE){
            System.out.println(">>> Exception, IOException");
            return null;
        }
        // ArrayList to String array with method toArray()
        return retAList.toArray(new String[retAList.size()]);
    }

}
