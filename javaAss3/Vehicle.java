public class Vehicle {
    public String cName;
    protected int cCode;

    // Constructor for Vehicle
    public Vehicle(String inName, int inCode) {
        cName = inName;
        cCode = inCode;
    }
    public void dspInfo(){
        System.out.printf("Card Name: %s; Card Code: %d",cName,cCode);
    }
}
