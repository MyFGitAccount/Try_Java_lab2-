import javax.swing.JOptionPane;

public class L31NumAna{
    public static final int arrSize=10;
    public static double []doubleArr=new double[arrSize];
    
    public static double AVG(double []doubleArray){
        double result=0;
        if(doubleArray.length==0){return 0;}
        for(int i=0;i<doubleArray.length;i++){
            result+=doubleArr[i];
        }
        return result/doubleArray.length;
    }
    public static double Max(double []doubleArr){
        if(doubleArr.length==0) return 0;
        double max=0;
        for(int i=0;i<doubleArr.length;i++){
            max=Math.max(max,doubleArr[i]);
        }
        return max;
    }
    public static double Min(double []doubleArr){
        if(doubleArr.length==0) return 0;
        double min=0;
        for(int i=0;i<doubleArr.length;i++){
            min=Math.min(min,doubleArr[i]);
        }
        return min;
    }
    public static void main(String []arg){
        doubleArr[0]=1;
        doubleArr[1]=1.9;
        doubleArr[2]=4.5;
        doubleArr[3]=6.2;
        doubleArr[4]=-7.32;
        doubleArr[5]=100.876;
        doubleArr[6]=121.098;
        doubleArr[7]=-70.67;
        doubleArr[8]=67.23;
        doubleArr[9]=9.987;
        System.out.print("The numbers are:");
        for(int i=0;i<doubleArr.length;i++){
            if(i!=doubleArr.length-1) System.out.print(doubleArr[i]+",");
            else {System.out.print(doubleArr[i]);};
        }
        System.out.print("\n");
        System.out.print("From Multiple Methods:- " + "AVG:"+AVG(doubleArr)+", "+"MIN:"+Min(doubleArr)+", "+"MAX:"+Max(doubleArr));
        JOptionPane.showMessageDialog(null,"AVG: "+AVG(doubleArr)+","+"MIN: "+Min(doubleArr)+","+"MAX:"+Max(doubleArr), "Geeks Premier League 2023", 
        JOptionPane.INFORMATION_MESSAGE);
    }
}