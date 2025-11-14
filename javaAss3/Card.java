public class Card extends Vehicle{
    public String cName;
    protected int cCode;
    Card(String inName,int inCode){
        super(inName,inCode);
    }
    public int getcCode() {
        return cCode;
    }
    public String[] getInfo(){
       String[] returnString=new String[2];
       returnString[0]=cName;
       returnString[1]=Integer.toString(cCode);
       return returnString;
    }
    public void dspInfo(){
        super.dspInfo();
    }

}