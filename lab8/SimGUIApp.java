/* SimGUIApp.java: A simple GUI-based application for demo, for U7 */
import javax.swing.*; // import for all classes in specific package
import java.awt.*;
import java.awt.event.*;
public class SimGUIApp{ // class declaration, NOT using Anonymous class --> NO output U7GUI$1.class, etc.
public static void genGUI(String fTitle){ // a method to generate GUI window
	JFrame aGUIWin = new JFrame(fTitle); // 1. main window JFrame
		JPanel northP = new JPanel(); // 2. top panel
		JButton northLB = new JButton("Type, and Click."); // 2.1 left button, at top
		JButton northRB = new JButton("Click to New one."); // 2.2 right button, at top
		JTextField northTF = new JTextField(10); // 2.3, textfield for typing; width size = 20
		JLabel centerImgL = new JLabel(new ImageIcon("cc.jpg")); // 3. a label, for showing image 
		JLabel souchInfoL = new JLabel("Info Label: Done by MyName"); // 4. a label, at bottom for text info.
// constructing top panel, containing two components responding to button and textfield
		northLB.addActionListener(cEvent -> { // button-click response: show simple dialog
			JOptionPane.showMessageDialog(null, // pop a dialog window
					"Your Typed Message:\n ["+northTF.getText()+"]\n");
		});
		northRB.addActionListener(cEvent -> { // button-click response: show simple dialog
			genGUI(fTitle + ", Again"); // create and pop another window
		});
		northP.add(northLB); northP.add(northTF); northP.add(northRB); // add elts to top panel
		aGUIWin.add(northP, BorderLayout.NORTH); // add panel to window (JFrame), to top NORTH
// constructing middle image label, and add it to window
		aGUIWin.add(centerImgL, BorderLayout.CENTER); // add image label to CENTER 
// constructing bottom information label, and add it to window
		aGUIWin.add(souchInfoL, BorderLayout.SOUTH); // add bottom button to SOUTH
// finalizing and setting the window JFrame, visible, etc.
		aGUIWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close JFrame to exit program
		aGUIWin.pack(); aGUIWin.setVisible(true); // show the window (JFrame) 
	}
	public static void main(String[ ] args) {
		genGUI("Our Sim GUI App");
		genGUI("Our Sim GUI App - 2");
	}
}