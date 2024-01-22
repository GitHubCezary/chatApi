package pl.chatApi.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.chatApi.Models.ChatUser;
import pl.chatApi.Repo.ChatUserRepository;

@Service
public class UserDetailServiceImplement implements UserDetailsService {

    private final ChatUserRepository chatUserRepository;


    @Autowired
    public UserDetailServiceImplement(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ChatUser chatUser = chatUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return User.withUsername(chatUser.getUsername())
                .password(chatUser.getPassword())
                .build();
    }
}
