import javax.swing.JOptionPane;

public class L32MarkSix {
    public static int[] genRanNum(int totalInt,int min,int max){
        int []Intarr=new int[totalInt];
        int num=0;
        for(int i=0;i<totalInt;i++){
            num=(int)(Math.random()*max);
            if(num<min) num=min;
            Intarr[i]=num;
        }
        return Intarr;
    }
    public static void main(String []arr){
        int[] Intarr=genRanNum(10, 10, 50);
        String IntStringArr=java.util.Arrays.toString(Intarr);
        System.out.print("Intarr:");
        System.out.print(IntStringArr);
        String out="";
        for(int i=0;i<Intarr.length;i++){
            if(i<Intarr.length-1) out+=""+Intarr[i]+", ";
            else{out+=""+Intarr[i];};
        }
        System.out.println();
        try{
            for(int i=0;i<Intarr.length;i++){
                if (i<Intarr.length-1) System.out.print(Intarr[i]+", ");
                else{System.out.print(Intarr[i]);};
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            System.out.println("Thread interrupted:"+e.getMessage());
        }
         JOptionPane.showMessageDialog(null,"MyName's Mark 6 numbers are:\n"+out, "Mark Six", JOptionPane.INFORMATION_MESSAGE);
    }
}
