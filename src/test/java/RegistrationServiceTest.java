
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.chatApi.Models.ChatUser;
import pl.chatApi.Repo.ChatUserRepository;
import pl.chatApi.Services.RegistrationService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private ChatUserRepository chatUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void test_Register_User() {
        // Przygotuj dane testowe
        String username = "testUser";
        String password = "password";

        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        // Wywo³aj testowan¹ metodê
        registrationService.registrationUserService(username, password);

        verify(chatUserRepository).save(Mockito.any(ChatUser.class));
    }
}

