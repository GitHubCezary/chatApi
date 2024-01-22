package pl.chatApi.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.chatApi.Models.ChatUser;
import pl.chatApi.Services.RegistrationService;

@CrossOrigin
@RestController
public class JpaController {
    private final RegistrationService registrationService;

    @Autowired
    public JpaController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody ChatUser user) {
        registrationService.registrationUserService(user.getUsername(), user.getPassword());
    }
    @GetMapping("/test")
    public String getHello(){
        return "hello";
    }
}
