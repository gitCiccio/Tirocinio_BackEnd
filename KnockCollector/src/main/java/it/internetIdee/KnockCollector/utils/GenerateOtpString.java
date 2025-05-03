package it.internetIdee.KnockCollector.utils;

import java.security.SecureRandom;

public class GenerateOtpString {

    private static final String digits = "0123456789";
    private static final SecureRandom rnd = new SecureRandom();

    public static String generateOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = rnd.nextInt(digits.length());
            otp.append(digits.charAt(index));
        }
        return otp.toString();
    }
}
