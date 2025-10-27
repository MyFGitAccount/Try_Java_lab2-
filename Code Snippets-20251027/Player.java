// Player.java, oop
// Given, for doing lab 6, may contain bugs
// OOP, Given Reference Only, 2025

import javax.swing.*;

public class Player{
// fields
    private int pID;
    private String pName;
    protected String pType;
    protected double pWeight;
    protected int totalP;
    
// constructor
    public Player(int id, String name){
        this.pID = id;
        this.pName = name;
        totalP++;
    }
    public Player(String name){
        this(totalP,name)
    }
    public String getName(){
        return pName;
    }
// methods
    public void setType(String type){  pType = type;  }
    public void setWeight(double w){  pWeight = w;  }
    public String getPInfo(){
        return String.format("%08d",pID)+"," +pName+","
        +pType+"," +String.format("%.2f",pWeight);
    }
    public void dispPInfo(){
        String[] pInfoList = getPInfo().split(",");// split a str to string array
        JOptionPane.showMessageDialog(null,
        "\n ID: " +pInfoList[0]
        +"\n Name: " +pInfoList[1]
        +"\n Type: " +pInfoList[2]
        +"\n Weight: " +pInfoList[3],
        "Player Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public int CountPlayer(){
       return this.totalP;
    }
////////////// <OPTIONAL> - USER INPUT NEW PLAYER /////////////////
    public static Player userCreateP(){ // a class (static) method
        String idstr, name, type, weightstr;
        idstr = JOptionPane.showInputDialog(null, "What is your ID?"); // input id
        name = JOptionPane.showInputDialog(null, "What is your Name?"); // name
        String[] optVal = { "Badminton", "Tennis", "Soccer" }; // option values
        type = (String) JOptionPane.showInputDialog(null, // "select" type
        "Choose your sport-type", "Sport Type",
        JOptionPane.INFORMATION_MESSAGE, null, optVal, optVal[0]);
        weightstr = JOptionPane.showInputDialog (null, "What is your Weight?");
        Player retP = new Player(Integer.parseInt(idstr), name); //new Player Obj
        retP.setType(type); // player object set Type
        retP.setWeight(Double.parseDouble(weightstr)); // player obj set weight
        return retP;
    }
    
////////// TESTING ONLY, to be deleted /////////
    public static void main(String[] args){
        Player andyP = new Player(20002000, "LAU Andy");
        andyP.setType("Tennis");
        andyP.setWeight(55.5);
        String andyStr = andyP.getPInfo();
        System.out.println(andyStr);
        andyP.dispPInfo();
        
        System.out.println("..TEMP TEST, by HUNG, sid");
    }
}
