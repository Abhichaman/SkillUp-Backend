package skill_up.service;

import skill_up.entity.User;
import skill_up.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Generate OTP and send
    public String generateAndSendOtp(String email,String name) {
        String otp = String.format("%06d", new Random().nextInt(999999)); // always 6 digits

        User user = userRepository.findByEmail(email).orElse(new User());
        user.setEmail(email);
        user.setName(name);
        user.setOtp(otp);
        user.setVerified(false);
        userRepository.save(user);

        // Send OTP by email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);

        return otp;
    }

    // Verify OTP
    public boolean verifyOtp(String email, String otp) {
        return userRepository.findByEmail(email)
                .map(user -> {

                    if (user.getOtp() != null && user.getOtp().equals(otp)) {
                        user.setVerified(true);
                        user.setOtp(null); // clear OTP after success
                        userRepository.save(user);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

}
