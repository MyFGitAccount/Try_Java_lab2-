import java.util.Scanner;

public class L22Console {
    public static float Max(float... n){
        float max=0;
        for(float i: n){
            if(max<i){
                max=i;
            }
        }
        return max;
    }
    public static float Min(float... n){
        float min=1000;
        for(float i: n){
            if(min>i){
               min=i;
            }
        }
        return min;
    }
    public static float Sum(float... n){
        float sum=0;
        for(float i: n){
            sum+=i;
        }
        return sum;
    }
    public static void main(String []arg){
        System.out.println("Input three numbers for analysis");
        Scanner scanner = new Scanner(System.in);
        float num1=scanner.nextFloat();        
        float num2=scanner.nextFloat();    
        float num3=scanner.nextFloat();
        System.out.println(String.format("Number: %.3f %.3f %.3f", num1,num2,num3));
        System.out.println(String.format("Maximum: %.3f",Max(num1,num2,num3)));
        System.out.println(String.format("Minimum: %.3f",Min(num1,num2,num3)));
        System.out.println(String.format("Sum: %.3f",Sum(num1,num2,num3)));
    }
}
