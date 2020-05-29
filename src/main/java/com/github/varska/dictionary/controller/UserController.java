package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.entity.User;
import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.service.EmailService;
import com.github.varska.dictionary.service.UserService;
import com.github.varska.dictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    private UserService userService;
    private WordService wordService;
    private EmailService emailService;

    @GetMapping("/user/{username}")
    public String getUser(@PathVariable ("username") String username, Model model){

        User user = userService.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Такого пользователя не существует");
        }

        List<Word> wordList = wordService.findByUser(user);
        model.addAttribute("userWords", wordList);

        return "user/author";
    }

    @GetMapping("/reset-email")
    public String getResetPage(){
        return "user/reset-email";
    }

    @PostMapping("/reset-email")
    public String resetEmail(@RequestParam("email") String email,
                             Model model,
                             HttpServletRequest req){

        User user = userService.findByEmail(email);

        if (user == null){
            throw new UsernameNotFoundException("Такого пользователя не существует");
        }

        user.setResetToken(UUID.randomUUID().toString());
        userService.save(user);

        String appUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getLocalPort();


        SimpleMailMessage resetEmail = new SimpleMailMessage();
        resetEmail.setTo(user.getEmail());
        resetEmail.setSubject("Reset password");
        resetEmail.setText("To reset your password, click the link below:\n" + appUrl
                + "/change-password?token=" + user.getResetToken());

        emailService.sendEmail(resetEmail);

        model.addAttribute("successMessage", "A password reset link has been sent to " + email);

        return "user/reset-email";
    }

    @GetMapping("/change-password")
    public String changePasswordPage(@RequestParam("token") String token,
                                     Model model,
                                     HttpServletRequest req){
        User user = userService.findByResetToken(token);
        if (user == null){
            throw new UsernameNotFoundException("User with this token is not found");
        }

        String appUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getLocalPort();
        String refreshUrl = appUrl + "/change-password?token=" + user.getResetToken();

        model.addAttribute("refreshUrl", refreshUrl);
        model.addAttribute("resetToken", token);
        return "user/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam Map<String, String> requestParams,
                                               Model model
                                ) {
        User user = userService.findByResetToken(requestParams.get("token"));

        if (user == null) {
            throw new UsernameNotFoundException("User with this token is not found");
        }

        if (!requestParams.get("password").equals(requestParams.get("confirmPassword"))) {
            return "redirect:" + requestParams.get("refreshUrl");
        }

        user.setPassword(requestParams.get("password"));
        user.setResetToken(null);

        model.addAttribute("passwordChanged", "Пароль был успешно изменен");
        userService.save(user);

        return "user/login";
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }
}
