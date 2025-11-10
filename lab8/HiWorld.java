/* HiWorld.java: A bit complicated Java program
*  More comments here...
*/ 
package com.example.mypackage; // package statement
import javax.swing.JOptionPane; // import statement here

public class HiWorld {  // class (named HiWorld) declaration

  private String inNameStr;   // declare a field / data member

  HiWorld(String inStr) {  // a constructor
	inNameStr = inStr;
  }

  void sayHi() {  // a method
	JOptionPane.showMessageDialog(null, "Hi " + inNameStr + "!");
  }

// This special method, main(), is the program entry point as in C
  public static void main(String[ ] args) {
	String nameStr = JOptionPane.showInputDialog(null, 
			"What is your name?");
	HiWorld myHW = new HiWorld(nameStr); //declare & create object
	myHW.sayHi(); //call the method (of object)
  }
}
