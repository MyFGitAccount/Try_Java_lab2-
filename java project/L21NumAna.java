

public class L21NumAna {
    public float firstNum;
    public float secNum;
    public float thirdNum;
    public float []Numarr;
    public float Max(float num1,float num2,float num3){
        if(num1>num2&&num1>num3){
            return num1;
        }
        else if(num2>num1&&num2>num3){
            return num2;
        }
        else if(num3>num1&&num3>num2){
            return num3;
        }
        else{
            return 0;
        }
  }
  public float Min(float num1,float num2,float num3){
       if(num1<num2&&num1<num3){
            return num1;
        }
        else if(num2<num1&&num2<num3){
            return num2;
        }
        else if(num3<num1&&num3<num2){
            return num3;
        }
        else{
            return 0;
        }
  }
  public float Avr(float num1,float num2,float num3){
    return (num1+num2+num3)/3;
  }
  public float Sum(float num1,float num2,float num3){
    return num1+num2+num3;
  }
  public float getThirdNum(float num){
     return (float) (num*4.23);
  }
  public static void main(String []arg){
     L21NumAna num=new L21NumAna();
     num.firstNum=Float.parseFloat(arg[0]);
     num.secNum=(float)4.23;
     num.thirdNum=num.getThirdNum(num.firstNum);
     num.Numarr=new float[3];
     num.Numarr[0]=num.firstNum;
     num.Numarr[1]=num.secNum;
     num.Numarr[2]=num.thirdNum;
     System.out.print("Numbers:");
     for(int i=0;i<num.Numarr.length;i++){
        System.out.print(num.Numarr[i]+" ");
     }
     System.out.print("\n");
     System.out.println("Minimum: "+ num.Min(num.firstNum,num.secNum,num.thirdNum) + " Maximum: "+num.Max(num.firstNum,num.secNum,num.thirdNum));
     System.out.println("Sum: "+num.Sum(num.firstNum,num.secNum, num.thirdNum));
    }
}
