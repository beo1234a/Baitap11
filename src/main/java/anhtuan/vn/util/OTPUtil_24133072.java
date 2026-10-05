package anhtuan.vn.util;

import java.util.Random;

public class OTPUtil_24133072 {

    private OTPUtil_24133072() {
    }

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}