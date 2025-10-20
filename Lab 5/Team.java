import javax.swing.JOptionPane;

public class Team {
    String tID;
    String tName;    
    Player tCaptain;
    Player []tPlayers;
    int tPlayerCounter=0;

    public Team (String id, String name, Player captain){
        tID = id; tName = name; tCaptain = captain;
        }
    
    public void setPlayer(Player p){
        this.tPlayers[tPlayerCounter]=p;
        tPlayerCounter++;
    }
    public String getTInfo(){
        return tID +"," +tName +"," +tCaptain.pName;
    }
    public void dispTInfo(){
       JOptionPane.showMessageDialog(null, String.format("ID:%s\nTeam Name:%s\nTeam Captain:%s",tID,tName,tCaptain), tID, tPlayerCounter);
    }
    public static void main(String []arg){
       
    }
}
