class Player{
    private int pID;
    private String pName;
    protected String pType;
    protected double pWeight;
    protected int totalP;
    Player(int id,String name){
       this.pName=name;
       this.pID=id;
       this.totalP++;
    }
    Player(String name){
        this.pName=name;
    }
    public String getName(){
       return this.pName;
    }
    public setType(String typ){
        this.pType=typ;
    }
    public setWeight(double w){
        this.pWeight=w;
    }
    public String getPInfo(){
       return 
    }
}
