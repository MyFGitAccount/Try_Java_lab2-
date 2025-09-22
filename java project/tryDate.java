import java.time.LocalDate;
import java.util.Calendar;

public class tryDate {
    public static void main(String []arg){
        LocalDate date=LocalDate.now().withDayOfYear(0);
        System.out.println(date);

    }
}
