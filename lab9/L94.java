import java.io.*;
import javax.swing.*;
public class L94 {
public static void main(String[] args){ // for testing only
   System.out.println("--- Lab Program: Copy, Rename and Delete Files ---");
   // a)
   JFileChooser chooser = new JFileChooser("./"); // from current dir.
   int status = chooser.showDialog(null, "Select File to Copy...");
   String orgFile = null;
   if (status == JFileChooser.APPROVE_OPTION)
      orgFile = chooser.getSelectedFile().getName(); // selected file name
   // b)
   try { // copy selected file to 3 new files: CA*, CB*, CC*
      java.nio.file.Files.copy(new File(orgFile).toPath(),
      new File("_CA"+orgFile).toPath());
      java.nio.file.Files.copy(new File(orgFile).toPath(),
      new File("_CB"+orgFile).toPath());
      java.nio.file.Files.copy(new File(orgFile).toPath(),
      new File("_CC"+orgFile).toPath());
      } catch (IOException ioe) {
         System.out.println("ERR: IOException");
      }
      JOptionPane.showMessageDialog(null, "Copy Finished");
      // c)
      new File("_CA"+orgFile).renameTo(new File("_RCA"+orgFile)); //rename
     JOptionPane.showMessageDialog(null, "Rename OK:\n"+("_RCA"+orgFile));
     // d)
     new File("_CB"+orgFile).delete(); // delete file
     JOptionPane.showMessageDialog(null, "Delete OK:\n"+("_CB"+orgFile));
     }
}
