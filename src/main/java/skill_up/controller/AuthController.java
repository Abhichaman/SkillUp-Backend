package skill_up.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import skill_up.entity.User;
import skill_up.repo.UserRepository;
import skill_up.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    // Step 1: Send OTP
    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email,@RequestParam String name) {
        otpService.generateAndSendOtp(email,name);
        return "OTP sent to " + email;
    }

    // Step 2: Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity <?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean verified = otpService.verifyOtp(email, otp);
        if (verified) {
            // get user again from DB (because we only got boolean before)
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Map<String, Object> response = new HashMap<>();
            response.put("name", user.getName());
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invaild Otp!");
        }

    }
}
