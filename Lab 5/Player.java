import javax.swing.JOptionPane;

class Player{
    private int pID;
    public String pName;
    private String pType;
    private double pWeight;
    Player(int id,String pName,String pType,double w){
        this.pID=id;
        this.pName=pName;
        this.pType=pType;
        this.pWeight=w;
        System.out.println(String.format("Player object is constructed and it has %d id %s pType and %f weight",this.pID,this.pType,this.pWeight));
    }
    public void setType(String ty){
        this.pType=ty;
    }
    public void setWeight(double w){
        this.pWeight=w;
    }
    public String getPInfo(){
        return String.format("%d,%s,%s,%f",this.pID,this.pName,this.pType,this.pWeight);
    }
    public void disPInfo(){
        System.out.println(String.format(
            "ID:%d Name:%s Type:%s Weight:%f",this.pID,this.pName,this.pType,this.pWeight
        ));
        JOptionPane.showMessageDialog(null, String.format("\nID:%d\nName:%s\nType:%s\nWeight:%f",this.pID,this.pName,this.pType,this.pWeight), "Player Information",  	JOptionPane.OK_OPTION);
    }
}