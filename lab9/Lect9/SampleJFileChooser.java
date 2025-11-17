import javax.swing.*;
import java.io.*;

public class SampleJFileChooser {
    public static void main(String[] args) {
	JFileChooser chooser;
        	File file;
        	int status;
	// start the listing from a specific directory "D:/"
//	chooser = new JFileChooser("./");  // Current directory
//	chooser = new JFileChooser("c:/Users/tempi/Downloads");	 // Specific directory
	chooser = new JFileChooser();   // Default directory
         System.out.println("------------------Start chooser\n"+chooser+"----------------------\nEnd Chooser");
	// show the dialog with customized label "Browse"
//        	status = chooser.showDialog(null,"Browse"); 
//        	status = chooser.showDialog(null, "open"); 
        	status = chooser.showOpenDialog(null); 
        System.out.println("------------------Start status\n"+status+"----------------------\nEnd status");
	if (status == JFileChooser.APPROVE_OPTION) {
	file = chooser.getSelectedFile();
        System.out.println("------------------Start file\n"+file+"----------------------\nEnd file");
	// show the message dialog "Open File: <Your selected file name>"
	JOptionPane.showMessageDialog(null, "Open File: " + file.getName());
	} else {
           	JOptionPane.showMessageDialog(null, "Open File dialog cancelled");
        }
    }
}
