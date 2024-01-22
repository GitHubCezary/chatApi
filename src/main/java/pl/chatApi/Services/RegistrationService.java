package pl.chatApi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chatApi.Models.ChatUser;
import pl.chatApi.Repo.ChatUserRepository;

@Service
public class RegistrationService {
    private final ChatUserRepository chatUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(ChatUserRepository chatUserRepository, PasswordEncoder passwordEncoder) {
        this.chatUserRepository = chatUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrationUserService(String username, String password) {

        String hashedPassword = passwordEncoder.encode(password);

        ChatUser newUser = new ChatUser(username, hashedPassword);
        chatUserRepository.save(newUser);
    }
}
