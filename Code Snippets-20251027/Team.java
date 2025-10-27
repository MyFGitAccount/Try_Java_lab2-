// Reference code ONLY, may not complete and may have MANY BUGS
// Class: Team, based on the designed UML
// Finished by: OOP, 2025

import javax.swing.JOptionPane;

public class Team {  // class declaration
////////// Field(s)
    String tID;
    String tName;
    Player tCaptain;    
    Player[] tPlayers;
////////// Constructor(s)
    public Team (String id, String name, Player captain){
        tID = id;  tName = name;  tCaptain = captain;
    }

////////// Method(s)
    public void setPlayers(Player[] players){ tPlayers = players;}
    public String getTInfo(){
        return  tID +"," +tName +"," +tCaptain.getName();
    }
    public void dispTInfo(){
    	String[] tInfoList = getTInfo().split(","); // split string
        JOptionPane.showMessageDialog(null,
        	 "\n ID: " +tInfoList[0] 
        	+"\n Name: " +tInfoList[1]
        	+"\n Captain: " +tInfoList[2],
            "Team Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
