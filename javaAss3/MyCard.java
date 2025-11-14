public class MyCard extends Card {
    int code;
    String name;
    public static final int DEF_VOICE=23048;
    MyCard(String n,int c){
      super(n,c);
    }
    MyCard(String n){
        super(n,DEF_VOICE);
    }

    public static void main(String[] arr){
        Card[] CardArr=new Card[4];
        CardArr[0]=new Card("cdA",2134);
        CardArr[1]=new Card("cdB", 2135);
        CardArr[2]=new Card("cdC",2136);
        CardArr[3]=new MyCard("cdD");
    }
}
