import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JavaNew {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);


        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //String format = formatter.format(LocalDateTime.now());
        //System.out.println( format );

        String stDate = "2023-01-20";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(stDate, formatter);
        System.out.println( localDate.getYear() );

    }
}
