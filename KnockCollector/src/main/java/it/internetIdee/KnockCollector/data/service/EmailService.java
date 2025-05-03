package it.internetIdee.KnockCollector.data.service;

import it.internetIdee.KnockCollector.data.entity.Otp;
import it.internetIdee.KnockCollector.repository.OtpRepository;
import it.internetIdee.KnockCollector.utils.GenerateOtpString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final OtpRepository otpRepository;

    private final JavaMailSender mailSender;

    public boolean checkOtp(String otp, String email) {
        Optional<Otp> optionalOtp = otpRepository.findOtpByUserEmail(email);

        if (optionalOtp.isEmpty()) {
            log.debug("OTP non trovato");
            return false;
        }

        Otp foundOtp = optionalOtp.get();

        if (foundOtp.getCreateDate().isBefore(LocalDateTime.now().minusMinutes(5))) {
            log.debug("OTP scaduto");
            otpRepository.delete(foundOtp);
            return false;
        }

        if (foundOtp.isUsed()) {
            log.debug("OTP già utilizzato");
            otpRepository.delete(foundOtp);
            return false;
        }

        if (!foundOtp.getOtp().equals(otp)) {
            log.debug("Codice OTP non valido");
            return false;
        }

        // Se tutto va bene, puoi marcare come usato
        otpRepository.delete(foundOtp);

        return true;
    }


    public void sendMail(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom("ftartaglia29@gmail.com");//da cambiare con account apposito
            message.setSubject("Codice OTP");
            Otp otp = new Otp();
            otp.setUserEmail(email);
            otp.setCreateDate(LocalDateTime.now());
            otp.setOtp(GenerateOtpString.generateOtp());
            otp.setUsed(false);
            otpRepository.save(otp);
            message.setText("Codice OTP: " + otp.getOtp() + " scade tra 5 minuti!");
            mailSender.send(message);
        } catch (Exception e) {
            log.error("Errore nell'invio dell'email", e);
        }
    }


}
