// Reference code ONLY
// Player.java: version for Object File (implements Serializable)
// Finished by: OOP, 2025

import javax.swing.JOptionPane;
public class Player implements java.io.Serializable{  // class declaration
////////// Field(s)
    private int pID;
    private String pName;
    protected String pType;
    protected double pWeight;
    protected static int totalP = 0; // a class field, for auto ID generation
////////// Constructor(s)
    public Player (int id, String name){
        pID = id;        pName = name;
        totalP++;   // update and keep track total number of players, for auto ID
    }
    public Player(String name){  // overloading constructor
        this(totalP, name); // call another constructor, with totalP as player ID
    }
////////// Method(s)
    public void setType(String type) {pType = type;}
    public void setWeight(double weight) {pWeight = weight;}    
    public String getPInfo(){
        return  String.format("%08d",pID) +"," + pName +","
            + pType +"," + String.format("%.2f",pWeight);
    }
    public void dispPInfo(){
        JOptionPane.showMessageDialog(null,
            "ID, Name, Type, Weight:\n" + getPInfo(),
            "Player Information", JOptionPane.INFORMATION_MESSAGE);
    }
////////////// <OPTIONAL> - USER INPUT NEW PLAYER /////////////////
    public static Player userCreateP(){ // a class (static) method
        String idstr, name, type, weightstr;
        idstr = JOptionPane.showInputDialog(null, "What is your ID?"); // input id
        name = JOptionPane.showInputDialog(null, "What is your Name?"); // input name
        String[] optVal = { "Badminton", "Tennis", "Soccer" }; // type option values
        type = (String) JOptionPane.showInputDialog(null, // "select" type
				"Choose your sport-type", "Sport Type",
				JOptionPane.INFORMATION_MESSAGE, null, optVal, optVal[0]);
        weightstr = JOptionPane.showInputDialog (null, "What is your Weight?");        
        Player retP = new Player(Integer.parseInt(idstr), name);//new Player Obj
        retP.setType(type); // player object set type
        retP.setWeight(Double.parseDouble(weightstr)); // player obj set weight
        return retP;
    }
}
