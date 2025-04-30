package it.internetIdee.KnockCollector.utils;

public class AmountUtils {
    
    public static boolean isValidAmount(double amount){
        if(amount<=0)
            return false;
        return true;
    }
}
