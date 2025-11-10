import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class In{
    public static void main(String[] arg){
       if(arg.length<1){
         System.out.println("The user does not provide the file");
         System.exit(69);
       }
       for(int i=0;i<arg.length;i++){
          String fileName=arg[i];
          try{
            File file=new File(fileName);
            System.out.println("File:"+i);
            Scanner FileScanner=new Scanner(file);

            while(FileScanner.hasNextLine()){
                System.out.println(FileScanner.nextLine());
            }
            FileScanner.close();
          }catch(FileNotFoundException E){
            System.out.println("Error: "+E);
            System.out.println("Exit with:"+i);
            System.exit(i);
          }

        }
    }
}