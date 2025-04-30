package it.internetIdee.KnockCollector.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DateUtils {
    
    public static boolean isValidDate(String date){
        
        if(!matchDate(date))
            return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            LocalDate inputDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();

            if(inputDate.isBefore(today))
                return false;

            return true;
        }catch(DateTimeException e){
            return false;
        }
    }

    public static boolean isLegalAge(String date){
        if(!matchDate(date))
            return false;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(date, formatter);;
        LocalDate today = LocalDate.now();

        int period = Period.between(inputDate, today).getYears();
        if(period>=18 && period<100)
            return true;
        return false;
    }

    private static boolean matchDate(String date){
        String regex = "\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        if(!matcher.matches())
            return false;
        return true;
    }
}
