// OOP, Given Reference Only, 2025
import javax.swing.JOptionPane;
public class RecordSys {    // the main class, for starting the program
    public static void main (String[] args){    // main() method, entry point of execution
        JOptionPane.showMessageDialog(null, 
            "Team and Player Record System\n...Press <OK> to read current Records",
            "CC Record System 2024", JOptionPane.INFORMATION_MESSAGE);
// default 3 Players		
        Player a = new Player(20002000, "LAU Andy"); // create player (1st)
        a.setType("Badminton");    a.setWeight(65);
        a.dispPInfo();
        Player b = new Player(20012001, "CHOW Betty"); // create player (2nd)
        b.setType("Tennis");    b.setWeight(50);
        b.dispPInfo();
        Player c = new Player(20022002, "AU Candy"); // create player (3rd)
        c.setType("Tennis");    c.setWeight(60.5);
        c.dispPInfo();
//////// ... MORE HERE??

////////////// <OPTIONAL> - USER INPUT NEW PLAYER, tested OK /////////////////
//        Player uiP = Player.userCreateP();
//        uiP.dispPInfo();

// default 2 Teams
        Team t01 = new Team("ST01", "School Badminton Team", a);
        t01.dispTInfo();
        Team t02 = new Team("ST02", "School Tennis Team", b);
        t02.dispTInfo();        
    }   
}