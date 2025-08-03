package GitFolder.hrutwikCode;

import javax.sound.midi.Soundbank;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateNTimeExamples {
    public static void main(String[] args) {
        LocalDate localDate=LocalDate.now();
        System.out.println("Local Date :"+localDate);
        LocalTime localTime=LocalTime.now();
        System.out.println("Local Time :"+localTime);
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println("Local Date N Time :"+localDateTime);
        ZonedDateTime zonedDateTime=ZonedDateTime.now();
        System.out.println("Zonal Date N Time : "+zonedDateTime);

        LocalDate birth=LocalDate.of(1993,8,1);
        Period age=Period.between(birth,localDate);
        System.out.println("Age of Person who Born in 1993 :"+age);

        Duration duration=Duration.ofDays(5);
        System.out.println("Duration of 5 Days :"+duration);

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        String formatted = now.format(formatter);
        System.out.println("Formatted: " + formatted);

        LocalDateTime parsed = LocalDateTime.parse("12/12/2024 10:15:30", formatter);
        System.out.println("Parsed: " + parsed);
    }
}
