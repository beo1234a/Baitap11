package anhtuan.vn.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil_24133072 {

    private static final String FROM_EMAIL = "tuantu612006@gmail.com";
    private static final String APP_PASSWORD = "jqei glsd gzau btof";

    private EmailUtil_24133072() {
    }

    public static boolean sendOTP(String toEmail, String otp) {

        try {
            Properties props = new Properties();

            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");
            props.put("mail.smtp.writetimeout", "10000");

            Session session = Session.getInstance(
                    props,
                    new Authenticator() {

                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {

                            return new PasswordAuthentication(
                                    FROM_EMAIL,
                                    APP_PASSWORD);
                        }
                    });

            MimeMessage message = new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(
                            FROM_EMAIL,
                            "Nguyen Anh Tuan - 24133072",
                            "UTF-8"));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            message.setSubject(
                    "Ma OTP kich hoat tai khoan",
                    "UTF-8");

            String content =
                    "Xin chao,\n\n"
                    + "Ma OTP kich hoat tai khoan cua ban la: "
                    + otp
                    + "\n\n"
                    + "Vui long nhap ma OTP nay de kich hoat tai khoan.\n\n"
                    + "Nguyen Anh Tuan\n"
                    + "MSSV: 24133072\n"
                    + "Ma de: 03";

            message.setText(
                    content,
                    "UTF-8");

            Transport.send(message);

            System.out.println(
                    "======================================");

            System.out.println(
                    "GUI OTP THANH CONG");

            System.out.println(
                    "Email nhan: " + toEmail);

            System.out.println(
                    "======================================");

            return true;

        } catch (Exception e) {

            System.out.println(
                    "======================================");

            System.out.println(
                    "GUI OTP THAT BAI");

            System.out.println(
                    "Loai loi: "
                    + e.getClass().getName());

            System.out.println(
                    "Noi dung loi: "
                    + e.getMessage());

            System.out.println(
                    "======================================");

            e.printStackTrace();

            return false;
        }
    }
}