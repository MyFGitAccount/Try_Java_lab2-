import javax.swing.JOptionPane;

public class RecordSys {
    static Player []playerRecord=new Player[1000];
    private static int playerCount=0;
    public static Player login(){
        String name = JOptionPane.showInputDialog("Enter your Name:");
        String Id=JOptionPane.showInputDialog("Enter your ID: ");
        String type=JOptionPane.showInputDialog("Enter your sport type: ");
        String weight=JOptionPane.showInputDialog("Enter your weight: ");
        Player playerA=new Player(Integer.parseInt(Id), name, type, Double.parseDouble(weight));
        playerA.disPInfo();
        playerRecord[playerCount]=playerA;
        playerCount++;
        return playerA;
    }
    public static void main(String []arr){
        String name = JOptionPane.showInputDialog("Enter your Name:");
        String Id=JOptionPane.showInputDialog("Enter your ID: ");
        String type=JOptionPane.showInputDialog("Enter your sport type: ");
        String weight=JOptionPane.showInputDialog("Enter your weiught: ");
        Player playerA=new Player(Integer.parseInt(Id), name, type, Double.parseDouble(weight));
        playerA.disPInfo();
        Player playerB=login();

            }
    
}
