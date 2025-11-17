// L93.java; OOP
import javax.swing.*;
import java.io.*;
public class L93 {
public static void main(String[] args){
   System.out.println("--- Lab Program: Simple Object File Write/Read ---");
   String fName = "Player.obj";
   // default 3 Players
   Player[] allP = new Player[3];
   allP[0] = new Player(20002000, "LAU Andy");
   allP[0].setType("Badminton"); allP[0].setWeight(65);
   allP[1] = new Player(20002001, "CHOW Betty");
   allP[1].setType("Tennis"); allP[1].setWeight(50);
   allP[2] = new Player(20022002, "AU Candy");
   allP[2].setType("Tennis"); allP[2].setWeight(60.5);
   // Write to object file
   try{
      ObjectOutputStream ObjStream = new ObjectOutputStream(new FileOutputStream(fName));
      ObjStream.writeObject ( allP ); //save the array
      ObjStream.close();
      } catch (IOException ioe){}
      // Read from object file
      try{
         ObjectInputStream inObjStream = new ObjectInputStream(new FileInputStream(fName));
         Player[ ] readPs = (Player[]) inObjStream.readObject( );
         inObjStream.close();
         System.out.println("> Data read from the Object File:");
         for (int i=0; i<readPs.length; i++)
            readPs[i].dispPInfo();
         } catch (IOException ioe){}
           catch (ClassNotFoundException cnfe){}
         System.out.println("\n>>> END of Program main()! Developed by MyName (20202020) <<< ");
         }
}
