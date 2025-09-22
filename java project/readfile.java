import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class readfile {
    public static void main(String []arg){
        try {
            FileInputStream in=new FileInputStream(arg[0]);
            int ch;
            while ((ch =in.read())!=-1){
                System.out.print((char)ch);
            }

        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
           System.out.println(e);
        }
    }
}
