import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.chatApi.Controlers.JpaController;
import pl.chatApi.Models.ChatUser;
import pl.chatApi.Services.RegistrationService;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class JpaControllerTest {

    @InjectMocks
    private JpaController jpaController;

    @Mock
    private RegistrationService registrationService;

    @Test
    public void test_Register_User() {
        ChatUser user = new ChatUser("testUser","password");

        jpaController.registerUser(user);

        verify(registrationService, times(1))
                .registrationUserService(user.getUsername(),user.getPassword());
    }
}
